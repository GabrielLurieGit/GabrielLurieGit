package api.rest;

import api_rest.AuthenticationController;
import dto.RegistrationBodyDto;
import dto.TokenDto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class LoginTestsRest extends AuthenticationController {
    RegistrationBodyDto user;
   @BeforeClass
    public void registrationUser(){
        int i = new Random().nextInt(1000);
        user = RegistrationBodyDto.builder()
                .username("bilbo+baggins"+i+"@gmail.com")
                .password("Qwerty123!")
                .firstName("bilbo")
                .lastName("baggins")
                .build();
        System.out.println("registration successful --> "+registrationLogin(user,REGISTRATION_URL).getStatusCode());
    }

    @Test
    public void loginPositiveTest(){
        Assert.assertEquals(registrationLogin(user,LOGIN_URL).getStatusCode(),200);
    }

    @Test
    public void loginNegativeTest_wrongPassword(){
        user.setPassword("password1");
        Response response = registrationLogin(user,LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(),401);
    }

    @Test
    public void loginNegativeTest_wrongEmail(){
        user.setUsername("username");
        Response response = registrationLogin(user,LOGIN_URL);
        System.out.println(response.getBody().print());
        Assert.assertEquals(response.getStatusCode(),401);
    }

}
