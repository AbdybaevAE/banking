package kz.abdybaev.banking.app.cardssystem.services.dto;


import kz.abdybaev.banking.lib.common.dto.CreateBalanceRq;

import java.util.List;

public record CreateCardArgs(Long userId, List<CreateBalanceRq> balances) {
}
