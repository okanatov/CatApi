package org.okanatov.test.catapi;

import java.io.IOException;

public class HttpClientException extends IOException {
    public HttpClientException(String message) {
        super(message);
    }
}
