package kz.abdybaev.banking.app.accountssystem.services.dto;

import kz.abdybaev.banking.lib.accounts.domain.AccountType;

import java.util.Set;

public record GetAccountItemRes(
        Long accountId,
        Set<BalanceRes> balances,
        Long userId,
        AccountType accountType
) {
}
