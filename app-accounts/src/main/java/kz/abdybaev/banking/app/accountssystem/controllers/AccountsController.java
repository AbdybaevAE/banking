package kz.abdybaev.banking.app.accountssystem.controllers;

import kz.abdybaev.banking.app.accountssystem.services.AccountsService;
import kz.abdybaev.banking.app.accountssystem.services.dto.CreateAccountArgs;
import kz.abdybaev.banking.lib.accounts.clients.dto.CreateAccountRs;
import kz.abdybaev.banking.lib.accounts.clients.dto.CreateAccountsRq;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("accounts")
@AllArgsConstructor
public class AccountsController {
    private final AccountsService accountsService;
    @PostMapping
    public CreateAccountRs createAccount(
            @Valid @RequestBody CreateAccountsRq request
    )  {
        var args = new CreateAccountArgs(request.getUserId(), request.getBalances(), request.getAccountType());
        var res = accountsService.createAccount(args);
        return new CreateAccountRs(res.accountId());
    }
}
