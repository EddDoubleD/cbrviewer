package com.eddoubled.cbrviewer.utils;

import lombok.experimental.UtilityClass;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import java.io.IOException;

@UtilityClass
public class HttpUtils {

    public static void execute(ClassicHttpRequest request, HttpClientResponseHandler<?> handler) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            client.execute(request, handler);
        }
    }
}
