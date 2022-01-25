package kz.abdybaev.banking.lib.accounts.clients.dto;

import kz.abdybaev.banking.lib.accounts.domain.AccountType;
import kz.abdybaev.banking.lib.cardssystem.dto.BalanceItemDto;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateAccountsRq {
    @NotNull
    private Long userId;

    @NotEmpty
    @Valid
    private List<BalanceItemDto> balances;

    @NotEmpty
    private AccountType accountType;
}
