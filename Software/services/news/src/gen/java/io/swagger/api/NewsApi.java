package io.swagger.api;



import io.swagger.api.factories.NewsApiServiceFactory;

import io.swagger.annotations.ApiParam;


import io.swagger.model.News;


import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;


@Path("/news")
@io.swagger.annotations.Api(description = "the news API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-10-21T13:20:24.544Z")
public class NewsApi {
    private final NewsApiService delegate;

    public NewsApi(@Context ServletConfig servletContext) {
        NewsApiService delegate = null;

        if (servletContext != null) {
            String implClass = servletContext.getInitParameter("NewsApi.implementation");
            if (implClass != null && !"".equals(implClass.trim())) {
                try {
                    delegate = (NewsApiService) Class.forName(implClass).newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (delegate == null) {
            delegate = NewsApiServiceFactory.getNewsApi();
        }

        this.delegate = delegate;
    }


    @GET
    @Path("/{countryName}/{lang}")
    @Produces({"application/json"})
    @io.swagger.annotations.ApiOperation(value = "Get latest news ", notes = "Get the latest of a country", response = News.class, tags={ "News", })
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Get latest news of the passed country", response = News.class) })
    public Response newsCountryNameLangGet(@ApiParam(value = "The country we want to check news",required=true) @PathParam("countryName") String countryName
            ,@ApiParam(value = "Language in which news should be displayed",required=true) @PathParam("lang") String lang
            ,@Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.newsCountryNameGet(countryName,lang,securityContext);
    }
}

