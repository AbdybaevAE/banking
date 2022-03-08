package kz.abdybaev.banking.lib.accounts.clients.dto;

import kz.abdybaev.banking.lib.common.dto.OperationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAccountResponse extends OperationResponse {
    private final Long accountId;
}
