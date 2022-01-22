package kz.abdybaev.banking.app.cardssystem;

import kz.abdybaev.banking.app.cardssystem.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        AppConfig.class
})
public class CardsSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(CardsSystemApplication.class, args);
    }
}
