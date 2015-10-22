package org.okanatov.test.catapi;

import java.io.*;
import java.util.Scanner;

public class CachedCatApi implements CatApi {

    @Override
    public Url getRandomImage() {
        File file = new File("./random");

        if (!file.exists() || (System.currentTimeMillis() - file.lastModified() > 3000)) {
            HttpClient client = new RealHttpClient();
            XmlParser parser = new RealXmlParser();
            CatApi api = new RealCatApi(client, parser);
            Url url = api.getRandomImage();
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(url);
            } catch (FileNotFoundException e) {
                return Url.createFromString("http://cdn.my-cool-website.com/default.jpg");
            }
            return url;
        } else {
            try (Scanner in = new Scanner(file)) {
                return Url.createFromString(in.nextLine());
            } catch (FileNotFoundException e) {
                return Url.createFromString("http://cdn.my-cool-website.com/default.jpg");
            }
        }
    }
}
