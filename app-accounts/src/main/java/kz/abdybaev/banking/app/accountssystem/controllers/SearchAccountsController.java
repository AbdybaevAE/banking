package kz.abdybaev.banking.app.accountssystem.controllers;

import kz.abdybaev.banking.app.accountssystem.services.AccountsService;
import kz.abdybaev.banking.app.accountssystem.services.dto.SearchAccountsArgs;
import kz.abdybaev.banking.lib.accounts.clients.dto.AccountRsItem;
import kz.abdybaev.banking.lib.accounts.clients.dto.SearchAccountRq;
import kz.abdybaev.banking.lib.common.dto.SearchRs;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("search-accounts")
@AllArgsConstructor
public class SearchAccountsController {
    private final AccountsService accountsService;
    @PostMapping
    public SearchRs<AccountRsItem> search(
            @Valid @RequestBody SearchAccountRq request
            ) {
        var args = new SearchAccountsArgs(request.getUserIds(), request.getTypes(), request.getPage());
        var res = accountsService.searchAccounts(args);
        return new SearchRs<>(res.stream().map(item -> AccountRsItem.builder()
                .accountId(item.accountId())
                .accountType(item.accountType())
                .balances(item.balances())
                .build()).collect(Collectors.toList()));
    }
}
