package org.okanatov.test.catapi;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class RealHttpClient implements HttpClient {
    @Override
    public String get(String string) throws HttpClientException {
        try {
            URL url = new URL(string);
            Scanner in = new Scanner(url.openStream());
            String response = "";

            while (in.hasNextLine())
                response += in.nextLine();

            return response;
        } catch (IOException e) {
            throw new HttpClientException(e.getMessage());
        }
    }
}
