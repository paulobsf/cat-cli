package me.pauloferreira.catcli;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class CatPicService {

  public static final String CAT_API_ENDPOINT = "http://thecatapi.com/api/images/get";

  private final OkHttpClient okHttpClient;

  public CatPicService() {
    okHttpClient = new OkHttpClient();
  }

  public String getCatUrl() {
    try {
      okHttpClient.setFollowRedirects(false);

      Request request = new Request.Builder()
          .url(CAT_API_ENDPOINT)
          .build();

      Response response = okHttpClient.newCall(request).execute();

      return response.header("Location"); //  Assumes redirect
    } catch (IOException e) {
      throw new CatException("Could not retrieve cat url", e);
    }
  }
}
