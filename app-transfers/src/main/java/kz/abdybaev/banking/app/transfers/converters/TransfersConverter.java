package kz.abdybaev.banking.app.transfers.converters;

import kz.abdybaev.banking.app.transfers.services.dto.InternalAcc2AccArguments;
import kz.abdybaev.banking.lib.transfers.dto.InternalAcc2AccRequest;
import org.springframework.stereotype.Component;

@Component
public class TransfersConverter {
    public InternalAcc2AccArguments toInternalAcc2AccArguments(InternalAcc2AccRequest request) {
        return new InternalAcc2AccArguments(
                request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmount()
        );
    }
}
