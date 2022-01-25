package kz.abdybaev.banking.lib.accounts.clients.dto;

import kz.abdybaev.banking.lib.accounts.domain.AccountType;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class SearchAccountRq {
    private List<Long> userIds = Collections.emptyList();
    private List<AccountType> types = Collections.emptyList();
    private Long page = 0L;
}
