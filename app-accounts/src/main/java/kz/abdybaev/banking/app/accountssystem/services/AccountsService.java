package kz.abdybaev.banking.app.accountssystem.services;

import kz.abdybaev.banking.app.accountssystem.services.dto.*;

import java.util.List;

/**
 * Accounts service
 */
public interface AccountsService {
    CreateAccountResult createAccount(CreateAccountArguments arguments);

    CreateDebitResult createDebit(CreateDebitArguments arguments);

    CreateCreditResult createCredit(CreateCreditArguments arguments);

    List<GetAccountItemResult> getAccounts(SearchAccountsArguments arguments);
}
