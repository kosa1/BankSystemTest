package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditCustomerFormPage extends PageObject {

    @FindBy(name = "addr")
    private WebElement address;

    @FindBy(name = "city")
    private WebElement city;

    @FindBy(name = "sub")
    private WebElement submit;

    @FindBy(className = "heading3")
    WebElement assert_string;

    public EditCustomerFormPage(WebDriver driver) {
        super(driver);
    }

    public static EditCustomerFormPage using(WebDriver driver){
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
