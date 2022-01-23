package kz.abdybaev.banking.lib.cardssystem.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateCardRq {
    @NotNull
    private Long userId;

    @NotEmpty @Valid
    private List<BalanceItemDto> balances;
}
