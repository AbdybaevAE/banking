package kz.abdybaev.banking.app.accountssystem.services.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateDebitArgs(
        Long accountId,
        BigDecimal amount,
        LocalDateTime time) {
}
