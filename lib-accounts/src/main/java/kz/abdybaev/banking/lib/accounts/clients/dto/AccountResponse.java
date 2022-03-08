package kz.abdybaev.banking.lib.accounts.clients.dto;

import kz.abdybaev.banking.lib.accounts.domain.AccountType;
import kz.abdybaev.banking.lib.common.dto.BalanceRs;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AccountResponse {
    private Long accountId;
    private AccountType accountType;
    private List<BalanceRs> balances;
    private Long userId;
}
