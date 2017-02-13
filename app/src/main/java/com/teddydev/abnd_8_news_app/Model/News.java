package com.teddydev.abnd_8_news_app.Model;

/**
 * Created by Matu on 13.02.2017.
 */

public class News {
    private String title;
    private String section;
    private String webUrl;

    public News(String title, String section, String webUrl) {
        this.title = title;
        this.section = section;
        this.webUrl = webUrl;
    }

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public String getWebUrl() {
        return webUrl;
    }
}
