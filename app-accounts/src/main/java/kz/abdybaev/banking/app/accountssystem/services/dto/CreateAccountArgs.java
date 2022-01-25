package kz.abdybaev.banking.app.accountssystem.services.dto;

import kz.abdybaev.banking.lib.accounts.domain.AccountType;
import kz.abdybaev.banking.lib.cardssystem.dto.BalanceItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public record CreateAccountArgs(Long userId,
                                List<BalanceItemDto> balances,
                                AccountType accountType) {
}
