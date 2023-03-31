package com.eddoubled.cbrviewer.service;

import com.eddoubled.cbrviewer.model.jaxb2.ValCurs;
import com.eddoubled.cbrviewer.repository.ConvertRepository;
import com.eddoubled.cbrviewer.utils.DateUtils;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class ConvertService {
    @Autowired
    private ConvertRepository convertRepository;
    private final URI url;

    public ConvertService(URI url) {
        this.url = url;
    }


    /**
     * Получение с сайта ЦБРФ всех курсов валют на текущую дату
     * @throws IOException сетевая ошибка
     */
    public void uploadAllRatesToDb() throws IOException {
        // формируем запрос
        ClassicHttpRequest get = ClassicRequestBuilder.get()
                .addParameter("date_req", DateUtils.format(new Date()))
                .setUri(url)
                .build();

        final List<ValCurs.Valute> values = new ArrayList<>();
        HttpUtils.execute(get, response -> {
            try {
                ValCurs valCurs = XMLParser.deserialize(response.getEntity().getContent(), ValCurs.class);
                if (valCurs != null) {
                    values.addAll(valCurs.getValute());
                }
            } catch (JAXBException e) {
                log.error(e.getMessage(), e);
            }

            return null;
        });
        // В ответе отсутствует рубль
        ValCurs.Valute rub = new ValCurs.Valute();
        rub.setName("Российский рубль");
        rub.setCharCode("RUB");
        rub.setNominal(1L);
        rub.setValue("1");
        rub.setNumCode(643);
        rub.setID("R643");
        values.add(rub);

        convertRepository.saveAll(values);
    }

    /**
     * Очистка списка курсов валют
     */
    public void clearAll() {
        convertRepository.deleteAll();
    }

    /**
     * Получение курсов для всех валют
     */
    public List<ValCurs.Valute> getAllCurrenciesRates() {
        return convertRepository.findAll();
    }

    /**
     * Поиск курса валюты
     * @param id идентификатор валюты
     * @return курс запрашиваемой валюты
     */
    public Optional<ValCurs.Valute> findById(String id) {
        return convertRepository.findById(id);
    }
}
