package kz.abdybaev.banking.lib.accounts.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class CreateDebitRs {
    private Long debitId;
}
