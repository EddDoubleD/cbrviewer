package com.eddoubled.cbrviewer.service;

import com.eddoubled.cbrviewer.model.dto.response.dynamics.DynamicsResponse;
import com.eddoubled.cbrviewer.model.jaxb2.ValCursDynamics;
import com.eddoubled.cbrviewer.utils.DateUtils;
import com.eddoubled.cbrviewer.utils.HttpUtils;
import com.eddoubled.cbrviewer.utils.XMLParser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
@Slf4j
public class RateDynamicService {

    CurrencyService currencyService;

    URI url;

    public DynamicsResponse getDynamics(String from, String to, String id) throws IOException {
        ClassicHttpRequest get = ClassicRequestBuilder.get()
                .addParameter("date_req1", from)
                .addParameter("date_req2", to)
                .addParameter("VAL_NM_RQ", id)
                .setUri(url)
                .build();

        AtomicReference<ValCursDynamics> dynamics = new AtomicReference<>();
        HttpUtils.execute(get, response -> {
            try {
                String result = IOUtils.toString(response.getEntity().getContent(), response.getEntity().getContentEncoding());
                result = result.replaceAll("ValCurs", "ValCursDynamics");
                dynamics.set(XMLParser.deserialize(result, ValCursDynamics.class));
            } catch (JAXBException e) {
                log.error(e.getMessage(), e);
            }

            return null;
        });

        ValCursDynamics valCursDynamics = dynamics.get();
        final DynamicsResponse result = new DynamicsResponse(valCursDynamics.getName());
        valCursDynamics.getRecord()
                .forEach(record -> {
                    Date time = DateUtils.parseNormal(record.getDate());
                    if (time != null) {
                        result.addTimeRate(time.getTime(), new BigDecimal(record.getValue().replace(",", ".")));
                    }
                });

        return result;
    }
}
