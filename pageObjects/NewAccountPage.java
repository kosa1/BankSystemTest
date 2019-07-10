package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utilities.Log;

public class NewAccountPage extends PageObject {

    @FindBy(name = "cusid")
    private WebElement customer_id;

    @FindBy(name = "selaccount")
    private WebElement select_account;

    @FindBy(name = "inideposit")
    private WebElement init_deposit;

    @FindBy(name = "button2")
    private WebElement submit_button;

    public NewAccountPage setCustomierId(String customierId){
        Log.info("Setting Customer ID");
        customer_id.sendKeys(customierId);
        return this;
    }
    public NewAccountPage setSelectAccount(String type){
        Log.info("Setting Account Type");
        Select select = new Select(select_account);
        if(type.equals("Savings")){
            select.selectByValue("Savings");
        } else if(type.equals("Current")){
            select.selectByValue("Current");
        }
        return this;
    }
    public NewAccountPage setInitialDeposit(String deposit){
        Log.info("Setting Initial Deposit");
        init_deposit.sendKeys(deposit);
        return this;
    }
    public void performClick(){
        Log.info("Clicking submit button");
        submit_button.click();
    }

    public NewAccountPage(WebDriver driver) {
        super(driver);
    }
}
