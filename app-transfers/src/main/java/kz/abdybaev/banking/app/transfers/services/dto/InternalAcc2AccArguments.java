package kz.abdybaev.banking.app.transfers.services.dto;

import java.math.BigDecimal;

public record InternalAcc2AccArguments(
        Long fromAccountId,
        Long toAccountId,
        BigDecimal amount
) {
}
