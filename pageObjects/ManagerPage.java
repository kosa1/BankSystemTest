package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utilities.Log;


public class ManagerPage extends PageObject {

    @FindBy(linkText = "Edit Customer")
    private WebElement edit_customer_link;

    @FindBy(linkText = "New Customer")
    private WebElement new_customer_link;

    @FindBy(linkText = "New Account")
    private WebElement new_account_link;

    @FindBy(linkText = "Delete Account")
    private WebElement delete_account_link;

    public ManagerPage(RemoteWebDriver driver){
        super(driver);
    }

    public void verifyLogin(String title){
        Assert.assertEquals(title, driver.getTitle());
        System.out.println("assertion passed");
    }

    public void clickOnEditCustomerLink() {
        Log.info("Clicking on Edit Customer Link from the panel on the left");
        this.edit_customer_link.click();
    }

    public void clickOnNewCustomerLink(){
        Log.info("Clicking on New Customer Link from the panel on the left");
        this.new_customer_link.click();
    }

    public void clickOnNewAccountLink(){
        Log.info("Clicking on New Account Link from the panel on the left");
        this.new_account_link.click();
    }

    public void clickOnDeleteAccountLink(){
        Log.info("Clicking on Delete Account Link from the panel on the left");
        this.delete_account_link.click();
    }
}
