package me.pauloferreira.catcli;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import me.pauloferreira.catcli.service.BrowserService;
import me.pauloferreira.catcli.service.CatFactsService;
import me.pauloferreira.catcli.service.CatPicService;

public class OptionFactory {

  private static final String DEFAULT_OPTION = "browser";

  private static final String ASCII_MEOW
      =   " ______\n"
        + "( meow )\n"
        + " ------\n"
        + "     o\n"
        + "      o\n"
        + "       (\"`-'  '-/\") .___..--' ' \"`-._\n"
        + "         ` *_ *  )    `-.   (      ) .`-.__. `)\n"
        + "         (_Y_.) ' ._   )   `._` ;  `` -. .-'\n"
        + "      _.. `--'_..-_/   /--' _ .' ,4\n"
        + "   ( i l ),-''  ( l i),'  ( ( ! .-'   ";

  private final Map<String, Option> options;

  private OptionFactory() {
    this.options = new HashMap<>();
  }

  public void addOption(String name, Option option) {
    this.options.put(name, option);
  }

  public Set<String> getOptions() {
    return this.options.keySet();
  }

  public void executeDefaultOption() {
    executeOption(DEFAULT_OPTION);
  }

  public void executeOption(String name) {
    if (this.options.containsKey(name)) {
      this.options.get(name).execute();
    }
  }

  public static OptionFactory getInstance() {
    OptionFactory optionFactory = new OptionFactory();

    optionFactory.addOption("browser", () -> {
      CatPicService catPicService = new CatPicService();
      BrowserService browserService = new BrowserService();

      browserService.openUrl(catPicService.getCatUrl());
    });

    optionFactory.addOption("file", () -> {
      CatPicService catPicService = new CatPicService();

      String photoPath = catPicService.getCatPhoto();

      System.out.println("Cat photo available in: " + photoPath);
    });

    optionFactory.addOption("fact", () -> {
      CatFactsService catFactsService = new CatFactsService();
      System.out.println(catFactsService.getFact());
    });

    optionFactory.addOption("ascii", () -> System.out.println(ASCII_MEOW));

    return optionFactory;
  }
}
