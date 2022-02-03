package kz.abdybaev.banking.lib.common.exceptions;

import kz.abdybaev.banking.lib.common.operation.KnownDescriptions;
import kz.abdybaev.banking.lib.common.operation.StatusCode;

public class AccountNotFound extends GenericException{
    public AccountNotFound() {
        super(StatusCode.ACCOUNT_NOT_FOUND, KnownDescriptions.ACCOUNT_NOT_FOUND);
    }
}
