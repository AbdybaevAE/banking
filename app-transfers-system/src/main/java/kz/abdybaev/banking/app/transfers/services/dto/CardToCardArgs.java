package kz.abdybaev.banking.app.transfers.services.dto;

import java.math.BigDecimal;

public record CardToCardArgs(
        Long fromCardId,
        Long toCardId,
        BigDecimal amount
) {
}
