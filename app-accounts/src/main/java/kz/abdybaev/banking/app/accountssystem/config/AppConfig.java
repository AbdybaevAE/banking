package kz.abdybaev.banking.app.accountssystem.config;

import kz.abdybaev.banking.lib.common.controlleradvice.BaseControllerAdviceConfig;
import kz.abdybaev.banking.lib.common.interceptors.reqreslog.RequestResponseLoggingFilterConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import({
        BaseControllerAdviceConfig.class,
        RequestResponseLoggingFilterConfig.class
})
public class AppConfig {
}
