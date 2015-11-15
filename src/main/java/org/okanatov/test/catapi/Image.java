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

public class Image {
    private Url url;

    public static Image createFromResponse(String response) throws ImageException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(response)));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath path = xPathFactory.newXPath();
            Url url = Url.createFromString(path.evaluate("/response/data/images/image/url", doc));
            return new Image(url);
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            throw new ImageException(e.getMessage());
        }
    }

    public Url getUrl() {
        return url;
    }

    private Image(Url url) {
        this.url = url;
    }
}
