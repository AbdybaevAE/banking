package kz.abdybaev.banking.app.accountssystem.services.dto;

import kz.abdybaev.banking.lib.accounts.domain.TransactionStatus;
import kz.abdybaev.banking.lib.accounts.domain.TransactionType;

public record UpdateTransactionStatusItemArguments(String externalId, TransactionStatus transactionStatus, TransactionType transactionType) {
}
