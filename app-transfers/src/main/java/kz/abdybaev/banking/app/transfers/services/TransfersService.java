package kz.abdybaev.banking.app.transfers.services;

import kz.abdybaev.banking.app.transfers.services.dto.CardToCardArgs;

public interface TransfersService {
    void cardToCard(CardToCardArgs args);
}
