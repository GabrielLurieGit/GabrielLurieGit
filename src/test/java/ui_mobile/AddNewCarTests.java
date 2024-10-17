package ui_mobile;

import api_rest.CarController;
import config.AppiumConfig;
import dto.CarDto;
import dto.CarsDto;
import dto.RegistrationBodyDto;
import enums.Fuel;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AddNewCarScreen;
import screens.MyCarsScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import static helpers.PropertiesReader.getProperty;

import java.util.Random;

public class AddNewCarTests extends AppiumConfig {
    SearchScreen searchScreen;
    @BeforeMethod
    public void login(){
        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username(getProperty("login.properties","email"))
                .password(getProperty("login.properties","password"))
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
        int i = new Random().nextInt(1000)+1000;
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


    @Test
    public void addNewCarPositiveTestValidateWithRest(){
        int i = new Random().nextInt(1000)+1000;
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
        searchScreen
                .clickOnOverFlowMenuBtn()
                .clickBtnMyCars()
                .clickBtnAddNewCar()
                .typeAddNewCarForm(car)
                .clickAddCarPositive();
        CarController carController = new CarController();
        carController.login();
        System.out.println(carController.tokenDto.getAccessToken());
        Response response = carController.getUserCars();
        if(response.getStatusCode()==200){
            CarsDto carsDto = response.as(CarsDto.class);
            for (CarDto carAPI: carsDto.getCars()){
                if(carAPI.equals(car)){
                    System.out.println("list -->"+carAPI);
                    System.out.println("new -->"+car);
                    Assert.assertEquals(car,carAPI);
                    break;
                }
            }
        }else System.out.println("response get all user car"+response);
    }

    @Test
    public void addNewCarNegativeTestSerialNumberisEmpty(){
        CarDto car = CarDto.builder()
                .serialNumber("")
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
                .clickAddCarNegative()
                .validateErrorMessage("Fields: Serial number, Manufacture, Model, City, Price per day is required!"));
    }


}
