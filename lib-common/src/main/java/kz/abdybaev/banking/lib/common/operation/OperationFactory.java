package kz.abdybaev.banking.lib.common.operation;

public class OperationFactory {
    private static final OperationStatus okOperation = new OperationStatus(StatusCode.OK, KnownDescriptions.OK_MESSAGE);
    public static OperationStatus ok() {
        return okOperation;
    }
}
