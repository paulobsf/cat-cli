package me.pauloferreira.catcli.service;

import java.util.List;

import me.pauloferreira.catcli.client.CatFactsClient;
import me.pauloferreira.catcli.exception.CatException;
import me.pauloferreira.catcli.model.Facts;
import retrofit.RestAdapter;

public class CatFactsService {

  private static final String CAT_FACTS_API_ENDPOINT = "http://catfacts-api.appspot.com";

  private CatFactsClient catFactsClient;

  public CatFactsService() {
    catFactsClient = new RestAdapter.Builder()
        .setEndpoint(CAT_FACTS_API_ENDPOINT)
        .build()
        .create(CatFactsClient.class);
  }

  /**
   * @return cat fact
   * @throws CatException if client returns invalid client response
   */
  public String getFact() {
    Facts facts = catFactsClient.getFacts(1);

    List<String> factsList = facts.getFacts();

    if (facts.isSuccess() && factsList.size() > 0) {
      return factsList.get(0);
    } else {
      throw new CatException("Could not retrieve cat fact");
    }
  }
}
