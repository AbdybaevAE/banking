package kz.abdybaev.banking.app.accountssystem.services;

import kz.abdybaev.banking.app.accountssystem.services.dto.*;

public interface TransactionsService {
    CreateDebitResult createDebit(CreateDebitArguments arguments);

    CreateCreditResult createCredit(CreateCreditArguments arguments);

    void updateTransactionsStatus(UpdateTransactionStatusArguments arguments);
}
