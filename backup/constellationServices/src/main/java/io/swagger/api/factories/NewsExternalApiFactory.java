package io.swagger.api.factories;

import com.constellation.services.externalServices.News.EventRegistryAPI;
import com.constellation.services.externalServices.News.NewsExternalApi;

public class NewsExternalApiFactory {

    public static NewsExternalApi getNewsExternalApi() {
        return  new EventRegistryAPI();
    }
}
