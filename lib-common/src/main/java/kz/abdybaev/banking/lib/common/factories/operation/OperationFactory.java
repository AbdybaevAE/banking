package kz.abdybaev.banking.lib.common.factories.operation;

import kz.abdybaev.banking.lib.common.dto.OperationResponse;
import kz.abdybaev.banking.lib.common.operation.KnownDescriptions;
import kz.abdybaev.banking.lib.common.operation.OperationStatus;
import kz.abdybaev.banking.lib.common.operation.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class OperationFactory {
    private static final OperationStatus okOperation = new OperationStatus(StatusCode.OK, KnownDescriptions.OK_MESSAGE);
    private static final OperationResponse okOperationResponse = new OperationResponse(StatusCode.OK.name(), KnownDescriptions.OK_MESSAGE);
    public OperationStatus ok() {
        return okOperation;
    }
    public OperationStatus badArguments(String statusDescription) {
        return new OperationStatus(StatusCode.BAD_REQUEST, statusDescription);
    }
    public OperationResponse okResponse() {
        return okOperationResponse;
    }
}
