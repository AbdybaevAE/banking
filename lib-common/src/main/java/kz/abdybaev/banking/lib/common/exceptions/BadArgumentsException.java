package kz.abdybaev.banking.lib.common.exceptions;

import kz.abdybaev.banking.lib.common.operation.KnownDescriptions;
import kz.abdybaev.banking.lib.common.operation.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadArgumentsException extends GenericException{
    public BadArgumentsException(StatusCode statusCode, String statusDescription) {
        super(statusCode, statusDescription);
    }
    public BadArgumentsException(String statusDescription) {
        super(StatusCode.BAD_REQUEST, statusDescription);
    }
    public BadArgumentsException() {
        super(StatusCode.BAD_REQUEST, KnownDescriptions.BAD_ARGUMENTS);
    }
}
