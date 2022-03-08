package kz.abdybaev.banking.app.accountssystem.services.dto;

import kz.abdybaev.banking.lib.accounts.domain.AccountType;
import kz.abdybaev.banking.lib.common.dto.BalanceRs;

import java.util.List;

public record AccountResult(Long accountId, AccountType accountType, List<BalanceRs> balances) {
}
