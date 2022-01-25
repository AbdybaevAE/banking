package kz.abdybaev.banking.lib.accounts.clients;

import kz.abdybaev.banking.lib.accounts.clients.dto.CreateAccountRs;
import kz.abdybaev.banking.lib.accounts.clients.dto.CreateAccountsRq;
import org.springframework.stereotype.Component;

@Component
public class AccountsClientImpl implements AccountsClient{
    @Override
    public CreateAccountRs createAccountRq(CreateAccountsRq request) {
        return null;
    }
}
