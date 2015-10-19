package org.okanatov.test.catapi;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class CatApi {

    public static String getRandomImage() {
        File file = new File("./random");

        if (!file.exists() || (System.currentTimeMillis() - file.lastModified() > 3000)) {
            String responseXml = "";
            try {
                URL url = new URL("http://thecatapi.com/api/images/get?format=xml&type=jpg");
                Scanner in = new Scanner(url.openStream());

                while (in.hasNextLine()) {
                    responseXml += in.nextLine();
                }
            } catch (IOException e) {
                return "http://cdn.my-cool-website.com/default.jpg";
            }

            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(new StringReader(responseXml)));
                XPathFactory xPathFactory = XPathFactory.newInstance();
                XPath path = xPathFactory.newXPath();

                PrintWriter writer = new PrintWriter(file);
                writer.print(path.evaluate("/response/data/images/image/url", doc));
                writer.close();

                return path.evaluate("/response/data/images/image/url", doc);
            } catch (ParserConfigurationException e) {
                return "http://cdn.my-cool-website.com/default.jpg";
            } catch (SAXException e) {
                return "http://cdn.my-cool-website.com/default.jpg";
            } catch (IOException e) {
                return "http://cdn.my-cool-website.com/default.jpg";
            } catch (XPathExpressionException e) {
                return "http://cdn.my-cool-website.com/default.jpg";
            }
        } else {
            try {
                Scanner in = new Scanner(file);
                return in.nextLine();
            } catch (FileNotFoundException e) {
                return "http://cdn.my-cool-website.com/default.jpg";
            }
        }
    }
}
