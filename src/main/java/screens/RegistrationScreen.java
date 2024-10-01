package screens;

import dto.RegistrationBodyDto;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationScreen extends BaseScreen{
    public RegistrationScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    @FindBy(id = "com.telran.ilcarro:id/editRegName")
    AndroidElement regName;

    @FindBy(id = "com.telran.ilcarro:id/editRegLastName")
    AndroidElement regLastName;

    @FindBy(id = "com.telran.ilcarro:id/editRegEmail")
    AndroidElement regEmail;

    @FindBy(id = "com.telran.ilcarro:id/editRegPassword")
    AndroidElement regPassword;
    @FindBy(id = "com.telran.ilcarro:id/checkBoxAgree")
    AndroidElement checkBoxAgree;
    @FindBy(id = "com.telran.ilcarro:id/regBtn")
    AndroidElement btnYalla;


    public RegistrationScreen typeRegForm(RegistrationBodyDto user){
        regName.sendKeys(user.getFirstName());
        regLastName.sendKeys(user.getFirstName());
        regEmail.sendKeys(user.getUsername());
        regPassword.sendKeys(user.getPassword());
        return this;
    }

    public RegistrationScreen clickCheckbox(){
        checkBoxAgree.click();
        return this;
    }

    public SearchScreen clickButttonYallaPositive(){
        btnYalla.click();
        return new SearchScreen(driver);
    }

    public ErrorScreen clickButtonYallaNegative(){
        btnYalla.click();
        return new ErrorScreen(driver);
    }






}
