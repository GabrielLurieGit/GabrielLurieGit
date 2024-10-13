package api.rest;

import api_rest.CarController;
import dto.CarDto;
import dto.ErrorMessageDtoString;
import enums.Fuel;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class AddNewCarTests extends CarController {
SoftAssert softAssert = new SoftAssert();
    @Test
    public void addNewCarPositiveTest(){
        int i = new Random().nextInt(1000)+1000;
        CarDto car = CarDto.builder()
                .serialNumber("555-" + i)
                .manufacture("Tesla")
                .model("Model S")
                .year("2023")
                .fuel(Fuel.ELECTRIC.getFuel())
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .build();
        Response response = addNewCar(car);
        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertTrue(response.getBody().print().contains("Car added"));
        softAssert.assertAll();
    }


    @Test
    public void addNewCarNegativeTest_validateBodyRequest(){
        int i = new Random().nextInt(1000)+1000;
        CarDto car = CarDto.builder()
                .manufacture("Tesla")
                .model("Model S")
                .year("20234")
                .fuel(Fuel.ELECTRIC.getFuel())
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .build();
        Response response = addNewCar(car);
        ErrorMessageDtoString errorMessage = response.getBody().as(ErrorMessageDtoString.class);
        softAssert.assertEquals(response.getStatusCode(),400);
        softAssert.assertTrue(errorMessage.getMessage().toString().contains("must not be blank"));
        softAssert.assertTrue(errorMessage.getError().equals("Bad Request"));
        softAssert.assertAll();
    }


    @Test
    public void addNewCarNegativeTest_validateHeaderDate(){
        CarDto car = CarDto.builder()
                .manufacture("Tesla")
                .model("Model S")
                .year("20234")
                .fuel(Fuel.ELECTRIC.getFuel())
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .build();
        Response response = addNewCar(car);
        softAssert.assertEquals(response.getStatusCode(),400);
        System.out.println(response.getHeader("date"));
        String [] arrayDate = response.getHeader("date").split(" ");

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        System.out.println(localDate+" "+localTime+"------"+localDate.getMonth()+"---------"+localDate.getMonthValue());
        softAssert.assertEquals(arrayDate[1],localDate.getDayOfMonth()+"","validate day");
        softAssert.assertTrue(localDate.getMonth().toString().contains(arrayDate[2].toUpperCase()),"validate month");
        softAssert.assertTrue(arrayDate[3].equals(localDate.getYear()+""),"validate year");
        softAssert.assertAll();
    }


    @Test
    public void addNewCarNegativeTest_wrongAuthorization(){
        int i = new Random().nextInt(1000)+1000;
        CarDto car = CarDto.builder()
                .serialNumber("555-" + i)
                .manufacture("Tesla")
                .model("Model S")
                .year("20234")
                .fuel(Fuel.ELECTRIC.getFuel())
                .seats(4)
                .carClass("A")
                .pricePerDay(100.23)
                .city("Haifa")
                .build();
        Response response = addNewCarWrongToken(car,"eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYmlnYnJvdGhlckBnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTcyNzAwMTY4NSwiaWF5IjoxNzI2NDAxNjg1fQ.1shPExsbSqmZ9TUCsuZ2Aao9meT1cVvW8rEp761VmZk\n");
        System.out.println(response.print());
        softAssert.assertEquals(response.getStatusCode(),401);
       // softAssert.assertTrue(response.getBody().print().contains("Car added"));
        softAssert.assertAll();
    }






}
