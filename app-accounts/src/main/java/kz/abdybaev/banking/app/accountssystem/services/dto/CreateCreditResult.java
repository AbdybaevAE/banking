package kz.abdybaev.banking.app.accountssystem.services.dto;

import java.math.BigDecimal;

public record CreateCreditResult(
        Long accountId,
        Long creditId,
        BigDecimal creditAmount,
        String externalId

) {
}
