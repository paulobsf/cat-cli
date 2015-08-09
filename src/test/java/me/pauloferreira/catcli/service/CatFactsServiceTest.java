package me.pauloferreira.catcli.service;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import me.pauloferreira.catcli.client.CatFactsClient;
import me.pauloferreira.catcli.exception.CatException;
import me.pauloferreira.catcli.model.Facts;


@RunWith(MockitoJUnitRunner.class)
public class CatFactsServiceTest {

  @Mock
  private CatFactsClient catFactsClient;

  @InjectMocks
  private CatFactsService catFactsService = new CatFactsService();

  @Test
  public void getFact() {
    String expectedValue = "Cats are cute.";

    Facts facts = new Facts(true, Collections.singletonList(expectedValue));

    Mockito.when(catFactsClient.getFacts(1)).thenReturn(facts);

    String fact = catFactsService.getFact();

    Assert.assertThat(fact, CoreMatchers.equalTo(expectedValue));
  }

  @Test(expected = CatException.class)
  public void getFactFail() {
    String expectedValue = "Cats are cute.";

    Facts facts = new Facts(false, Collections.singletonList(expectedValue));

    Mockito.when(catFactsClient.getFacts(1)).thenReturn(facts);

    catFactsService.getFact();
  }

  @Test(expected = CatException.class)
  public void getFactNoFact() {
    Facts facts = new Facts(true, Collections.emptyList());

    Mockito.when(catFactsClient.getFacts(1)).thenReturn(facts);

    catFactsService.getFact();
  }
}
