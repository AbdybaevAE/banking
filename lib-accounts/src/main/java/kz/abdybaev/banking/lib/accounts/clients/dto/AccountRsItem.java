package kz.abdybaev.banking.lib.accounts.clients.dto;

import kz.abdybaev.banking.lib.accounts.domain.AccountType;
import kz.abdybaev.banking.lib.cardssystem.dto.BalanceItemDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AccountRsItem {
    private Long accountId;
    private AccountType accountType;
    private List<BalanceItemDto> balances;
    private Long userId;
}
