package kz.abdybaev.banking.app.accountssystem.services.dto;

import kz.abdybaev.banking.lib.accounts.domain.AccountType;
import kz.abdybaev.banking.lib.common.dto.CreateBalanceRq;

import java.util.List;

public record CreateAccountArgs(Long userId,
                                List<CreateBalanceRq> balances,
                                AccountType accountType) {
}
