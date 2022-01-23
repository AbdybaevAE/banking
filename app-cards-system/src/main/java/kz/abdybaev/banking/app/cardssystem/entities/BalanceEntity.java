package kz.abdybaev.banking.app.cardssystem.entities;

import kz.abdybaev.banking.lib.cardssystem.domain.BalanceKind;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Table(name = "BALANCE")
@Entity
public class BalanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BALANCE_ID")
    private Long id;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_ID")
    private CardEntity cardEntity;

    @Column(name = "BALANCE_VALUE", nullable = false)
    private BigDecimal value;

    @Column(name = "BALANCE_KIND",nullable = false)
    @Enumerated(EnumType.STRING)
    private BalanceKind balanceKind;
}
