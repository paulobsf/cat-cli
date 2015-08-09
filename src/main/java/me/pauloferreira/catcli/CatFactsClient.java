package me.pauloferreira.catcli;

import me.pauloferreira.catcli.model.Facts;
import retrofit.http.GET;
import retrofit.http.Query;

public interface CatFactsClient {
  @GET("/api/facts")
  Facts getFacts(
      @Query("number") Integer number
  );
}
