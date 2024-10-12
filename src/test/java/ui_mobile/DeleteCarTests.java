package ui_mobile;

import config.AppiumConfig;
import dto.RegistrationBodyDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.MyCarsScreen;
import screens.SplashScreen;

public class DeleteCarTests extends AppiumConfig {
MyCarsScreen myCarsScreen;
    @BeforeMethod
    public void login(){
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("bigbrother2@gmail.com")
                .password(" Tr43123456!")
                .build();
        myCarsScreen = new SplashScreen(driver)
                .gotoSearchScreen()
                .clickOnOverFlowMenuBtn()
                .clickBtnLogin()
                .typeLoginForm(user)
                .clickBtnLoginPositive()
                .clickOnOverFlowMenuBtn()
                .clickBtnMyCars();
    }

    @Test
    public void deleteCarPositiveTest(){
        myCarsScreen.deleteCar();

    }
}
