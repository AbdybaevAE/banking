package kz.abdybaev.banking.lib.transfers.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class CardToCardRq {
    @NotNull
    private Long fromCardId;
    @NotNull
    private Long toCardId;
    @Positive
    private BigDecimal amount;
}
