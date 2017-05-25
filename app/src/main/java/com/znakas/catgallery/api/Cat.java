package com.znakas.catgallery.api;

/**
 * Created by andriy on 5/22/17.
 */

public final class Cat {
    String url;
    String id;

    public Cat(String url, String id) {
        this.url = url;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
