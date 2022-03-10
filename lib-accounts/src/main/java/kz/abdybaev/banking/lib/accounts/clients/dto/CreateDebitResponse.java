package kz.abdybaev.banking.lib.accounts.clients.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateDebitResponse {
    private Long debitId;
    private Long accountId;
    private BigDecimal debitAmount;
    private String transactionExternalId;
}
