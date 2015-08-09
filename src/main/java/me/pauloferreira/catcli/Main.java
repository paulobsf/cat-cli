package me.pauloferreira.catcli;

import java.util.Iterator;
import java.util.Set;

public class Main {

  private static void printHelp(Set<String> availableOptions) {
    String optionsHelp = "[";

    Iterator<String> iterator = availableOptions.iterator();
    while (iterator.hasNext()) {
      optionsHelp += iterator.next();
      if (iterator.hasNext()) {
        optionsHelp += " | ";
      }
    }
    optionsHelp += "]";

    System.out.println("Usage: java -jar <path to jar> " + optionsHelp);
  }

  public static void main(String[] args) {
    try {
      OptionFactory optionFactory = OptionFactory.getInstance();

      Set<String> availableOptions = optionFactory.getOptions();

      switch (args.length) {
        case 0:
          optionFactory.executeDefaultOption();
          break;

        case 1:
          String option = args[0].trim().toLowerCase();
          if (availableOptions.contains(option)) {
            optionFactory.executeOption(option);
          } else {
            printHelp(availableOptions);
          }
          break;

        default:
          printHelp(availableOptions);
          break;
      }

    } catch (CatException e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }
}
