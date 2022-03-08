package kz.abdybaev.banking.app.transfers.controllers;

import kz.abdybaev.banking.app.transfers.services.TransfersService;
import kz.abdybaev.banking.app.transfers.services.dto.CardToCardArgs;
import kz.abdybaev.banking.lib.common.operation.OperationFactory;
import kz.abdybaev.banking.lib.common.operation.OperationStatus;
import kz.abdybaev.banking.lib.transfers.dto.CardToCardRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("transfers")
@AllArgsConstructor
public class TransfersController {
    private final TransfersService transfersService;
    @PostMapping("card-to-card")
    public OperationStatus cardToCard(@Valid @RequestBody CardToCardRequest request) {
        var args = new CardToCardArgs(request.getFromCardId(), request.getToCardId(), request.getAmount());
        transfersService.cardToCard(args);
        return OperationFactory.ok();
    }
}
