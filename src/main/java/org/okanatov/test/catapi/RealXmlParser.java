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

public class RealXmlParser implements XmlParser {
    @Override
    public String get(String string) throws XmlParserException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(string)));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath path = xPathFactory.newXPath();
            return path.evaluate("/response/data/images/image/url", doc);
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            throw new XmlParserException(e.getMessage());
        }
    }
}
