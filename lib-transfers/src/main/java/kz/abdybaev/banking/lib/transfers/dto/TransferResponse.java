package kz.abdybaev.banking.lib.transfers.dto;

import kz.abdybaev.banking.lib.common.dto.OperationResponse;
import kz.abdybaev.banking.lib.transfers.domain.TransferStatus;
import lombok.Getter;

@Getter
public class TransferResponse extends OperationResponse {
    private TransferStatus transferStatus;
}
