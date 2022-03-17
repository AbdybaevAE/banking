package kz.abdybaev.banking.app.transfers.services;

import kz.abdybaev.banking.app.transfers.services.dto.InternalAcc2AccArguments;

public interface TransfersService {
    void internalAcc2Acc(InternalAcc2AccArguments argument);
}
