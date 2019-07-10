package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewAccountFormPage extends PageObject {

    @FindBy(xpath = "//*[@id=\"account\"]/tbody/tr[4]/td[2]")
    private WebElement account_id;

    public String getAccount_id(){
        System.out.println("account id: "+account_id.getText());
        return account_id.getText();
    }

    public NewAccountFormPage(WebDriver driver) {
        super(driver);
    }
}
