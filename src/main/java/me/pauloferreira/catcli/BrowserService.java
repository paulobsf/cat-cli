package me.pauloferreira.catcli;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class BrowserService {

  private Desktop desktop;

  public BrowserService() {
    if (!java.awt.Desktop.isDesktopSupported()) {
      throw new CatException("Desktop not supported");
    }

    desktop = Desktop.getDesktop();

    if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
      throw new CatException("Desktop doesn't support the \"browse\" action");
    }
  }

  void openUrl(String url) {
    try {
      desktop.browse(new java.net.URI(url));
    } catch (IOException | URISyntaxException e) {
      throw new CatException("Could not open browser", e);
    }
  }
}
