package kz.abdybaev.banking.lib.accounts.clients;

import kz.abdybaev.banking.lib.accounts.clients.dto.CreateAccountResponse;
import kz.abdybaev.banking.lib.accounts.clients.dto.CreateAccountRequest;

public interface AccountsClient {
    CreateAccountResponse createAccountRq(CreateAccountRequest request);


}
