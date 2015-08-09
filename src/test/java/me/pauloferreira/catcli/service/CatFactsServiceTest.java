package me.pauloferreira.catcli.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import me.pauloferreira.catcli.client.CatFactsClient;
import me.pauloferreira.catcli.exception.CatException;
import me.pauloferreira.catcli.model.Facts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

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

    when(catFactsClient.getFacts(1)).thenReturn(facts);

    String fact = catFactsService.getFact();

    assertThat(fact, equalTo(expectedValue));
  }

  @Test(expected = CatException.class)
  public void getFactFail() {
    String expectedValue = "Cats are cute.";

    Facts facts = new Facts(false, Collections.singletonList(expectedValue));

    when(catFactsClient.getFacts(1)).thenReturn(facts);

    catFactsService.getFact();
  }

  @Test(expected = CatException.class)
  public void getFactNoFact() {
    Facts facts = new Facts(true, Collections.emptyList());

    when(catFactsClient.getFacts(1)).thenReturn(facts);

    catFactsService.getFact();
  }
}
