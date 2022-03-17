package kz.abdybaev.banking.app.transfers.controllers;

import kz.abdybaev.banking.app.transfers.converters.TransfersConverter;
import kz.abdybaev.banking.app.transfers.services.TransfersService;
import kz.abdybaev.banking.lib.common.dto.OperationResponse;
import kz.abdybaev.banking.lib.common.factories.operation.OperationFactory;
import kz.abdybaev.banking.lib.transfers.dto.InternalAcc2AccRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("transfers/internal")
@AllArgsConstructor
public class InternalTransfers {
    private final TransfersService transfersService;
    private final OperationFactory operationFactory;
    private final TransfersConverter transfersConverter;
    @PostMapping("acc2acc")
    public OperationResponse acc2acc(@Valid @RequestBody InternalAcc2AccRequest request) {
        var arguments = transfersConverter.toInternalAcc2AccArguments(request);
        transfersService.internalAcc2Acc(arguments);
        return null;
    }
}
