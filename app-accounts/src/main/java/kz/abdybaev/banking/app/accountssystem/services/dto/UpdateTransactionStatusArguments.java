package kz.abdybaev.banking.app.accountssystem.services.dto;

import java.util.List;

public record UpdateTransactionStatusArguments (
        Long accountId,
        List<UpdateTransactionStatusItemArguments> transactions
){
}
