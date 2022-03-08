package kz.abdybaev.banking.lib.accounts.clients.dto;

import kz.abdybaev.banking.lib.accounts.domain.AccountType;
import kz.abdybaev.banking.lib.common.dto.CreateBalanceRequest;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateAccountRequest {
    @NotNull
    private Long userId;

    @Valid
    @NotEmpty
    private List<CreateBalanceRequest> balances;

    @NotNull
    private AccountType accountType;
}
