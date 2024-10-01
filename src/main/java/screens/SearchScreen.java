package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.extern.java.Log;
import org.openqa.selenium.support.FindBy;

public class SearchScreen extends BaseScreen{
    public SearchScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    AndroidElement overFlowMenuBtn;

    @FindBy(xpath = "//*[@text='Registration' and @resource-id='com.telran.ilcarro:id/title']")
    AndroidElement btnRegistration;
    @FindBy(xpath = "//*[@text='Login' and @resource-id='com.telran.ilcarro:id/title']")
    AndroidElement btnLogin;

    @FindBy(xpath = "//hierarchy/android.widget.Toast")
    AndroidElement popUpMessageSuccess;


    public SearchScreen clickOnOverFlowMenuBtn(){
        overFlowMenuBtn.click();
        return this;
    }

    public RegistrationScreen clickOnBtnRegistration(){
        btnRegistration.click();
        return new RegistrationScreen(driver);
    }

    public boolean validatePopUpMessageSuccess(String text){
        return isTextinElementPresent(popUpMessageSuccess,text,3);
    }

    public LoginScreen clickBtnLogin(){
        btnLogin.click();
        return new LoginScreen(driver);
    }


}
