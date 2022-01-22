package kz.abdybaev.banking.app.cardssystem.services;

import kz.abdybaev.banking.app.cardssystem.entities.BalanceEntity;
import kz.abdybaev.banking.app.cardssystem.entities.CardEntity;
import kz.abdybaev.banking.app.cardssystem.repositories.CardsRepository;
import kz.abdybaev.banking.app.cardssystem.services.dto.BalanceRes;
import kz.abdybaev.banking.app.cardssystem.services.dto.CreateCardArgs;
import kz.abdybaev.banking.app.cardssystem.services.dto.CreateCardRes;
import kz.abdybaev.banking.app.cardssystem.services.dto.GetCardItemRes;
import kz.abdybaev.banking.lib.cardssystem.dto.BalanceItemDto;
import kz.abdybaev.banking.lib.common.exceptionstus.BadArgumentsException;
import kz.abdybaev.banking.lib.common.operation.KnownDescriptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements CardsService {
    private final CardsRepository cardsRepository;

    @Override
    public CreateCardRes createCard(CreateCardArgs args) {
        var balanceKinds = args.balances().stream().map(BalanceItemDto::getKind).collect(Collectors.toSet());
        if (balanceKinds.size() != args.balances().size()) throw new BadArgumentsException(KnownDescriptions.PROVIDE_DEFINED_BALANCES);

        var cardEntity = new CardEntity();
        cardEntity.setUserId(args.userId());
        var balances = args.balances().stream().map(balanceItemDto -> {
            var balanceEntity = new BalanceEntity();
            balanceEntity.setCardEntity(cardEntity);
            balanceEntity.setBalanceKind(balanceItemDto.getKind());
            balanceEntity.setValue(balanceItemDto.getAmount());
            return balanceEntity;
        }).collect(Collectors.toSet());
        cardEntity.setBalances(balances);
        cardsRepository.save(cardEntity);
        return new CreateCardRes(cardEntity.getId());
    }

    @Override
    public Set<GetCardItemRes> getCards() {
        var cardsEntities = cardsRepository.findAll();
        return cardsEntities
                .stream()
                .map(cardEntity ->
                        new GetCardItemRes(cardEntity.getId(), cardEntity.getUserId(),
                                cardEntity.getBalances()
                                        .stream()
                                        .map(balanceEntity -> new BalanceRes(balanceEntity.getBalanceKind(), balanceEntity.getValue()))
                                        .collect(Collectors.toSet()))
                )
                .collect(Collectors.toSet());
    }
}
