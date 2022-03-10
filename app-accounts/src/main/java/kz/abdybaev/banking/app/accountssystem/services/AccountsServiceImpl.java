package kz.abdybaev.banking.app.accountssystem.services;

import kz.abdybaev.banking.app.accountssystem.entities.AccountEntity;
import kz.abdybaev.banking.app.accountssystem.entities.BalanceEntity;
import kz.abdybaev.banking.app.accountssystem.entities.CreditEntity;
import kz.abdybaev.banking.app.accountssystem.entities.DebitEntity;
import kz.abdybaev.banking.app.accountssystem.repositories.AccountsRepository;
import kz.abdybaev.banking.app.accountssystem.services.converters.AccountsConverter;
import kz.abdybaev.banking.app.accountssystem.services.converters.BalanceConverter;
import kz.abdybaev.banking.app.accountssystem.services.dto.*;
import kz.abdybaev.banking.lib.accounts.domain.AccountStatus;
import kz.abdybaev.banking.lib.common.domain.BalanceKind;
import kz.abdybaev.banking.lib.common.dto.CreateBalanceRequest;
import kz.abdybaev.banking.lib.common.exceptions.AccountNotFound;
import kz.abdybaev.banking.lib.common.exceptions.BadArgumentsException;
import kz.abdybaev.banking.lib.common.exceptions.InsufficentFundsException;
import kz.abdybaev.banking.lib.common.operation.KnownDescriptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {
    private final AccountsRepository accountsRepository;
    private final BalanceConverter balanceConverter;
    private final AccountsConverter accountsConverter;
    private final EntityManagerFactory emf;
    private static final int MIN_SIZE_EXTERNAL_ID = 32;
    @Override
    public CreateAccountResult createAccount(CreateAccountArguments args) {
        var balanceKinds = args.balances().stream().map(CreateBalanceRequest::getKind).collect(Collectors.toSet());
        if (balanceKinds.size() != args.balances().size())
            throw new BadArgumentsException(KnownDescriptions.PROVIDE_DEFINED_BALANCES);
        var notProvidedBalancesStream = Arrays.stream(BalanceKind.values())
                .filter(Predicate.not(balanceKinds::contains))
                .map(kind -> new CreateBalanceRequest(kind, BigDecimal.ZERO));
        var accountEntity = new AccountEntity();
        accountEntity.setUserId(args.userId());
        accountEntity.setAccountType(args.accountType());
        accountEntity.setAccountStatus(AccountStatus.ACTIVE);
        var balances = Stream.concat(notProvidedBalancesStream, args.balances().stream()).map(balanceItemDto -> {
            var balanceEntity = new BalanceEntity();
            balanceEntity.setAccountEntity(accountEntity);
            balanceEntity.setBalanceKind(balanceItemDto.getKind());
            balanceEntity.setValue(balanceItemDto.getAmount());
            return balanceEntity;
        }).collect(Collectors.toSet());
        accountEntity.setBalances(balances);
        accountsRepository.save(accountEntity);
        return new CreateAccountResult(accountEntity.getId());
    }

    @Override
    public CreateDebitResult createDebit(CreateDebitArguments arguments) {
        var em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            var accountEntity = em.find(AccountEntity.class, arguments.accountId(), LockModeType.PESSIMISTIC_WRITE);
            if (accountEntity == null) {
                throw new AccountNotFound();
            }
            var availableBalance = accountEntity.getAvailableBalance();
            if (availableBalance.getValue().compareTo(arguments.amount()) < 0) {
                throw new InsufficentFundsException();
            }
            var newValue = availableBalance.getValue().subtract(arguments.amount());
            availableBalance.setValue(newValue);
            var externalId = processExternalId(arguments.externalId());
            var debit = new DebitEntity();
            debit.setAccountEntity(accountEntity);
            debit.setAmount(arguments.amount());
            debit.setExternalId(externalId);
            em.persist(availableBalance);
            em.persist(debit);
            em.getTransaction().commit();
            return new CreateDebitResult(debit.getId(), accountEntity.getId(), debit.getAmount(), debit.getExternalId());
        } catch (Exception exception) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw exception;
        } finally {
            em.close();
        }
    }

    @Override
    public CreateCreditResult createCredit(CreateCreditArguments arguments) {
        var em = emf.createEntityManager();
        var accountEntity = em.find(AccountEntity.class, arguments.accountId(), LockModeType.PESSIMISTIC_WRITE);
        if (accountEntity == null) {
            throw new AccountNotFound();
        }
        var availableBalance = accountEntity.getAvailableBalance();
        var newValue = availableBalance.getValue().add(arguments.amount());
        availableBalance.setValue(newValue);
        var externalId = processExternalId(arguments.externalId());
        var credit = new CreditEntity();
        credit.setAccountEntity(accountEntity);;
        credit.setAmount(arguments.amount());
        credit.setExternalId(externalId);
        em.persist(availableBalance);
        em.persist(credit);
        return new CreateCreditResult(credit.getId());
    }

    @Override
    public List<GetAccountItemResult> getAccounts(SearchAccountsArguments arguments) {
        return accountsRepository.findAll()
                .stream()
                .map(accountsConverter::convert)
                .collect(Collectors.toList());
    }
    private String processExternalId(String externalId) {
        var value = externalId.trim();
        if (value.length() < MIN_SIZE_EXTERNAL_ID) throw new BadArgumentsException(KnownDescriptions.INVALID_EXTERNAL_ID_LENGTH);
        return value;
    }
}
