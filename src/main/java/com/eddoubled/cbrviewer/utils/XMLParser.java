package com.eddoubled.cbrviewer.utils;

import lombok.experimental.UtilityClass;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.InputStream;

@UtilityClass
public class XMLParser {
    public static <T> T deserialize(String xml, Class<T> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Object result = context.createUnmarshaller().unmarshal(new File(xml));
        return clazz.equals(result.getClass()) ? (T) result : null;
    }

    public static <T> T deserialize(InputStream xml, Class<T> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Object result = context.createUnmarshaller().unmarshal(xml);
        return clazz.equals(result.getClass()) ? (T) result : null;
    }
}
