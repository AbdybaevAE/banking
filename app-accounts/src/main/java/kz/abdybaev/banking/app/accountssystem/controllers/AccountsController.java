package kz.abdybaev.banking.app.accountssystem.controllers;

import kz.abdybaev.banking.app.accountssystem.services.AccountsService;
import kz.abdybaev.banking.app.accountssystem.services.converters.AccountsConverter;
import kz.abdybaev.banking.app.accountssystem.services.converters.BalanceConverter;
import kz.abdybaev.banking.app.accountssystem.services.dto.CreateCreditArguments;
import kz.abdybaev.banking.app.accountssystem.services.dto.CreateDebitArguments;
import kz.abdybaev.banking.lib.accounts.clients.dto.*;
import kz.abdybaev.banking.lib.common.dto.SearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("accounts")
@AllArgsConstructor
public class AccountsController {
    private final AccountsService accountsService;
    private final BalanceConverter balanceConverter;
    private final AccountsConverter accountsConverter;

    @PostMapping
    public CreateAccountResponse createAccount(@Valid @RequestBody CreateAccountRequest request) {
        var arguments = accountsConverter.toCreateAccountArguments(request);
        var result = accountsService.createAccount(arguments);
        return accountsConverter.toCreateAccountResponse(result);
    }

    @GetMapping
    public SearchResponse<AccountResponse> getAccounts() {
        var records = accountsService.getAccounts();
        return new SearchResponse<>(
                records.stream()
                        .map(item ->
                                AccountResponse.builder()
                                        .userId(item.userId())
                                        .accountId(item.accountId())
                                        .accountType(item.accountType())
                                        .balances(item.balances().stream().map(balanceConverter::toBalanceRs).collect(Collectors.toList()))
                                        .build())
                        .collect(Collectors.toList()));
    }
    @PostMapping("{accountId}/transactions/debits")
    public CreateDebitResponse createDebit(
            @PathVariable Long accountId,
            @Valid @RequestBody CreateDebitRequest request
    ) {
        var args = new CreateDebitArguments(
                accountId,
                request.getAmount(),
                request.getTime()
        );
        var res = accountsService.createDebit(args);
        return new CreateDebitResponse(res.debitId());
    }

    @PostMapping("{accountId}/transactions/credits")
    public CreateCreditResponse createDebit(
            @PathVariable Long accountId,
            @Valid @RequestBody CreateCreditRequest request
    ) {
        var args = new CreateCreditArguments(
                accountId,
                request.getAmount(),
                request.getTime()
        );
        var res = accountsService.createCredit(args);
        return new CreateCreditResponse(res.creditId());
    }
}
