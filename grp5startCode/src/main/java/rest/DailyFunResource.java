/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BoredDTO;
import dtos.CatFactsDTO;
import dtos.DailyDTO;
import dtos.MyIPDTO;
import dtos.SpaceDTO;
import dtos.TrumpQuotesDTO;
import facades.DataFetcherFacade;
import java.io.IOException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author GRP 5
 */
@Path("dailyfun")
public class DailyFunResource {

    private static final DataFetcherFacade FACADE = new DataFetcherFacade();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    public DailyFunResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getDailyFun() throws IOException {

        BoredDTO boredDTO = FACADE.getBoredDTO();
        CatFactsDTO catFactsDTO = FACADE.getCatFactsDTO();
        MyIPDTO myIPDTO = FACADE.getMyIPDTO();
        SpaceDTO spaceDTO = FACADE.getSpaceDTO();
        TrumpQuotesDTO trumpQuotesDTO = FACADE.getTrumpQuotesDTO();

        DailyDTO dailyDTO = new DailyDTO(boredDTO, catFactsDTO, myIPDTO, spaceDTO, trumpQuotesDTO);
        String daily = GSON.toJson(dailyDTO);
        return daily;
    }

}
