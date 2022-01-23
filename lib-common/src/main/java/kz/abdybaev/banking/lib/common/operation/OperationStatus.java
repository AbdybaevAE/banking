package kz.abdybaev.banking.lib.common.operation;

public record OperationStatus(StatusCode statusCode,
                              String statusDescription) implements Operation {
}
