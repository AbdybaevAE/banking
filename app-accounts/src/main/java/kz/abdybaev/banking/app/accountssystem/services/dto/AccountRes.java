package kz.abdybaev.banking.app.accountssystem.services.dto;

import kz.abdybaev.banking.lib.accounts.domain.AccountType;
import kz.abdybaev.banking.lib.cardssystem.dto.BalanceItemDto;

import java.util.List;

public record AccountRes(Long accountId, AccountType accountType, List<BalanceItemDto> balances) {
}
