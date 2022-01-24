package kz.abdybaev.banking.app.cardssystem.services.dto;

import java.util.Set;

public record GetCardItemRes(Long cardId, Long userId, Set<BalanceRes> balances) {
}
