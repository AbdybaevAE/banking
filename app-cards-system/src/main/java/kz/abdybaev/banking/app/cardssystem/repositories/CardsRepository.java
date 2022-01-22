package kz.abdybaev.banking.app.cardssystem.repositories;

import kz.abdybaev.banking.app.cardssystem.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends JpaRepository<CardEntity, Long> {
}
