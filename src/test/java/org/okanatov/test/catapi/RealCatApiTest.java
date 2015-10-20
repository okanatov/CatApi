package org.okanatov.test.catapi;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RealCatApiTest {
    private CatApi api;
    private HttpClient client;

    @Before
    public void setUp() throws Exception {
        System.setProperty("http.proxyHost", "proxy.te.mera.ru");
        System.setProperty("http.proxyPort", "8080");

        client = new RealHttpClient();
        api = new RealCatApi(client);
    }

    @Test
    public void testFetchesARandomUrlOfACatGif() {
        String url = api.getRandomImage();
        assertTrue(url.startsWith("http://"));
        assertTrue(url.endsWith(".jpg"));
    }
}
