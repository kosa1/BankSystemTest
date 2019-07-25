package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

public class EditCustomerFormPage extends PageObject {

    @FindBy(name = "addr")
    private WebElement address;

    @FindBy(name = "city")
    private WebElement city;

    @FindBy(name = "sub")
    private WebElement submit;

    @FindBy(className = "heading3")
    private WebElement assert_string;

    public EditCustomerFormPage(RemoteWebDriver driver) {
        super(driver);
    }

    public static EditCustomerFormPage using(RemoteWebDriver driver){
        return new EditCustomerFormPage(driver);
    }

    public EditCustomerFormPage setAddress(String addr){
        this.address.clear();
        this.address.sendKeys(addr);
        return this;
    }

    public EditCustomerFormPage setCity(String city){
        this.city.clear();
        this.city.sendKeys(city);
        return this;
    }

    public EditCustomerFormPage clickSubmit(){
        this.submit.click();
        return this;
    }

    public String getAssert_string(){
        return this.assert_string.getText();
    }
}
