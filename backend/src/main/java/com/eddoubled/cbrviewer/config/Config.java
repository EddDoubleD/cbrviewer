package com.eddoubled.cbrviewer.config;

import com.eddoubled.cbrviewer.service.ConvertService;
import com.eddoubled.cbrviewer.service.CurrencyService;
import com.eddoubled.cbrviewer.service.RateDynamicService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.net.URI;
import java.net.URISyntaxException;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Configuration
@ConfigurationProperties(prefix = "cbr")
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
public class Config {

    private static final String XML_VAL = "XML_val.asp";
    private static final String XML_DAILY = "XML_daily.asp";
    private static final String XML_DYNAMIC = "XML_dynamic.asp";

    String url;

    @Bean
    public CurrencyService currencyService() throws URISyntaxException {
        return new CurrencyService(new URI(url + XML_VAL));
    }

    @Bean
    public ConvertService convertService() throws URISyntaxException {
        return new ConvertService(new URI(url + XML_DAILY));
    }

    @Bean
    public RateDynamicService rateDynamicService() throws URISyntaxException {
        return new RateDynamicService(currencyService(), new URI(url + XML_DYNAMIC));
    }
}
