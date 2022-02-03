package kz.abdybaev.banking.lib.common.exceptions;

import kz.abdybaev.banking.lib.common.operation.KnownDescriptions;
import kz.abdybaev.banking.lib.common.operation.StatusCode;

public class InsufficentFundsException extends GenericException{
    public InsufficentFundsException() {
        super(StatusCode.INSUFFICIENT_FUNDS, KnownDescriptions.INSUFFICIENT_FUNDS);
    }
}
