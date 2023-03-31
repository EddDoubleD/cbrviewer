package com.eddoubled.cbrviewer.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.*;

@UtilityClass
@Slf4j
public class XMLParser {

    public static <T> T deserialize(String xml, Class<T> clazz) throws JAXBException {
        try (InputStream is = new ByteArrayInputStream(xml.getBytes())) {
            return deserialize(is, clazz);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    public static <T> T deserialize(InputStream xml, Class<T> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Object result = context.createUnmarshaller().unmarshal(xml);
        return clazz.equals(result.getClass()) ? (T) result : null;
    }
}
