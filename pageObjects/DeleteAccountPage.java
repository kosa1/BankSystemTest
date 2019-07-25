package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import utilities.Log;

public class DeleteAccountPage extends PageObject {

    @FindBy(name = "accountno")
    private WebElement accountNo;

    @FindBy(name="AccSubmit")
    private WebElement submit;

    public DeleteAccountPage setAccounNo(String value){
        accountNo.clear();
        accountNo.sendKeys(value);
        Log.info("Account number typed");
        return this;
    }

    public DeleteAccountPage performSubmit(){
        submit.click();
        Log.info("Submit button clicked");
        return this;
    }

    public DeleteAccountPage(RemoteWebDriver driver) {
        super(driver);
    }

}
