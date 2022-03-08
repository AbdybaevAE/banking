package kz.abdybaev.banking.app.cardssystem.services;

import kz.abdybaev.banking.app.cardssystem.entities.BalanceEntity;
import kz.abdybaev.banking.app.cardssystem.entities.CardEntity;
import kz.abdybaev.banking.app.cardssystem.repositories.BalanceRepository;
import kz.abdybaev.banking.app.cardssystem.repositories.CardsRepository;
import kz.abdybaev.banking.app.cardssystem.services.dto.BalanceRes;
import kz.abdybaev.banking.app.cardssystem.services.dto.CreateCardArgs;
import kz.abdybaev.banking.app.cardssystem.services.dto.CreateCardRes;
import kz.abdybaev.banking.app.cardssystem.services.dto.GetCardItemRes;
import kz.abdybaev.banking.lib.common.domain.BalanceKind;
import kz.abdybaev.banking.lib.common.dto.CreateBalanceRequest;
import kz.abdybaev.banking.lib.common.exceptions.BadArgumentsException;
import kz.abdybaev.banking.lib.common.operation.KnownDescriptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements CardsService {
    private final CardsRepository cardsRepository;
    private final BalanceRepository balanceRepository;

    @Override
    public CreateCardRes createCard(CreateCardArgs args) {
        var balanceKinds = args.balances().stream().map(CreateBalanceRequest::getKind).collect(Collectors.toSet());
        if (balanceKinds.size() != args.balances().size())
            throw new BadArgumentsException(KnownDescriptions.PROVIDE_DEFINED_BALANCES);
        var notProvidedBalancesStream = Arrays.stream(BalanceKind.values())
                .filter(Predicate.not(balanceKinds::contains))
                .map(kind -> new CreateBalanceRequest(kind, BigDecimal.ZERO));

        var cardEntity = new CardEntity();
        cardEntity.setUserId(args.userId());

        var balances = Stream.concat(notProvidedBalancesStream, args.balances().stream())
                .map(balanceItemDto -> {
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
