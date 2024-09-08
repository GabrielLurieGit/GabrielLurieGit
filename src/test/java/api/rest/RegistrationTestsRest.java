package api.rest;

import api_rest.AuthenticationController;
import dto.ErrorMessageDtoString;
import dto.RegistrationBodyDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class RegistrationTestsRest extends AuthenticationController {
SoftAssert softAssert = new SoftAssert();
    @Test
    public void registrationPositiveTest(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("bilbo+baggins"+i+"@gmail.com")
                .password("Qwerty123!")
                .firstName("Bilbo")
                .lastName("Baggins")
                .build();
      Assert.assertEquals(registrationLogin(user,REGISTRATION_URL).getStatusCode(),200);
    }


    @Test
    public void registrationNegativeTestHTTP(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("bilbo+baggins"+i+"@gmail.com")
                .password("Qwerty123!")
                .firstName("Bilbo")
                .lastName("Baggins")
                .build();
        Response response = registrationLoginHTTP(user,REGISTRATION_URL);
        System.out.println(response.print());
        Assert.assertEquals(registrationLoginHTTP(user,REGISTRATION_URL).getStatusCode(),200);

    }

    @Test
    public void registrationNegativeTest_WrongEmail(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("bilbo+baggins"+i+"gmail.com")
                .password("Qwerty123!")
                .firstName("Bilbo")
                .lastName("Baggins")
                .build();
        Response response = registrationLogin(user,REGISTRATION_URL);
        softAssert.assertEquals(response.getStatusCode(),400,"validate status code");
        ErrorMessageDtoString errorMessage = response.getBody().as(ErrorMessageDtoString.class);
        softAssert.assertEquals(errorMessage.getError(),"Bad Request","validate error");
        softAssert.assertTrue(errorMessage.getMessage().toString().contains("well-formed email address"),"validate message");
        softAssert.assertAll();
    }



}
