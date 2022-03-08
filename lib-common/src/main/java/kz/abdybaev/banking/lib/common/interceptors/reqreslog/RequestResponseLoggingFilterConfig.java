package kz.abdybaev.banking.lib.common.interceptors.reqreslog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestResponseLoggingFilterConfig {
    @Bean
    public CommonsRequestLoggingFilter loggingFilter() {
        var filter = new CommonsRequestLoggingFilter();
        filter.setIncludeHeaders(true);
        filter.setIncludePayload(true);
        filter.setBeforeMessagePrefix("Before");
        filter.setAfterMessagePrefix("After");
        return filter;
    }
}
