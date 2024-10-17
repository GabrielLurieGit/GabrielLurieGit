package ui_mobile;

import config.AppiumConfig;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SearchScreen;
import screens.SplashScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {
    @Test(groups = "positive")
    public void registrationPositiveTest(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .firstName("Bob"+i)
                .lastName("Family"+i)
                .username("bob_family"+i+"@mail.com")
                .password("Qwerty123!")
                .build();
      Assert.assertTrue(new SplashScreen(driver)
              .gotoSearchScreen()
              .clickOnOverFlowMenuBtn()
              .clickOnBtnRegistration()
              .typeRegForm(user)
              .clickCheckbox()
              .clickButttonYallaPositive().validatePopUpMessageSuccess("Registration success!"));
    }

    @Test
    public void registrationNegativeTest_WrongEmail(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .firstName("Bob"+i)
                .lastName("Family"+i)
                .username("bob_family"+i+"mail.com")
                .password("Qwerty123!")
                .build();
        Assert.assertTrue(new SplashScreen(driver)
                .gotoSearchScreen()
                .clickOnOverFlowMenuBtn()
                .clickOnBtnRegistration()
                .typeRegForm(user)
                .clickCheckbox()
                .clickButtonYallaNegative()
                .validateErrorMessage("username=must be a well-formed email address"));
    }


    @Test
    public void registrationNegativeTest_WOCheckbox(){
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .firstName("Bob"+i)
                .lastName("Family"+i)
                .username("bob_family"+i+"@mail.com")
                .password("Qwerty123!")
                .build();
        Assert.assertTrue(new SplashScreen(driver)
                .gotoSearchScreen()
                .clickOnOverFlowMenuBtn()
                .clickOnBtnRegistration()
                .typeRegForm(user)
                .clickButtonYallaNegative()
                .validateErrorMessage("All fields must be filled and agree terms"));
    }






}
