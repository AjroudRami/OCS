package com.constellation.services.externalServices.News;

import io.swagger.api.factories.NewsFactory;
import io.swagger.model.News;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.util.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class EventRegistryAPI implements NewsExternalApi {
    @Override
    public List<News> getNewsByCountryName(String countryName, String lang) {
        List<News> newsList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String now = dtf.format(LocalDateTime.now());

        String url = "http://eventregistry.org/json/event?apiKey=f11b1176-5902-4d34-8172-294c74e4b04a&_time=" + now + "&action=getEvents&eventsConceptLang=fr&eventsCount=25&eventsEventImageCount=1&eventsIncludeEventInfoArticle=true&eventsIncludeEventSocialScore=true&eventsPage=1&eventsSortBy=date&query=%7B%22$query%22:%7B%22locationUri%22:%7B%22$and%22:%5B%22http:%2F%2Fen.wikipedia.org%2Fwiki%2F" + countryName + "%22%5D%7D%7D%7D&resultType=events";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();

            String StringResponse = response.body().string();
            JSONObject jsonResponse = new JSONObject(StringResponse);
            if (jsonResponse.has("events")) {

                JSONObject events = jsonResponse.getJSONObject("events");

                int resultCount = events.getInt("count");

                if (resultCount > 0) newsList = rawDataToList(events.getJSONArray("results"), lang);


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsList;
    }


    private List<News> rawDataToList(JSONArray events, String lang) {
        List<News> newsList = new ArrayList<>();

        JSONObject event, title, article, source;
        Object articleLang;
        for (int index = 0; index < events.length(); index++) {

            event = events.getJSONObject(index);

            JSONObject summary = event.getJSONObject("summary");
            if (summary.has(lang)) {
                title = event.getJSONObject("title");
                article = event.getJSONObject("infoArticle");
                articleLang = article.get(lang);
                if (articleLang != null) {
                    try {
                        source = ((JSONObject) articleLang).getJSONObject("source");
                        newsList.add(NewsFactory.createNews(title.getString(lang), summary.getString(lang), ((JSONObject) articleLang).getString("dateTime"), source.getString("title")));

                    } catch (ClassCastException e) {

                    }

                }

            }
        }

        return newsList;
    }
}
