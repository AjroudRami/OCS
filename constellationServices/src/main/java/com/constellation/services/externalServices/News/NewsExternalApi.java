package com.constellation.services.externalServices.News;

import io.swagger.model.News;

import java.util.List;

public interface NewsExternalApi {
  public List<News> getNewsByCountryName(String countryName, String lang);
}
