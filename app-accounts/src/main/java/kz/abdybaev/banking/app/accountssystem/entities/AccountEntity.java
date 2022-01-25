package kz.abdybaev.banking.app.accountssystem.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {
    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
