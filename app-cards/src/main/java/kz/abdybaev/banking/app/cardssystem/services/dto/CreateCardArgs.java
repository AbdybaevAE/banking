package kz.abdybaev.banking.app.cardssystem.services.dto;


import kz.abdybaev.banking.lib.common.dto.CreateBalanceRequest;

import java.util.List;

public record CreateCardArgs(Long userId, List<CreateBalanceRequest> balances) {
}
