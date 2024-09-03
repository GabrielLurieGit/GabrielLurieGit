package api.okhttp;

import dto.ErrorMessageDtoString;
import dto.RegistrationBodyDto;
import interfaces.BaseAPI;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Random;

public class RegistrationTestOkHttp implements BaseAPI {


    SoftAssert softAssert = new SoftAssert();
    @Test
    public void registrationNegativeTest_emailWOAt(){
        int i = new Random().nextInt(1000)+1000;
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username("frodo_baggins"+i+"gmail.com")
                .password("Qwerty123!")
                .firstName("frodo")
                .lastName("beggins")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(registrationBodyDto),JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        String responseJSON;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            responseJSON = response.body().string();
            System.out.println(responseJSON);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ErrorMessageDtoString errorMessageDtoString = GSON.fromJson(responseJSON, ErrorMessageDtoString.class);
        softAssert.assertEquals(errorMessageDtoString.getStatus(),400);
        softAssert.assertEquals(errorMessageDtoString.getError(),"Bad Request");
        softAssert.assertTrue(errorMessageDtoString.getMessage().toString().contains("email address"));
        softAssert.assertAll();

    }




    @Test
    public void registrationPositiveTest(){
        int i = new Random().nextInt(1000)+1000;
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username("frodo_baggins"+i+"@gmail.com")
                .password("Qwerty123!")
                .firstName("frodo")
                .lastName("beggins")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(registrationBodyDto),JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(response.isSuccessful());
    }

}
