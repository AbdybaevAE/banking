package kz.abdybaev.banking.lib.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ArrayResponse<T> extends OperationResponse {
    private final List<T> records;
}
