package me.pauloferreira.catcli;

import org.junit.Test;

import java.net.URL;

public class CatPicServiceTest {

  @Test
  public void testGetCatUrl() throws Exception {
    CatPicService catPicService = new CatPicService();

    //  implicitly checks for MalformedURLException (including null)
    String catUrl = catPicService.getCatUrl();

    new URL(catUrl);
  }
}