package me.pauloferreira.catcli;

public class Main {
  public static void main(String[] args) {
    try {
      CatPicService catPicService = new CatPicService();
      BrowserService browserService = new BrowserService();

      if (args.length == 0) {
        browserService.openUrl(catPicService.getCatUrl());
      }
    } catch (CatException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
