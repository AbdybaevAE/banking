package kz.abdybaev.banking.lib.accounts.clients.dto;

import kz.abdybaev.banking.lib.accounts.domain.AccountType;
import kz.abdybaev.banking.lib.common.dto.CreateBalanceRq;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateAccountsRq {
    @NotNull
    private Long userId;

    @Valid
    private List<CreateBalanceRq> balances;

    @NotNull
    private AccountType accountType;
}
