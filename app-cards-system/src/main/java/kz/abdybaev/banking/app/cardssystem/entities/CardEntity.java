package kz.abdybaev.banking.app.cardssystem.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "CARD")
public class CardEntity {
    @Id
    @Column(name = "CARD_ID")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cardEntity", cascade = CascadeType.ALL)
    private Set<BalanceEntity> balances;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

}
