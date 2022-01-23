package kz.abdybaev.banking.app.cardssystem.repositories;

import kz.abdybaev.banking.app.cardssystem.entities.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceEntity, Long> {
}
