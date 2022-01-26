package kz.abdybaev.banking.lib.common.dto;

import kz.abdybaev.banking.lib.common.domain.BalanceKind;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class BalanceRs {
    private final Long id;
    private final BalanceKind kind;
    private final BigDecimal amount;
}
