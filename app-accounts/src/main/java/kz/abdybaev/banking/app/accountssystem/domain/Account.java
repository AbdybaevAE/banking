package kz.abdybaev.banking.app.accountssystem.domain;

import kz.abdybaev.banking.app.accountssystem.services.dto.BalanceResult;
import kz.abdybaev.banking.lib.accounts.domain.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Account {
    private final Long accountId;
    private final Long userId;
    private final AccountType accountType;
    private final List<BalanceResult> balances;
}
