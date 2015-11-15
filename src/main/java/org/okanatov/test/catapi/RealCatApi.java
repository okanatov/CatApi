package org.okanatov.test.catapi;

public class RealCatApi implements CatApi {
    private HttpClient client;

    public RealCatApi(HttpClient client) {
        this.client = client;
    }

    @Override
    public Url getRandomImage() {
        try {
            String responseXml = client.get("http://thecatapi.com/api/images/get?format=xml&type=jpg");
            Image image = Image.createFromResponse(responseXml);
            return image.getUrl();
        } catch (HttpClientException | ImageException e) {
            return Url.createFromString("http://cdn.my-cool-website.com/default.jpg");
        }
    }
}
