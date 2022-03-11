package kz.abdybaev.banking.lib.accounts.clients.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateDebitRequest {
    @NotNull
    private LocalDateTime time;
    @NotNull
    @Positive
    private BigDecimal amount;

    @NotEmpty
    private String externalId;
}
