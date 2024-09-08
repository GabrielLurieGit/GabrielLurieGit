package api_rest;

import dto.RegistrationBodyDto;
import interfaces.BaseAPI;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class AuthenticationController implements BaseAPI {

    public Response registrationLogin(RegistrationBodyDto user, String url){
        return given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+ url)
                .thenReturn();
    }


    public Response registrationLoginHTTP(RegistrationBodyDto user, String url){
        return given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL_HTTP+ url)
                .thenReturn();
    }
}
