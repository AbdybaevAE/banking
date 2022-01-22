package kz.abdybaev.banking.app.cardssystem.controllers;

import kz.abdybaev.banking.app.cardssystem.services.CardsService;
import kz.abdybaev.banking.app.cardssystem.services.dto.CreateCardArgs;
import kz.abdybaev.banking.lib.cardssystem.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cards")
@AllArgsConstructor
public class CardsControllers {
    private final CardsService cardsService;
    @GetMapping
    public GetCardsRs getAllCards() {
        var cards = cardsService.getCards()
                .stream()
                .map(item -> {
                    var balances = item.balances()
                            .stream()
                            .map(balanceRes -> new BalanceItemDto(balanceRes.balanceKind(), balanceRes.value()))
                            .collect(Collectors.toSet());
                    return new GetCardItemRs(item.cardId(), item.userId(), balances);
                })
                .collect(Collectors.toSet());
        return new GetCardsRs(cards);
    }
    @PostMapping
    public CreateCardRs createCard(
            @Valid  @RequestBody CreateCardRq request
            ) {
        var args = new CreateCardArgs(request.getUserId(), request.getBalances());
        var res = cardsService.createCard(args);
        return new CreateCardRs(res.cardId());
    }
}
