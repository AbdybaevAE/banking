package kz.abdybaev.banking.lib.accounts.clients;

import kz.abdybaev.banking.lib.accounts.clients.dto.CreateAccountResponse;
import kz.abdybaev.banking.lib.accounts.clients.dto.CreateAccountRequest;
import org.springframework.stereotype.Component;

@Component
public class AccountsClientImpl implements AccountsClient{
    @Override
    public CreateAccountResponse createAccountRq(CreateAccountRequest request) {
        return null;
    }
}
