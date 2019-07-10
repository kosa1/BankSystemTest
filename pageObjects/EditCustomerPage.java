package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditCustomerPage extends PageObject {

    @FindBy(name = "cusid")
    private WebElement customer_id;

    @FindBy(name = "AccSubmit")
    private WebElement submit_btn;

    @FindBy(name = "res")
    private WebElement res_btn;


    public EditCustomerPage(WebDriver driver) {
        super(driver);
    }

    public EditCustomerPage setCustomerId(String id){
        this.customer_id.sendKeys(id);
        return this;
    }
    public void clickSubmitBtn(){
        this.submit_btn.click();
    }

}
