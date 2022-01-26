package kz.abdybaev.banking.app.accountssystem.entities;

import kz.abdybaev.banking.lib.accounts.domain.AccountType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {
    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "ACCOUNT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<BalanceEntity> balances = new HashSet<>();
}
