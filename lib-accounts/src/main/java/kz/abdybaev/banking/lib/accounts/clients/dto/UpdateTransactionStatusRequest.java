package kz.abdybaev.banking.lib.accounts.clients.dto;

import kz.abdybaev.banking.lib.accounts.domain.TransactionStatus;
import kz.abdybaev.banking.lib.accounts.domain.TransactionType;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdateTransactionStatusRequest {
    @Data
    public static class TransactionItem {
        @NotEmpty
        private String externalId;
        @NotNull
        private TransactionType transactionType;
        @NotNull
        private TransactionStatus transactionStatus;
    }
    @NotEmpty
    @Max(2)
    private List<TransactionItem> transactions;
}
