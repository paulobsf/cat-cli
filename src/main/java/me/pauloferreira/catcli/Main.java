package me.pauloferreira.catcli;

import java.util.Iterator;
import java.util.Set;

public class Main {
  private static void printHelp(Set<String> availableOptions) {
    String optionsRepresentation = "[";

    Iterator<String> iterator = availableOptions.iterator();
    while (iterator.hasNext()) {
      optionsRepresentation += iterator.next();
      if (iterator.hasNext()) { optionsRepresentation += " | "; }
    }
    optionsRepresentation += "]";

    System.out.println("Usage: java -jar <path to jar> " + optionsRepresentation);
  }

  public static void main(String[] args) {
    try {
      OptionFactory optionFactory = OptionFactory.getInstance();

      Set<String> availableOptions = optionFactory.getOptions();

      if (args.length == 0) {
        optionFactory.executeDefaultOption();
      } else if (args.length == 1) {
        String option = args[0].trim().toLowerCase();

        if (availableOptions.contains(option)) {
          optionFactory.executeOption(option);
        } else {
          printHelp(availableOptions);
        }
      } else {
        printHelp(availableOptions);
      }

    } catch (CatException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
