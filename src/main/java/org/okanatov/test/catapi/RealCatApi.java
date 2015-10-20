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
import java.io.IOException;
import java.io.StringReader;

public class RealCatApi implements CatApi {
    private HttpClient client;

    public RealCatApi(HttpClient client) {
        this.client = client;
    }

    public String getRandomImage() {
        String responseXml;
        try {
            responseXml = client.get("http://thecatapi.com/api/images/get?format=xml&type=jpg");
        } catch (HttpClientException e) {
            return "http://cdn.my-cool-website.com/default.jpg";
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(responseXml)));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath path = xPathFactory.newXPath();

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
    }
}
