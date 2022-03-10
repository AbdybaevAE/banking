package kz.abdybaev.banking.lib.accounts.clients.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CreateCreditResponse {
    @NotNull
    private Long accountId;

    @NotNull
    private Long creditId;

    @NotNull
    private BigDecimal creditAmount;

    @NotEmpty
    private String externalId;
}
