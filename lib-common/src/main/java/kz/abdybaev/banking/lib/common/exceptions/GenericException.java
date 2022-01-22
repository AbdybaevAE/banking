package kz.abdybaev.banking.lib.common.exceptions;

import kz.abdybaev.banking.lib.common.operation.StatusCode;
import lombok.Getter;

@Getter
public class GenericException extends RuntimeException{
    private final StatusCode statusCode;
    private final String statusDescription;
    public GenericException(StatusCode statusCode, String statusDescription) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }
}
