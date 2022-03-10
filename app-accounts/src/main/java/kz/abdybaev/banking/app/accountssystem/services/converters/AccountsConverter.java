package kz.abdybaev.banking.app.accountssystem.services.converters;

import kz.abdybaev.banking.app.accountssystem.domain.Account;
import kz.abdybaev.banking.app.accountssystem.entities.AccountEntity;
import kz.abdybaev.banking.app.accountssystem.services.dto.*;
import kz.abdybaev.banking.lib.accounts.clients.dto.*;
import kz.abdybaev.banking.lib.common.dto.SearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AccountsConverter {
    private final BalanceConverter balanceConverter;

    public GetAccountItemResult convert(AccountEntity entity) {
        return new GetAccountItemResult(
                entity.getId(),
                entity.getBalances().stream().map(balanceConverter::toBalanceResult).collect(Collectors.toSet()),
                entity.getUserId(),
                entity.getAccountType());
    }
    public CreateAccountArguments toCreateAccountArguments(CreateAccountRequest request) {
        return new CreateAccountArguments(request.getUserId(), request.getBalances(), request.getAccountType());
    }
    public CreateAccountResponse toCreateAccountResponse(CreateAccountResult result) {
        return new CreateAccountResponse(result.accountId());
    }
    public SearchResponse<AccountResponse> toGetAccountsResponse(List<GetAccountItemResult> records) {
        return new SearchResponse<>(
                records.stream()
                        .map(item ->
                                AccountResponse.builder()
                                        .userId(item.userId())
                                        .accountId(item.accountId())
                                        .accountType(item.accountType())
                                        .balances(item.balances().stream().map(balanceConverter::toBalanceResponse).collect(Collectors.toList()))
                                        .build())
                        .collect(Collectors.toList()));
    }
    public CreateDebitArguments toCreateDebitArguments(Long accountId, CreateDebitRequest request) {
        return new CreateDebitArguments(
                accountId,
                request.getAmount(),
                request.getTime(),
                request.getTransactionExternalId()
        );
    }
    public CreateDebitResponse toCreateDebitResponse(CreateDebitResult result) {
        return CreateDebitResponse.builder()
                .accountId(result.accountId())
                .debitAmount(result.debitAmount())
                .debitId(result.debitId())
                .transactionExternalId(result.transactionExternalId())
                .build();
    }
    public CreateCreditArguments toCreateCreditArguments(Long accountId, CreateCreditRequest request) {
        return new CreateCreditArguments(accountId, request.getAmount(), request.getTime(), request.getExternalId());
    }
    public CreateCreditResponse toCreateCreditResponse(CreateCreditResult result) {
        return CreateCreditResponse.builder()
                .accountId(result.accountId())
                .creditId(result.creditId())
                .creditAmount(result.creditAmount())
                .externalId(result.externalId())
                .build();
    }
}
