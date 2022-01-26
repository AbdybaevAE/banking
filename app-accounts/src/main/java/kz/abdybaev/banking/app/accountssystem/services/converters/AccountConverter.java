package kz.abdybaev.banking.app.accountssystem.services.converters;

import kz.abdybaev.banking.app.accountssystem.entities.AccountEntity;
import kz.abdybaev.banking.app.accountssystem.services.dto.GetAccountItemRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AccountConverter {
    private final BalanceConverter balanceConverter;

    public GetAccountItemRes convert(AccountEntity entity) {
        return new GetAccountItemRes(
                entity.getId(),
                entity.getBalances().stream().map(balanceConverter::toBalanceRes).collect(Collectors.toSet()),
                entity.getUserId(),
                entity.getAccountType());
    }
}
