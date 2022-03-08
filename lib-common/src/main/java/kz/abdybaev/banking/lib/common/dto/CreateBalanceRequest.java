package kz.abdybaev.banking.lib.common.dto;

import kz.abdybaev.banking.lib.common.domain.BalanceKind;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateBalanceRequest {
    @NotNull
    private BalanceKind kind;
    @NotNull
    @PositiveOrZero
    private BigDecimal amount;
}
