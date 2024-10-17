package screens;

import config.AppiumConfig;
import dto.CarDto;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static config.AppiumConfig.*;

public class AddNewCarScreen extends BaseScreen{
    public AddNewCarScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/editSerial")
    AndroidElement inputEditSerial;

    @FindBy(id = "com.telran.ilcarro:id/editMan")
    AndroidElement inputEditMan;

    @FindBy(id = "com.telran.ilcarro:id/editModel")
    AndroidElement inputEditModel;

    @FindBy(id = "com.telran.ilcarro:id/editCity")
    AndroidElement inputEditCity;
    @FindBy(id = "com.telran.ilcarro:id/editPrice")
    AndroidElement inputEditPrice;
    @FindBy(id = "com.telran.ilcarro:id/editCarClass")
    AndroidElement inputEditCarClass;
    @FindBy(id = "com.telran.ilcarro:id/editYear")
    AndroidElement inputEditYear;
    @FindBy(id = "com.telran.ilcarro:id/editSeats")
    AndroidElement inputEditSeats;
    @FindBy(id = "com.telran.ilcarro:id/editAbout")
    AndroidElement inputEditAbout;

    @FindBy(id = "com.telran.ilcarro:id/addCarBtn")
    AndroidElement btnAddCar;
    @FindBy(id = "com.telran.ilcarro:id/text1")
    AndroidElement dropDownListEditFuel;


    public AddNewCarScreen typeAddNewCarForm(CarDto car) {
        inputEditSerial.sendKeys(car.getSerialNumber());
        inputEditMan.sendKeys(car.getManufacture());
        inputEditModel.sendKeys(car.getModel());
        inputEditCity.sendKeys(car.getCity());
        inputEditPrice.sendKeys(car.getPricePerDay()+"");
        inputEditCarClass.sendKeys(car.getCarClass());
        //===========================
      //  int height = driver.manage().window().getSize().getHeight();
       // int weight = driver.manage().window().getSize().getWidth();
        System.out.println(height+"X"+ weight);
        if(AppiumConfig.height < 1280 || weight < 720){
            TouchAction<?> touchAction = new TouchAction(driver);
            touchAction.longPress(PointOption.point(weight/100,height/4*3))
                    .moveTo(PointOption.point(weight/100,height/4))
                    .release()
                    .perform();
        }
        typeFuel(car.getFuel());
        inputEditYear.sendKeys(car.getYear());
        inputEditSeats.sendKeys(car.getSeats()+"");
        inputEditAbout.sendKeys(car.getAbout());
        return new AddNewCarScreen(driver);
    }
    public MyCarsScreen clickAddCarPositive(){
        btnAddCar.click();
        return new MyCarsScreen(driver);
    }

    private void typeFuel(String fuel) {
      //  dropDownListEditFuel.click();
        clickWait(dropDownListEditFuel,10);
        AndroidElement element = driver.findElement(By.xpath("//*[@text='"+fuel+"']"));
        element.click();
    }

    public ErrorScreen clickAddCarNegative() {
        btnAddCar.click();
        return new ErrorScreen(driver);
    }
}
