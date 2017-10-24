package io.swagger.api.factories;

import io.swagger.api.NewsApiService;
import io.swagger.api.impl.NewsApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-10-21T13:20:24.544Z")
public class NewsApiServiceFactory {
    private final static NewsApiService service = new NewsApiServiceImpl();

    public static NewsApiService getNewsApi() {
        return service;
    }
}
