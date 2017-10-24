package io.swagger.api.impl;

import com.constellation.services.externalServices.News.NewsExternalApi;
import io.swagger.api.*;

import io.swagger.api.factories.NewsExternalApiFactory;
import io.swagger.model.News;

import io.swagger.api.NotFoundException;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-10-21T13:20:24.544Z")
public class NewsApiServiceImpl extends NewsApiService {
    @Override
    public Response newsCountryNameGet(String countryName, String lang, SecurityContext securityContext) throws NotFoundException {

        NewsExternalApi externalApi = NewsExternalApiFactory.getNewsExternalApi();

        List<News> newsResults = externalApi.getNewsByCountryName(countryName,lang);
        return Response.ok(newsResults, "application/json; charset=utf8").build();
    }

}
