package kz.abdybaev.banking.lib.common.controlleradvice.base;

import kz.abdybaev.banking.lib.common.operation.KnownDescriptions;
import kz.abdybaev.banking.lib.common.operation.Operation;
import kz.abdybaev.banking.lib.common.operation.OperationFactory;
import kz.abdybaev.banking.lib.common.operation.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class BaseControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handle(MethodArgumentNotValidException exception, WebRequest request) {
        var bodyMap = buildErrorMap(exception.getFieldErrors(), request);
        return new ResponseEntity<>(bodyMap, HttpStatus.BAD_REQUEST);
    }
    private Map<String, String> buildErrorMap(List<FieldError> errorFields, WebRequest request) {
        var ret = new HashMap<String, String>();
        ret.put("path", request.getContextPath());
        ret.put("statusCode", StatusCode.BAD_REQUEST.name());
        ret.put("statusDescription", KnownDescriptions.BAD_ARGUMENTS);
        ret.put("errors", errorFields
                .stream()
                .map(item -> "Error on field: " + item.getField() + " with message: " + item.getDefaultMessage())
                        .collect(Collectors.joining("; ")));
        return ret;
    }

}
