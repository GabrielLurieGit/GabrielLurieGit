package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class MyCarsScreen extends BaseScreen{
    public MyCarsScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/addCarBtn")
    AndroidElement btnAddNewCar;

    @FindBy(xpath = "//*[@text='Car was added!']")
    AndroidElement successMessagePopUp;

    public AddNewCarScreen clickBtnAddNewCar(){
        btnAddNewCar.click();
        return new AddNewCarScreen(driver);
    }


    public boolean validatePopUpMessageSuccess(String text){
        return isTextinElementPresent(successMessagePopUp,text,3);
    }
}
