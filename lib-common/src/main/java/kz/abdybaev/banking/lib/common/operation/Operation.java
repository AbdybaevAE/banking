package kz.abdybaev.banking.lib.common.operation;

import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Default Operation result
 */
public interface Operation {
    StatusCode statusCode();

    String statusDescription();

    default String statusMessage() {
        return "";
    }
}
