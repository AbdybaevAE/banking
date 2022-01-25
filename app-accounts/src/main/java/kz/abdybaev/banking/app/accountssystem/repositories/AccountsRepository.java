package kz.abdybaev.banking.app.accountssystem.repositories;

import kz.abdybaev.banking.app.accountssystem.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<AccountEntity, Long> {
}
