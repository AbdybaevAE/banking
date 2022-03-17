package kz.abdybaev.banking.app.accountssystem.domain;

/**
 * Main interface that need's to be implemented in order to apply operations.
 */
public interface Transaction {
    void decline();
    void approve();
}
