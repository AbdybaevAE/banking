package kz.abdybaev.banking.app.accountssystem.controllers;

import kz.abdybaev.banking.app.accountssystem.services.AccountsService;
import kz.abdybaev.banking.app.accountssystem.services.converters.BalanceConverter;
import kz.abdybaev.banking.app.accountssystem.services.dto.CreateAccountArgs;
import kz.abdybaev.banking.app.accountssystem.services.dto.CreateCreditArgs;
import kz.abdybaev.banking.app.accountssystem.services.dto.CreateDebitArgs;
import kz.abdybaev.banking.lib.accounts.clients.dto.*;
import kz.abdybaev.banking.lib.common.dto.SearchRs;
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

    @PostMapping
    public CreateAccountRs createAccount(@Valid @RequestBody CreateAccountsRq request) {
        var args = new CreateAccountArgs(request.getUserId(), request.getBalances(), request.getAccountType());
        var res = accountsService.createAccount(args);
        return new CreateAccountRs(res.accountId());
    }

    @GetMapping
    public SearchRs<AccountRs> getAccounts() {
        var res = accountsService.getAccounts();
        return new SearchRs<>(
                res.stream()
                        .map(item ->
                                AccountRs.builder()
                                        .userId(item.userId())
                                        .accountId(item.accountId())
                                        .accountType(item.accountType())
                                        .balances(item.balances().stream().map(balanceConverter::toBalanceRs).collect(Collectors.toList()))
                                        .build())
                        .collect(Collectors.toList()));
    }
    @PostMapping("{accountId}/transactions/debits")
    public CreateDebitRs createDebit(
            @PathVariable Long accountId,
            @Valid @RequestBody CreateDebitRq request
    ) {
        var args = new CreateDebitArgs(
                accountId,
                request.getAmount(),
                request.getTime()
        );
        var res = accountsService.createDebit(args);
        return new CreateDebitRs(res.debitId());
    }

    @PostMapping("{accountId}/transactions/credits")
    public CreateCreditRs createDebit(
            @PathVariable Long accountId,
            @Valid @RequestBody CreateCreditRq request
    ) {
        var args = new CreateCreditArgs(
                accountId,
                request.getAmount(),
                request.getTime()
        );
        var res = accountsService.createCredit(args);
        return new CreateCreditRs(res.creditId());
    }
}
