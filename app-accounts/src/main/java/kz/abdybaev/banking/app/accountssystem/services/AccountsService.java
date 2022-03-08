package kz.abdybaev.banking.app.accountssystem.services;

import kz.abdybaev.banking.app.accountssystem.services.dto.*;

import java.util.List;

/**
 * Accounts service
 */
public interface AccountsService {
    CreateAccountResult createAccount(CreateAccountArguments args);

    List<AccountResult> searchAccounts(SearchAccountsArguments args);

    CreateDebitResult createDebit(CreateDebitArguments args);

    CreateCreditResult createCredit(CreateCreditArguments args);

    List<GetAccountItemResult> getAccounts();
}
