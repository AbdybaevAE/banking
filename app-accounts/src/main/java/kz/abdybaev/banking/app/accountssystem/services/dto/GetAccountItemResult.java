package kz.abdybaev.banking.app.accountssystem.services.dto;

import kz.abdybaev.banking.lib.accounts.domain.AccountType;

import java.util.Set;

public record GetAccountItemResult(
        Long accountId,
        Set<BalanceResult> balances,
        Long userId,
        AccountType accountType
) {
}
