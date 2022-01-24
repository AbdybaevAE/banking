package kz.abdybaev.banking.lib.cardssystem.dto;

import kz.abdybaev.banking.lib.cardssystem.domain.BalanceKind;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceItemDto {
    @NotNull
    private BalanceKind kind;
    @NotNull
    @PositiveOrZero
    private BigDecimal amount;
}
