package kz.abdybaev.banking.lib.accounts.clients.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateCreditRequest {
    @NotNull
    @Positive
    private BigDecimal amount;
    @NotNull
    private LocalDateTime time;
}
