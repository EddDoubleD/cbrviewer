package com.eddoubled.cbrviewer.config;

import com.eddoubled.cbrviewer.service.CurrencyService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.net.URISyntaxException;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Configuration
@ConfigurationProperties(prefix = "cbr")
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
public class Config {
    String url;

    @Bean
    public CurrencyService currencyService() throws URISyntaxException {
        return new CurrencyService(url);
    }
}
