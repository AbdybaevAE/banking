package kz.abdybaev.banking.app.cardssystem.services;

import kz.abdybaev.banking.app.cardssystem.services.dto.CreateCardArgs;
import kz.abdybaev.banking.app.cardssystem.services.dto.CreateCardRes;
import kz.abdybaev.banking.app.cardssystem.services.dto.GetCardItemRes;

import java.util.Set;


public interface CardsService {
    CreateCardRes createCard(CreateCardArgs args);

    Set<GetCardItemRes> getCards();
}
