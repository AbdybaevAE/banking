package kz.abdybaev.banking.app.cardssystem.services.dto;

import kz.abdybaev.banking.lib.cardssystem.dto.BalanceItemDto;

import java.util.List;

public record CreateCardArgs(Long userId, List<BalanceItemDto> balances) {
}