package kz.abdybaev.banking.lib.transfers.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class InternalAcc2AccRequest {
    @NotNull
    private Long fromAccountId;
    @NotNull
    private Long toAccountId;
    @Positive
    private BigDecimal amount;
}
