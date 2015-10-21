package org.okanatov.test.catapi;

import java.io.*;
import java.util.Scanner;

public class CachedCatApi implements CatApi {

    public String getRandomImage() {
        File file = new File("./random");

        if (!file.exists() || (System.currentTimeMillis() - file.lastModified() > 3000)) {
            HttpClient client = new RealHttpClient();
            XmlParser parser = new RealXmlParser();
            CatApi api = new RealCatApi(client, parser);
            String url = api.getRandomImage();
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(url);
            } catch (FileNotFoundException e) {
                return "http://cdn.my-cool-website.com/default.jpg";
            }
            return url;
        } else {
            try (Scanner in = new Scanner(file)) {
                return in.nextLine();
            } catch (FileNotFoundException e) {
                return "http://cdn.my-cool-website.com/default.jpg";
            }
        }
    }
}
