package kz.abdybaev.banking.app.accountssystem.services;

import kz.abdybaev.banking.app.accountssystem.services.dto.*;

import java.util.List;

/**
 * Accounts service
 */
public interface AccountsService {
    CreateAccountRes createAccount(CreateAccountArgs args);

    List<AccountRes> searchAccounts(SearchAccountsArgs args);

    CreateDebitRes createDebit(CreateDebitArgs args);

    CreateCreditRes createCredit(CreateCreditArgs args);

    List<GetAccountItemRes> getAccounts();
}
