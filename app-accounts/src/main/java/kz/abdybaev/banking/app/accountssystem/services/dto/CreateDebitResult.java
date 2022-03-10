package kz.abdybaev.banking.app.accountssystem.services.dto;

import java.math.BigDecimal;

public record CreateDebitResult(
        Long debitId,
        Long accountId,
        BigDecimal debitAmount,
        String transactionExternalId
) {
}
