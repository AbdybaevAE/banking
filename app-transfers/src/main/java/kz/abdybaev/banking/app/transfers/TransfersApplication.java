package kz.abdybaev.banking.app.transfers;

import kz.abdybaev.banking.app.transfers.config.AppConfig;
import kz.abdybaev.banking.lib.common.controlleradvice.base.BaseControllerAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AppConfig.class, BaseControllerAdvice.class})
public class TransfersApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransfersApplication.class, args);
    }
}
