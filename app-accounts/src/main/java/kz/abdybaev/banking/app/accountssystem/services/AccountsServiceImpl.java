package kz.abdybaev.banking.app.accountssystem.services;

import kz.abdybaev.banking.app.accountssystem.repositories.AccountsRepository;
import kz.abdybaev.banking.app.accountssystem.services.dto.AccountRes;
import kz.abdybaev.banking.app.accountssystem.services.dto.CreateAccountArgs;
import kz.abdybaev.banking.app.accountssystem.services.dto.CreateAccountRes;
import kz.abdybaev.banking.app.accountssystem.services.dto.SearchAccountsArgs;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {
    private final AccountsRepository accountsRepository;

    @Override
    public CreateAccountRes createAccount(CreateAccountArgs args) {
        return null;
    }

    @Override
    public List<AccountRes> searchAccounts(SearchAccountsArgs args) {
        return null;
    }
}
