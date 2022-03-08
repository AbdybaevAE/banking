package kz.abdybaev.banking.app.accountssystem.services.dto;

import kz.abdybaev.banking.lib.accounts.domain.AccountType;

import java.util.List;

public record SearchAccountsArguments(List<Long> userIds, List<AccountType> accountTypes, Long page){
}
