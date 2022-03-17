package kz.abdybaev.banking.app.accountssystem.entities;

import kz.abdybaev.banking.lib.common.domain.BalanceKind;
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
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private AccountEntity accountEntity;

    @Column(name = "BALANCE_VALUE", nullable = false)
    private BigDecimal value;

    @Column(name = "BALANCE_KIND", nullable = false)
    @Enumerated(EnumType.STRING)
    private BalanceKind balanceKind;

    public void subtract(BigDecimal operationValue) {
        this.value = this.value.subtract(operationValue);
    }
    public void add(BigDecimal operationValue) {
        this.value = this.value.add(operationValue);
    }
}
