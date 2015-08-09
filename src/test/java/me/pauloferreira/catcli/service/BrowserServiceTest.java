package me.pauloferreira.catcli.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BrowserServiceTest {

  @Mock
  private Desktop desktop;

  @InjectMocks
  private BrowserService browserService = new BrowserService();

  @Test
  public void openValidUrl() throws IOException, URISyntaxException {
    String url = "http://www.google.com";

    doNothing().when(desktop).browse(any());

    browserService.openUrl(url);

    verify(desktop).browse(new URI(url));
  }
}
