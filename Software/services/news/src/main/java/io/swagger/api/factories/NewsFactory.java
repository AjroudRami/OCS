package io.swagger.api.factories;

import io.swagger.model.News;

import java.util.Date;

public class NewsFactory {

    public static News createNews(String title, String summary, String date, String source) {
        News news = new News();
        news.setTitle(title);
        news.setSummary(summary);
        news.setDate(date);
        news.setSource(source);
        return news;
    }
}
