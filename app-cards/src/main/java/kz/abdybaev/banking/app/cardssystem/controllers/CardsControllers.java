package kz.abdybaev.banking.app.cardssystem.controllers;

import kz.abdybaev.banking.app.cardssystem.services.CardsService;
import kz.abdybaev.banking.app.cardssystem.services.dto.CreateCardArgs;
import kz.abdybaev.banking.lib.cardssystem.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cards")
@AllArgsConstructor
public class CardsControllers {
    private final CardsService cardsService;

    @GetMapping
    public GetCardsRs getAllCards() {
        return null;
//        var cards = cardsService.getCards()
//                .stream()
//                .map(item -> {
//                    var balances = item.balances()
//                            .stream()
//                            .map(item ->  null)
//                            .collect(Collectors.toSet());
//                    return new GetCardItemRs(item.userId(), item.cardId(), balances);
//                })
//                .collect(Collectors.toSet());
//        return new GetCardsRs(cards);
    }

    @PostMapping
    public CreateCardRs createCard(
            @Valid @RequestBody CreateCardRq request
    ) {
        var args = new CreateCardArgs(request.getUserId(), request.getBalances());
        var res = cardsService.createCard(args);
        return new CreateCardRs(res.cardId());
    }
}
