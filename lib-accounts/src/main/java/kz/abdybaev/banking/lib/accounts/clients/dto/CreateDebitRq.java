package kz.abdybaev.banking.lib.accounts.clients.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateDebitRq {
    @NotNull
    private Long accountId;
    @NotNull
    private LocalDateTime time;
    @NotNull
    @Positive
    private BigDecimal amount;
}
