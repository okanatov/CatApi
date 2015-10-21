package org.okanatov.test.catapi;

public class RealCatApi implements CatApi {
    private HttpClient client;
    private XmlParser parser;

    public RealCatApi(HttpClient client, XmlParser parser) {
        this.client = client;
        this.parser = parser;
    }

    public String getRandomImage() {
        try {
            String responseXml = client.get("http://thecatapi.com/api/images/get?format=xml&type=jpg");
            return parser.get(responseXml);
        } catch (HttpClientException | XmlParserException e) {
            return "http://cdn.my-cool-website.com/default.jpg";
        }
    }
}
