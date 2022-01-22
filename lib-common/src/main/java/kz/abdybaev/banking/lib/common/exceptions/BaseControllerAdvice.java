package kz.abdybaev.banking.lib.common.exceptions;

import kz.abdybaev.banking.lib.common.operation.Operation;
import kz.abdybaev.banking.lib.common.operation.OperationFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class BaseControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Operation handleMethodArgumentsNotValid(MethodArgumentNotValidException ex, WebRequest request) {
//        System.out.println(ex);
//        var res = ex.getMessage();
        return OperationFactory.badArguments("ASdas");
    }
}
