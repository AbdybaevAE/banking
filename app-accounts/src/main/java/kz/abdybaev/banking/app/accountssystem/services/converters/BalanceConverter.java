package kz.abdybaev.banking.app.accountssystem.services.converters;

import kz.abdybaev.banking.app.accountssystem.entities.BalanceEntity;
import kz.abdybaev.banking.app.accountssystem.services.dto.BalanceRes;
import kz.abdybaev.banking.lib.common.dto.BalanceRs;
import org.springframework.stereotype.Component;

@Component
public class BalanceConverter {
    public BalanceRes toBalanceRes(BalanceEntity balanceEntity) {
        return new BalanceRes(balanceEntity.getId(), balanceEntity.getBalanceKind(), balanceEntity.getValue());
    }
    public BalanceRs toBalanceRs(BalanceRes res) {
        return new BalanceRs(res.balanceId(), res.balanceKind(), res.balanceAmount());
    }
}
