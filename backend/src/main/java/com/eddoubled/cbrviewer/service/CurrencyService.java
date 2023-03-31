package com.eddoubled.cbrviewer.service;

import com.eddoubled.cbrviewer.model.jaxb2.Valuta;
import com.eddoubled.cbrviewer.repository.CurrencyRepository;
import com.eddoubled.cbrviewer.utils.HttpUtils;
import com.eddoubled.cbrviewer.utils.XMLParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для инициализации справочника валют
 */

@Slf4j
@Component
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    private final URI url;


    public CurrencyService(URI url) {
        this.url = url;
    }


    /**
     * Загружает справочник валют с сайта ЦБ РФ и сохраняет его в БД
     * @throws IOException сетевые проблемы
     */
    public void uploadCurrencyToDb() throws IOException {
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
        //В ответе отсутствует рубль
        Valuta.Item rub = new Valuta.Item();
        rub.setID("R643");
        rub.setName("Российский рубль");
        rub.setNominal(1L);
        rub.setEngName("Russian Ruble");
        rub.setParentCode("R643");
        currency.add(rub);

        currencyRepository.saveAll(currency);
        log.info("{} currency success uploaded", currency.size());
    }

    /**
     * Очищает справочник валюты
     */
    public void clearAll() {
        currencyRepository.deleteAll();
    }

    /**
     * Возвращает все записи курсов валют
     * @return все курсы валюты из справочника
     */
    public List<Valuta.Item> getCurrency() {
        return currencyRepository.findAll();
    }

    /**
     * Возвращает запись о валюте из справочника по идентификатору
     * @param id идентификатор валюты
     * @return описание валюты
     */
    public Optional<Valuta.Item> findById(String id) {
        return currencyRepository.findById(id);
    }

    /**
     * Возвращает запись о валюте из справочника по наименованию
     * @param name наименование валюты
     * @return описание валюты
     */
    public Optional<Valuta.Item> findByName(String name) {
        return currencyRepository.findByName(name);
    }
}
