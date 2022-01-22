package kz.abdybaev.banking.app.cardssystem.services.dto;

import kz.abdybaev.banking.lib.cardssystem.domain.BalanceKind;

import java.math.BigDecimal;

public record BalanceRes(BalanceKind balanceKind, BigDecimal value) {
}
