package org.okanatov.test.catapi;

public class Url {
    private String url;

    public static Url createFromString(String string) {
        return new Url(string);
    }

    @Override
    public String toString() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Url url1 = (Url) o;

        return !(url != null ? !url.equals(url1.url) : url1.url != null);

    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }

    private Url(String url) {
        this.url = url;
    }
}
