package kz.abdybaev.banking.app.accountssystem.entities;

import kz.abdybaev.banking.app.accountssystem.domain.Transaction;
import kz.abdybaev.banking.lib.accounts.domain.TransactionStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "DEBIT")
@Getter
@Setter
public class DebitEntity implements Transaction {
    @Id
    @Column(name = "DEBIT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DEBIT_AMOUNT", nullable = false)
    private BigDecimal amount;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private AccountEntity accountEntity;

    @Column(name = "DEBIT_EXTERNAL_ID", nullable = false, unique = true)
    private String externalId;

    @Column(name = "DEBIT_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Override
    public void decline() {
        this.transactionStatus = TransactionStatus.DECLINED;
        this.accountEntity.getBlockedBalance().subtract(amount);
    }

    @Override
    public void approve() {
        this.transactionStatus = TransactionStatus.APPROVED;
        this.accountEntity.getBlockedBalance().subtract(amount);
        this.accountEntity.getAvailableBalance().subtract(amount);
    }
}
