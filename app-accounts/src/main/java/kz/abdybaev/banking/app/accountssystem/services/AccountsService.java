package kz.abdybaev.banking.app.accountssystem.services;

import kz.abdybaev.banking.app.accountssystem.services.dto.AccountRes;
import kz.abdybaev.banking.app.accountssystem.services.dto.CreateAccountArgs;
import kz.abdybaev.banking.app.accountssystem.services.dto.CreateAccountRes;
import kz.abdybaev.banking.app.accountssystem.services.dto.SearchAccountsArgs;

import java.util.List;

public interface AccountsService {
    CreateAccountRes createAccount(CreateAccountArgs args);
    List<AccountRes> searchAccounts(SearchAccountsArgs args);
}
