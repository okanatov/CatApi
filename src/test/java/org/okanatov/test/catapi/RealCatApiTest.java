package org.okanatov.test.catapi;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RealCatApiTest {
    private CatApi api;

    @Before
    public void setUp() throws Exception {
        System.setProperty("http.proxyHost", "proxy.te.mera.ru");
        System.setProperty("http.proxyPort", "8080");

        HttpClient client = new RealHttpClient();
        XmlParser parser = new RealXmlParser();
        api = new RealCatApi(client, parser);
    }

    @Test
    public void testFetchesARandomUrlOfACatGif() {
        Url url = api.getRandomImage();
        assertTrue(url.toString().startsWith("http://"));
        assertTrue(url.toString().endsWith(".jpg"));
    }
}
