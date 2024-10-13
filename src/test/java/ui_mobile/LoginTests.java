package ui_mobile;

import config.AppiumConfig;
import dto.RegistrationBodyDto;
import dto.TokenDto;
import interfaces.BaseAPI;
import io.appium.java_client.android.AndroidElement;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.SplashScreen;

import static io.restassured.RestAssured.given;

public class LoginTests extends AppiumConfig implements BaseAPI {

    LoginScreen loginScreen;
    @BeforeMethod
    public void openScreenLogin(){
      loginScreen =  new SplashScreen(driver)
                .gotoSearchScreen()
                .clickOnOverFlowMenuBtn()
                .clickBtnLogin(); //перенос клика на кнопку логина перед тестовым методом

    }

    @Test
    public void loginPositiveTest(){
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("bigbrother2@gmail.com")
                .password("Tr43123456!")
                .build();

      Assert.assertTrue(loginScreen
              .typeLoginForm(user)
              .clickBtnLoginPositive()
              .validatePopUpMessageSuccess("Login success!"));
    }

    @Test
    public void loginNegativeTest_WrongPassword(){
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("bigbrother2@gmail.com")
                .password("Tr43123456!1111")
                .build();

      Assert.assertTrue(loginScreen
              .typeLoginForm(user)
              .clickBtnLoginNegative()
              .validateErrorMessage("Login or Password incorrect"));
    }


    @Test
    public void loginNegativeTest_UsernameEmtpy(){
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("")
                .password("Tr43123456!")
                .build();

        Assert.assertTrue(loginScreen
                .typeLoginForm(user)
                .clickBtnLoginNegative()
                .validateErrorMessage("All fields must be filled"));
    }




}
