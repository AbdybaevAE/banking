package kz.abdybaev.banking.cardssystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cards")
public class CardsControllers {
    @GetMapping
    public String getAllCards() {
        return "ok";
    }
}
