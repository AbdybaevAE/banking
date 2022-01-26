package kz.abdybaev.banking.app.accountssystem.controllers;

import kz.abdybaev.banking.app.accountssystem.services.AccountsService;
import kz.abdybaev.banking.app.accountssystem.services.dto.CreateCreditArgs;
import kz.abdybaev.banking.app.accountssystem.services.dto.CreateDebitArgs;
import kz.abdybaev.banking.lib.accounts.clients.dto.CreateCreditRs;
import kz.abdybaev.banking.lib.accounts.clients.dto.CreateDebitRq;
import kz.abdybaev.banking.lib.accounts.clients.dto.CreateDebitRs;
import kz.abdybaev.banking.lib.accounts.clients.dto.CreateCreditRq;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/{accountId}/transactions")
@AllArgsConstructor
public class AccountTransactions {
    private final AccountsService accountsService;

    @PostMapping("debits")
    public CreateDebitRs createDebit(
            @Valid @RequestBody CreateDebitRq request
    ) {
        var args = new CreateDebitArgs(
                request.getAccountId(),
                request.getAmount(),
                request.getTime()
        );
        var res = accountsService.createDebit(args);
        return new CreateDebitRs(res.debitId());
    }

    @PostMapping("credits")
    public CreateCreditRs createDebit(
            @Valid @RequestBody CreateCreditRq request
    ) {
        var args = new CreateCreditArgs(
                request.getAccountId(),
                request.getAmount(),
                request.getTime()
        );
        var res = accountsService.createCredit(args);
        return new CreateCreditRs(res.creditId());
    }
}
