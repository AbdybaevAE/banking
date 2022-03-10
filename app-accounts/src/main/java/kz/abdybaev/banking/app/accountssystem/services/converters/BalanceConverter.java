package kz.abdybaev.banking.app.accountssystem.services.converters;

import kz.abdybaev.banking.app.accountssystem.entities.BalanceEntity;
import kz.abdybaev.banking.app.accountssystem.services.dto.BalanceResult;
import kz.abdybaev.banking.lib.common.dto.BalanceRs;
import org.springframework.stereotype.Component;

@Component
public class BalanceConverter {
    public BalanceResult toBalanceResult(BalanceEntity balanceEntity) {
        return new BalanceResult(balanceEntity.getId(), balanceEntity.getBalanceKind(), balanceEntity.getValue());
    }
    public BalanceRs toBalanceResponse(BalanceResult res) {
        return new BalanceRs(res.balanceId(), res.balanceKind(), res.balanceAmount());
    }
}
