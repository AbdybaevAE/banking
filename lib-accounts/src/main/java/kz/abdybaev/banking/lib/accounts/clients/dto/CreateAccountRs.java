package kz.abdybaev.banking.lib.accounts.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAccountRs {
    private final Long accountId;
}
