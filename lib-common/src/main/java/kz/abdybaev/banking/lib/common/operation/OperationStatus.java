package kz.abdybaev.banking.lib.common.operation;

public class OperationStatus implements Operation{
    private final StatusCode statusCode;
    private final String statusDescription;
    public OperationStatus(StatusCode statusCode, String statusDescription) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }
    @Override
    public StatusCode statusCode() {
        return statusCode;
    }

    @Override
    public String statusDescription() {
        return statusDescription;
    }
}
