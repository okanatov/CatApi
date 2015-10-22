package org.okanatov.test.catapi;

public class RealCatApi implements CatApi {
    private HttpClient client;
    private XmlParser parser;

    public RealCatApi(HttpClient client, XmlParser parser) {
        this.client = client;
        this.parser = parser;
    }

    @Override
    public Url getRandomImage() {
        try {
            String responseXml = client.get("http://thecatapi.com/api/images/get?format=xml&type=jpg");
            return Url.createFromString(parser.get(responseXml));
        } catch (HttpClientException | XmlParserException e) {
            return Url.createFromString("http://cdn.my-cool-website.com/default.jpg");
        }
    }
}
