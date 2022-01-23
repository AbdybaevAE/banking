package kz.abdybaev.banking.app.transfers.repositories;

import kz.abdybaev.banking.app.transfers.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<TransactionEntity, Long> {
}
