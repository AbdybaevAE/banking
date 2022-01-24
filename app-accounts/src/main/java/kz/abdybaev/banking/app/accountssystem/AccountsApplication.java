package kz.abdybaev.banking.app.accountssystem;

import kz.abdybaev.banking.app.accountssystem.config.AppConfig;
import kz.abdybaev.banking.lib.common.exceptions.BaseControllerAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AppConfig.class, BaseControllerAdvice.class})
public class AccountsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
}
