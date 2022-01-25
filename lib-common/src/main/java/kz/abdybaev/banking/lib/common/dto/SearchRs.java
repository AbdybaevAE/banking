package kz.abdybaev.banking.lib.common.dto;

import kz.abdybaev.banking.lib.common.dto.OperationRs;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
public class SearchRs<T> extends OperationRs {
    private final Integer numRecords;
    private final List<T> records;
    public SearchRs(List<T> records) {
        this.numRecords = records.size();
        this.records = records;
    }
}
