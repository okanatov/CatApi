package org.okanatov.test.catapi;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CachedCatApiTest {
    private CatApi api;

    @Before
    public void setUp() throws Exception {
        System.setProperty("http.proxyHost", "proxy.te.mera.ru");
        System.setProperty("http.proxyPort", "8080");

        api = new CachedCatApi();
    }

    @Test
    public void testFetchesARandomUrlOfACatGif() {
        String url = api.getRandomImage();
        assertTrue(url.startsWith("http://"));
        assertTrue(url.endsWith(".jpg"));
    }

    @Test
    public void testCachesARandomCatGifUrlFor3Seconds() throws InterruptedException {
        String firstUrl = api.getRandomImage();
        Thread.sleep(2000);
        String secondUrl = api.getRandomImage();
        Thread.sleep(2000);
        String thirdUrl = api.getRandomImage();

        assertEquals(firstUrl, secondUrl);
        assertNotEquals(secondUrl, thirdUrl);
    }
}