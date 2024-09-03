package api.okhttp;

import dto.RegistrationBodyDto;
import interfaces.BaseAPI;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class RegistrationTestOkHttp implements BaseAPI {

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
