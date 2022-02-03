package kz.abdybaev.banking.app.accountssystem.domain;

import kz.abdybaev.banking.app.accountssystem.entities.AccountEntity;
import kz.abdybaev.banking.app.accountssystem.services.dto.BalanceRes;
import kz.abdybaev.banking.lib.accounts.domain.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class Account {
    private final Long accountId;
    private final Long userId;
    private final AccountType accountType;
    private final List<BalanceRes> balances;
}
