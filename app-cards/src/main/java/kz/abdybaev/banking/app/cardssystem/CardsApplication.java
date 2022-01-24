package kz.abdybaev.banking.app.cardssystem;

import kz.abdybaev.banking.app.cardssystem.config.AppConfig;
import kz.abdybaev.banking.lib.common.exceptions.BaseControllerAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AppConfig.class, BaseControllerAdvice.class})
public class CardsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }
}
