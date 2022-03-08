package kz.abdybaev.banking.lib.common.dto;

import kz.abdybaev.banking.lib.common.operation.KnownDescriptions;
import kz.abdybaev.banking.lib.common.operation.StatusCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationResponse {
    private String statusCode = StatusCode.OK.name();
    private String statusDescription = KnownDescriptions.OK_MESSAGE;
}
