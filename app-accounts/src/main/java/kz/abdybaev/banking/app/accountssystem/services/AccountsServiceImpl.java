package kz.abdybaev.banking.app.accountssystem.services;

import kz.abdybaev.banking.app.accountssystem.entities.AccountEntity;
import kz.abdybaev.banking.app.accountssystem.entities.BalanceEntity;
import kz.abdybaev.banking.app.accountssystem.repositories.AccountsRepository;
import kz.abdybaev.banking.app.accountssystem.services.converters.AccountConverter;
import kz.abdybaev.banking.app.accountssystem.services.converters.BalanceConverter;
import kz.abdybaev.banking.app.accountssystem.services.dto.*;
import kz.abdybaev.banking.lib.common.domain.BalanceKind;
import kz.abdybaev.banking.lib.common.dto.CreateBalanceRq;
import kz.abdybaev.banking.lib.common.exceptions.BadArgumentsException;
import kz.abdybaev.banking.lib.common.operation.KnownDescriptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final AccountConverter accountConverter;

    @Override
    public CreateAccountRes createAccount(CreateAccountArgs args) {
        var balanceKinds = args.balances().stream().map(CreateBalanceRq::getKind).collect(Collectors.toSet());
        if (balanceKinds.size() != args.balances().size())
            throw new BadArgumentsException(KnownDescriptions.PROVIDE_DEFINED_BALANCES);
        var notProvidedBalancesStream = Arrays.stream(BalanceKind.values()).filter(Predicate.not(balanceKinds::contains)).map(kind -> new CreateBalanceRq(kind, BigDecimal.ZERO));
        var accountEntity = new AccountEntity();
        accountEntity.setUserId(args.userId());
        var balances = Stream.concat(notProvidedBalancesStream, args.balances().stream()).map(balanceItemDto -> {
            var balanceEntity = new BalanceEntity();
            balanceEntity.setAccountEntity(accountEntity);
            balanceEntity.setBalanceKind(balanceItemDto.getKind());
            balanceEntity.setValue(balanceItemDto.getAmount());
            return balanceEntity;
        }).collect(Collectors.toSet());
        accountEntity.setBalances(balances);
        accountsRepository.save(accountEntity);
        return new CreateAccountRes(accountEntity.getId());
    }

    @Override
    public List<AccountRes> searchAccounts(SearchAccountsArgs args) {
        return null;
    }

    @Override
    public CreateDebitRes createDebit(CreateDebitArgs args) {

        return null;
    }

    @Override
    public CreateCreditRes createCredit(CreateCreditArgs args) {
        return null;
    }

    @Override
    public List<GetAccountItemRes> getAccounts() {
        return accountsRepository.findAll()
                .stream()
                .map(accountConverter::convert)
                .collect(Collectors.toList());
    }
}
