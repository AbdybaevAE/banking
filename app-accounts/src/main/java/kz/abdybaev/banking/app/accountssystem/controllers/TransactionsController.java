package kz.abdybaev.banking.app.accountssystem.controllers;

import kz.abdybaev.banking.app.accountssystem.services.TransactionsService;
import kz.abdybaev.banking.app.accountssystem.services.converters.AccountsConverter;
import kz.abdybaev.banking.lib.accounts.clients.dto.*;
import kz.abdybaev.banking.lib.common.dto.OperationResponse;
import kz.abdybaev.banking.lib.common.factories.operation.OperationFactory;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("accounts/{accountId}/transactions")
@AllArgsConstructor
public class TransactionsController {
    private final TransactionsService transactionsService;
    private final AccountsConverter accountsConverter;
    private final OperationFactory operationFactory;

    @PostMapping("debits")
    public CreateDebitResponse createDebit(
            @PathVariable Long accountId,
            @Valid @RequestBody CreateDebitRequest request
    ) {
        var arguments = accountsConverter.toCreateDebitArguments(accountId, request);
        var result = transactionsService.createDebit(arguments);
        return accountsConverter.toCreateDebitResponse(result);
    }

    @PostMapping("credits")
    public CreateCreditResponse createCredit(
            @PathVariable Long accountId,
            @Valid @RequestBody CreateCreditRequest request
    ) {
        var arguments = accountsConverter.toCreateCreditArguments(accountId, request);
        var result = transactionsService.createCredit(arguments);
        return accountsConverter.toCreateCreditResponse(result);
    }
    @PutMapping
    public OperationResponse updateTransactionStatus(
            @PathVariable Long accountId,
            @Valid @RequestBody UpdateTransactionStatusRequest request
            ) {
        var arguments = accountsConverter.toUpdateTransactionStatusArguments(accountId, request);
        transactionsService.updateTransactionsStatus(arguments);
        return operationFactory.okResponse();
    }

}
