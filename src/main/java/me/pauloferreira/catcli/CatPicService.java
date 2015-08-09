package me.pauloferreira.catcli;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CatPicService {

  private static final String CAT_API_ENDPOINT = "http://thecatapi.com/api/images/get";

  private OkHttpClient okHttpClient;

  public CatPicService() {
    okHttpClient = new OkHttpClient();
  }

  private Response executeHttpCall(String url, boolean followRedirects) throws IOException {
    okHttpClient.setFollowRedirects(followRedirects);

    Request request = new Request.Builder()
        .url(url)
        .build();

    return okHttpClient.newCall(request).execute();
  }

  public String getCatUrl() {
    try {
      Response response = executeHttpCall(CAT_API_ENDPOINT, false);

      return response.header("Location"); //  Assumes redirect
    } catch (IOException e) {
      throw new CatException("Could not retrieve cat url", e);
    }
  }

  private String getFileName(HttpUrl httpUrl) {
    List<String> pathSegments = httpUrl.pathSegments();

    return pathSegments.get(pathSegments.size() - 1);
  }

  private String saveFileInDesktop(String fileName, byte[] content) throws IOException {
    String filePath = System.getProperty("user.home") + "/Desktop/" + fileName;

    FileOutputStream fileOutputStream = new FileOutputStream(filePath);
    fileOutputStream.write(content);
    fileOutputStream.close();

    return filePath;
  }

  public String getCatPhoto() {
    try {
      Response response = executeHttpCall(CAT_API_ENDPOINT, true);

      HttpUrl redirectUrl = response.request().httpUrl();

      return saveFileInDesktop(getFileName(redirectUrl), response.body().bytes());
    } catch (IOException e) {
      throw new CatException("Could not retrieve cat photo", e);
    }
  }
}
