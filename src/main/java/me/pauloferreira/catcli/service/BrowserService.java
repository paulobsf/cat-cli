package me.pauloferreira.catcli.service;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import me.pauloferreira.catcli.exception.CatException;

public class BrowserService {

  private Desktop desktop;

  /**
   * @throws CatException if desktop or browse action not supported
   */
  public BrowserService() {
    if (!java.awt.Desktop.isDesktopSupported()) {
      throw new CatException("Desktop not supported");
    }

    desktop = Desktop.getDesktop();

    if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
      throw new CatException("Desktop doesn't support the \"browse\" action");
    }
  }

  /**
   * @throws CatException if invalid url is passed or the browser fails to be launched
   */
  public void openUrl(String url) {
    try {
      desktop.browse(new java.net.URI(url));
    } catch (IOException | URISyntaxException e) {
      throw new CatException("Could not open browser", e);
    }
  }
}
