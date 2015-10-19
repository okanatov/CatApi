package org.okanatov.test.catapi;

import org.junit.Test;

import static org.junit.Assert.*;

public class CatApiTest {
    @Test
    public void testFetchesARandomUrlOfACatGif() {
        String url = CatApi.getRandomImage();
        assertTrue(url.startsWith("http://"));
        assertTrue(url.endsWith(".jpg"));
    }

    @Test
    public void testCachesARandomCatGifUrlFor3Seconds() throws InterruptedException {
        String firstUrl = CatApi.getRandomImage();
        Thread.sleep(2000);
        String secondUrl = CatApi.getRandomImage();
        Thread.sleep(2000);
        String thirdUrl = CatApi.getRandomImage();

        assertEquals(firstUrl, secondUrl);
        assertNotEquals(secondUrl, thirdUrl);
    }
}