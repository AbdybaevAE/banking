package kz.abdybaev.banking.lib.common.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchResponse<T> extends OperationResponse {
    private final Integer numRecords;
    private final List<T> records;

    public SearchResponse(List<T> records) {
        this.numRecords = records.size();
        this.records = records;
    }
}
