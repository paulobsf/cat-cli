package me.pauloferreira.catcli.service;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CatPicServiceTest {

  @Mock
  private OkHttpClient okHttpClient;

  @Mock
  private Call call;

  @InjectMocks
  private CatPicService catPicService = new CatPicService();

  @Test
  public void getCatUrl() throws Exception {
    String expectedValue = "http://pauloferreira.me/cat.png";

    Request request = new Request.Builder()
        .url("http://pauloferreira.me/api/images/get")
        .build();

    Response response = new Response.Builder()
        .request(request)
        .protocol(Protocol.HTTP_1_1)
        .code(302)
        .addHeader("Location", expectedValue)
        .build();

    when(call.execute()).thenReturn(response);
    when(okHttpClient.newCall(any())).thenReturn(call);

    String catUrl = catPicService.getCatUrl();

    assertThat(catUrl, equalTo(expectedValue));
  }

  @Test
  public void getCatPhoto() throws Exception {
    String fileName = UUID.randomUUID().toString() + ".png";

    String expectedValue = System.getProperty("user.home") + "/Desktop/" + fileName;

    Request request = new Request.Builder()
        .url("http://pauloferreira.me/" + fileName)
        .build();

    ResponseBody body = ResponseBody.create(MediaType.parse("image/png"), new byte[]{});

    Response response = new Response.Builder()
        .request(request)
        .protocol(Protocol.HTTP_1_1)
        .code(200)
        .body(body)
        .build();

    when(call.execute()).thenReturn(response);
    when(okHttpClient.newCall(any())).thenReturn(call);

    String catPath = catPicService.getCatPhoto();

    //  xxx: would be much better to mock fs somehow
    Files.delete(FileSystems.getDefault().getPath(catPath));

    assertThat(catPath, equalTo(expectedValue));

  }
}