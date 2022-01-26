package kz.abdybaev.banking.lib.cardssystem.dto;

import kz.abdybaev.banking.lib.common.dto.BalanceRs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCardItemRs {
    private Long userId;
    private Long cardId;
    private Set<BalanceRs> balances;
}
