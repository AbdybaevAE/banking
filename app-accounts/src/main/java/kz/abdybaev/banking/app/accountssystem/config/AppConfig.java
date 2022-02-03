package kz.abdybaev.banking.app.accountssystem.config;

import kz.abdybaev.banking.lib.common.controlleradvice.BaseControllerAdviceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import({
        BaseControllerAdviceConfig.class
})
public class AppConfig {
}
