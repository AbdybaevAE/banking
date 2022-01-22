package kz.abdybaev.banking.app.cardssystem.entities;

import kz.abdybaev.banking.lib.cardssystem.domain.BalanceKind;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Table(name = "BALANCE")
@Entity
public class BalanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BALANCE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_ID")
    private CardEntity cardEntity;

    @Column(name = "BALANCE_VALUE", nullable = false)
    private BigDecimal value;

    @Column(name = "BALANCE_KIND",nullable = false)
    @Enumerated(EnumType.STRING)
    private BalanceKind balanceKind;
}
