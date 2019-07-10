package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Log;

public class LoginPage extends PageObject {

    @FindBy(name = "uid")
    private WebElement userID;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "btnLogin")
    private WebElement loginButton;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static LoginPage using(WebDriver driver){
        return new LoginPage(driver);
    }

    public LoginPage setCredentials(String id, String password){
        Log.info("Setting Credentials");
        userID.clear();
        userID.sendKeys(id);
        this.password.clear();
        this.password.sendKeys(password);
        return this;
    }
    public ManagerPage clickLoginButton(){
        Log.info("Clicking Login Button");
        this.loginButton.click();
        return new ManagerPage(driver);
    }
}
