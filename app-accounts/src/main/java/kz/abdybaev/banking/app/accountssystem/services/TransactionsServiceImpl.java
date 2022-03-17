package kz.abdybaev.banking.app.accountssystem.services;

import kz.abdybaev.banking.app.accountssystem.domain.Transaction;
import kz.abdybaev.banking.app.accountssystem.services.dto.*;
import kz.abdybaev.banking.lib.accounts.domain.TransactionStatus;
import kz.abdybaev.banking.app.accountssystem.entities.AccountEntity;
import kz.abdybaev.banking.app.accountssystem.entities.CreditEntity;
import kz.abdybaev.banking.app.accountssystem.entities.DebitEntity;
import kz.abdybaev.banking.lib.accounts.domain.TransactionType;
import kz.abdybaev.banking.lib.common.exceptions.AccountNotFound;
import kz.abdybaev.banking.lib.common.exceptions.BadArgumentsException;
import kz.abdybaev.banking.lib.common.exceptions.InsufficentFundsException;
import kz.abdybaev.banking.lib.common.operation.KnownDescriptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TransactionsServiceImpl implements TransactionsService {
    private static final int MIN_SIZE_EXTERNAL_ID = 32;
    private final EntityManagerFactory emf;

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
            debit.setTransactionStatus(TransactionStatus.BLOCKED);
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
        var availableBalance = accountEntity.getBlockedBalance();
        var newValue = availableBalance.getValue().add(arguments.amount());
        availableBalance.setValue(newValue);
        var externalId = processExternalId(arguments.externalId());
        var credit = new CreditEntity();
        credit.setAccountEntity(accountEntity);
        credit.setAmount(arguments.amount());
        credit.setTransactionStatus(TransactionStatus.BLOCKED);
        credit.setExternalId(externalId);
        em.persist(availableBalance);
        em.persist(credit);
        return new CreateCreditResult(credit.getId(), credit.getId(), credit.getAmount(), credit.getExternalId());
    }

    @Override
    public void updateTransactionsStatus(UpdateTransactionStatusArguments arguments) {
        if (arguments.transactions().isEmpty()) throw new BadArgumentsException();
        var externalSet = arguments.transactions()
                .stream()
                .peek(item -> {
                    if (!item.transactionStatus().equals(TransactionStatus.BLOCKED)) throw new BadArgumentsException();
                })
                .map(UpdateTransactionStatusItemArguments::externalId)
                .collect(Collectors.toSet());
        if (externalSet.size() != arguments.transactions().size()) throw new BadArgumentsException();
        var transactionsMap = arguments.transactions()
                .stream()
                .collect(Collectors.toMap(UpdateTransactionStatusItemArguments::externalId, Function.identity()));
        var debitExternalIds = transactionsMap.values()
                .stream()
                .filter(item -> item.transactionType().equals(TransactionType.DEBIT))
                .map(UpdateTransactionStatusItemArguments::externalId)
                .toList();
        var creditExternalIds = transactionsMap.values()
                .stream()
                .filter(item -> item.transactionType().equals(TransactionType.CREDIT))
                .map(UpdateTransactionStatusItemArguments::externalId)
                .toList();
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            var accountEntity = em.createQuery("select a from AccountEntity a where a.id = :accountId",AccountEntity.class)
                    .setParameter("accountId", arguments.accountId())
                    .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                    .getSingleResult();
            if (accountEntity == null)
                throw new BadArgumentsException();
            var creditEntities = em.createQuery("select c from CreditEntity c where c.externalId in :externalIds and c.accountEntity.id = :accountId", CreditEntity.class)
                    .setParameter("externalIds", creditExternalIds)
                    .setParameter("accountId", arguments.accountId())
                    .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                    .getResultList()
                    .stream()
                    .collect(Collectors.toMap(CreditEntity::getExternalId, Function.identity()));
            if (creditExternalIds.size() != creditEntities.size()) throw new BadArgumentsException();
            var debitEntities = em.createQuery("select d from DebitEntity d where d.externalId in :externalIds and d.accountEntity.id = :accountId", DebitEntity.class)
                    .setParameter("externalIds", debitExternalIds)
                    .setParameter("accountId", arguments.accountId())
                    .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                    .getResultList()
                    .stream()
                    .collect(Collectors.toMap(DebitEntity::getExternalId, Function.identity()));
            if (debitExternalIds.size() != debitEntities.size()) throw new BadArgumentsException();
            for (var currentTransaction: transactionsMap.values()) {
                var transactionStatus = currentTransaction.transactionStatus();
                var transactionType = currentTransaction.transactionType();
                var foundDebitEntity = debitEntities.get(currentTransaction.externalId());
                var foundCreditEntity = creditEntities.get(currentTransaction.externalId());
                Transaction transaction = null;
                if (transactionType.equals(TransactionType.CREDIT)) {
                    transaction = foundCreditEntity;
                } else if (transactionType.equals(TransactionType.DEBIT)) {
                    transaction = foundDebitEntity;
                }
                if (transaction == null) throw new BadArgumentsException();
                switch (transactionStatus) {
                    case DECLINED -> transaction.decline();
                    case APPROVED -> transaction.approve();
                    default -> throw new IllegalArgumentException();
                }
            }
            for (var entity: creditEntities.values()) {
                em.persist(entity);
            }
            for (var entity: debitEntities.values()) {
                em.persist(entity);
            }
            for (var entity: accountEntity.getBalances()) {
                em.persist(entity);
            }
            em.persist(accountEntity);
            em.getTransaction().commit();
        } catch (Exception exception) {
            if (em != null && em.getTransaction().isActive()) em.getTransaction().rollback();
            throw exception;
        } finally {
            if (em != null) em.close();
        }
    }

    private String processExternalId(String externalId) {
        var value = externalId.trim();
        if (value.length() < MIN_SIZE_EXTERNAL_ID) throw new BadArgumentsException(KnownDescriptions.INVALID_EXTERNAL_ID_LENGTH);
        return value;
    }
}
