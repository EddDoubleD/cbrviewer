package com.eddoubled.cbrviewer.service;

import com.eddoubled.cbrviewer.utils.HttpUtils;
import com.eddoubled.cbrviewer.utils.XMLParser;
import io.codejournal.maven.xsd2java.Valuta;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
/**
 * Сервис для инициализации справочника валют
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@Component
public class CurrencyService {
    private static final String PATH = "/scripts/XML_val.asp";

    URI url;

    public CurrencyService(String url) throws URISyntaxException {
        this.url = new URI(url + PATH);
    }


    public List<Valuta.Item> uploadCurrency() throws IOException {
        // формируем запрос
        ClassicHttpRequest get = ClassicRequestBuilder.get()
                .addParameter("d", "0")
                .setUri(url)
                .build();
        // Определяем результирующую выборку
        final List<Valuta.Item> currency = new ArrayList<>();
        HttpUtils.execute(get, response -> {
            try {
                Valuta valuta = XMLParser.deserialize(response.getEntity().getContent(), Valuta.class);
                if (valuta != null) {
                    currency.addAll(valuta.getItem());
                }
            } catch (JAXBException e) {
                log.error(e.getMessage(), e);
            }

            return null;
        });

        return currency;
    }
}
