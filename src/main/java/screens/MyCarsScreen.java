package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.FindBy;
import static config.AppiumConfig.*;

public class MyCarsScreen extends BaseScreen{
    public MyCarsScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/addCarBtn")
    AndroidElement btnAddNewCar;

    @FindBy(xpath = "//*[@text='Car was added!']")
    AndroidElement successMessagePopUp;

    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/LinearLayout']")
    AndroidElement carFromList;

    @FindBy(id = "android:id/button1")
    AndroidElement yesDeleteButton;

    public AddNewCarScreen clickBtnAddNewCar(){
        btnAddNewCar.click();
        return new AddNewCarScreen(driver);
    }


    public boolean validatePopUpMessageSuccess(String text){
        return isTextinElementPresent(successMessagePopUp,text,3);
    }

    public void deleteCar() {
        TouchAction<?> touchAction = new TouchAction(driver);
        touchAction.longPress(PointOption.point(weight/8,height/8*3))
                .moveTo(PointOption.point(weight/8*7,height/8*3))
                .release()
                .perform();
        yesDeleteButton.click();
    }
}
