package me.pauloferreira.catcli;

public class Main {
  public static void main(String[] args) {
    CatPicService catPicService = new CatPicService();

    if (args.length == 0) {
      System.out.println(catPicService.getCatUrl());
    }
  }
}
