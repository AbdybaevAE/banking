package kz.abdybaev.banking.app.accountssystem.services.dto;

import kz.abdybaev.banking.lib.common.domain.BalanceKind;

import java.math.BigDecimal;

public record BalanceRes(Long balanceId, BalanceKind balanceKind, BigDecimal balanceAmount) {
}
