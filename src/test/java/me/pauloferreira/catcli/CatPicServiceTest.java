package me.pauloferreira.catcli;

import org.junit.Test;

import java.net.URL;

public class CatPicServiceTest {

  @Test
  public void testGetCatUrl() throws Exception {
    CatPicService catPicService = new CatPicService();

    new URL(catPicService.getCatUrl());   //  implicitly checks for MalformedURLException (including null)
  }
}