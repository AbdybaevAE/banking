package kz.abdybaev.banking.app.accountssystem.services.converters;

import kz.abdybaev.banking.app.accountssystem.domain.Account;
import kz.abdybaev.banking.app.accountssystem.entities.AccountEntity;
import kz.abdybaev.banking.app.accountssystem.services.dto.CreateAccountArguments;
import kz.abdybaev.banking.app.accountssystem.services.dto.CreateAccountResult;
import kz.abdybaev.banking.app.accountssystem.services.dto.GetAccountItemResult;
import kz.abdybaev.banking.lib.accounts.clients.dto.CreateAccountRequest;
import kz.abdybaev.banking.lib.accounts.clients.dto.CreateAccountResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AccountsConverter {
    private final BalanceConverter balanceConverter;

    public GetAccountItemResult convert(AccountEntity entity) {
        return new GetAccountItemResult(
                entity.getId(),
                entity.getBalances().stream().map(balanceConverter::toBalanceRes).collect(Collectors.toSet()),
                entity.getUserId(),
                entity.getAccountType());
    }
    public Account toAccount(AccountEntity entity) {
        return new Account(
                entity.getId(),
                entity.getUserId(),
                entity.getAccountType(),
                entity.getBalances().stream().map(balanceConverter::toBalanceRes).collect(Collectors.toList())
        );
    }
    public CreateAccountArguments toCreateAccountArguments(CreateAccountRequest request) {
        return new CreateAccountArguments(request.getUserId(), request.getBalances(), request.getAccountType());
    }
    public CreateAccountResponse toCreateAccountResponse(CreateAccountResult result) {
        return new CreateAccountResponse(result.accountId());
    }
}
