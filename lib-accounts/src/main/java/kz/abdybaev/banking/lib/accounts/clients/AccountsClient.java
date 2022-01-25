package kz.abdybaev.banking.lib.accounts.clients;

import kz.abdybaev.banking.lib.accounts.clients.dto.CreateAccountRs;
import kz.abdybaev.banking.lib.accounts.clients.dto.CreateAccountsRq;

public interface AccountsClient {
    CreateAccountRs createAccountRq(CreateAccountsRq request);


}
