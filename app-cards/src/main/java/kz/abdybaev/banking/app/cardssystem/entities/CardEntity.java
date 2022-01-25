package kz.abdybaev.banking.app.cardssystem.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "CARD")
public class CardEntity {
    @Id
    @Column(name = "CARD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cardEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BalanceEntity> balances = new HashSet<>();

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Long accountId;
}
