package ui_mobile;

import config.AppiumConfig;
import dto.CarDto;
import dto.RegistrationBodyDto;
import enums.Fuel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AddNewCarScreen;
import screens.MyCarsScreen;
import screens.SearchScreen;
import screens.SplashScreen;

import java.util.Random;

public class AddNewCarTests extends AppiumConfig {
    SearchScreen searchScreen;
    @BeforeMethod
    public void login(){
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("bigbrother2@gmail.com")
                .password(" Tr43123456!")
                .build();
        searchScreen = new SplashScreen(driver)
                .gotoSearchScreen()
                .clickOnOverFlowMenuBtn()
                .clickBtnLogin()
                .typeLoginForm(user)
                .clickBtnLoginPositive();
    }

    @Test
    public void addNewCarPositiveTest(){
        int i = new Random().nextInt()*1000+1000;
        CarDto car = CarDto.builder()
                .serialNumber("777-" + i)
                .manufacture("Tesla")
                .model("Model X")
                .year("2024")
                .fuel(Fuel.ELECTRIC.getFuel())
                .seats(4)
                .carClass("B")
                .pricePerDay(1.23)
                .city("Netanya")
                .about("My car")
                .build();
      Assert.assertTrue(searchScreen
              .clickOnOverFlowMenuBtn()
              .clickBtnMyCars()
              .clickBtnAddNewCar()
              .typeAddNewCarForm(car)
              .clickAddCarPositive()
              .validatePopUpMessageSuccess("Car was added!"));
    }

}
