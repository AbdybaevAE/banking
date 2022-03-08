package kz.abdybaev.banking.app.accountssystem.controllers;

import kz.abdybaev.banking.app.accountssystem.services.AccountsService;
import kz.abdybaev.banking.app.accountssystem.services.dto.SearchAccountsArguments;
import kz.abdybaev.banking.lib.accounts.clients.dto.AccountResponse;
import kz.abdybaev.banking.lib.accounts.clients.dto.SearchAccountRq;
import kz.abdybaev.banking.lib.common.dto.SearchResponse;
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
    public SearchResponse<AccountResponse> search(
            @Valid @RequestBody SearchAccountRq request
    ) {
        var args = new SearchAccountsArguments(request.getUserIds(), request.getTypes(), request.getPage());
        var res = accountsService.searchAccounts(args);
        return new SearchResponse<>(res.stream().map(item -> AccountResponse.builder()
                        .accountId(item.accountId())
                        .accountType(item.accountType())
                        .balances(item.balances())
                        .build())
                .collect(Collectors.toList()));
    }
}
