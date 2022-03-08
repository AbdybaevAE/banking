package kz.abdybaev.banking.app.accountssystem.services.dto;

import kz.abdybaev.banking.lib.accounts.domain.AccountType;
import kz.abdybaev.banking.lib.common.dto.CreateBalanceRequest;

import java.util.List;

public record CreateAccountArguments(Long userId,
                                     List<CreateBalanceRequest> balances,
                                     AccountType accountType) {
}
