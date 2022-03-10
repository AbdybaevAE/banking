package kz.abdybaev.banking.app.accountssystem.controllers;

import kz.abdybaev.banking.app.accountssystem.services.AccountsService;
import kz.abdybaev.banking.app.accountssystem.services.converters.AccountsConverter;
import kz.abdybaev.banking.app.accountssystem.services.converters.BalanceConverter;
import kz.abdybaev.banking.app.accountssystem.services.dto.CreateCreditArguments;
import kz.abdybaev.banking.app.accountssystem.services.dto.CreateDebitArguments;
import kz.abdybaev.banking.app.accountssystem.services.dto.SearchAccountsArguments;
import kz.abdybaev.banking.lib.accounts.clients.dto.*;
import kz.abdybaev.banking.lib.common.dto.SearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("accounts")
@AllArgsConstructor
public class AccountsController {
    private final AccountsService accountsService;
    private final AccountsConverter accountsConverter;

    @PostMapping
    public CreateAccountResponse createAccount(@Valid @RequestBody CreateAccountRequest request) {
        var arguments = accountsConverter.toCreateAccountArguments(request);
        var result = accountsService.createAccount(arguments);
        return accountsConverter.toCreateAccountResponse(result);
    }

    @GetMapping
    public SearchResponse<AccountResponse> getAccounts() {
        var arguments = new SearchAccountsArguments(null, null, null);
        var records = accountsService.getAccounts(arguments);
        return accountsConverter.toGetAccountsResponse(records);

    }
    @PostMapping("{accountId}/transactions/debits")
    public CreateDebitResponse createDebit(
            @PathVariable Long accountId,
            @Valid @RequestBody CreateDebitRequest request
    ) {
        var arguments = accountsConverter.toCreateDebitArguments(accountId, request);
        var result = accountsService.createDebit(arguments);
        return accountsConverter.toCreateDebitResponse(result);
    }

    @PostMapping("{accountId}/transactions/credits")
    public CreateCreditResponse createCredit(
            @PathVariable Long accountId,
            @Valid @RequestBody CreateCreditRequest request
    ) {
        var arguments = accountsConverter.toCreateCreditArguments(accountId, request);
        var result = accountsService.createCredit(arguments);
        return accountsConverter.toCreateCreditResponse(result);
    }
}
