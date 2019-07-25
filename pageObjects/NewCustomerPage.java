package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import utilities.Browser;
import utilities.Log;

import java.rmi.Remote;

public class NewCustomerPage extends PageObject {

    @FindBy(name = "name")
    private WebElement customer_name;

    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[1]")
    private WebElement male;

    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[2]")
    private WebElement female;

    @FindBy(xpath = "//*[@id=\"dob\"]")
    private WebElement dob;

    @FindBy(name = "addr")
    private WebElement addr;

    @FindBy(name = "city")
    private WebElement city;

    @FindBy(name = "pinno")
    private WebElement pinno;

    @FindBy(name = "state")
    private WebElement state;

    @FindBy(name = "telephoneno")
    private WebElement telephoneno;

    @FindBy(name = "emailid")
    private WebElement emailid;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "sub")
    private WebElement submit;

    @FindBy(name = "res")
    private WebElement reset;


    public NewCustomerPage(RemoteWebDriver driver){
        super(driver);
    }

    public NewCustomerPage setCustomerName(String name){
        Log.info("Setting Customer Name");
        customer_name.sendKeys(name);
        return this;
    }
    public NewCustomerPage setGender(String name){
        Log.info("Setting Customer Gender");
        if(name.equals("male") || name.equals("m")){
            male.click();
        }else if(name.equals("female") || name.equals("f")){
            female.click();
        }
        return this;
    }
    public NewCustomerPage setDate(String date){
       Log.info("Setting Customer Date");
       String year=date.substring(0,4);
       String month=date.substring(4,6);
       String day=date.substring(6,8);

       if(driver.getCapabilities().getBrowserName().equals("chrome")){
        // chrome code
           dob.sendKeys(day);
           dob.sendKeys(month);
           dob.sendKeys(year);

       }else if (driver.getCapabilities().getBrowserName().equals("firefox")) {
           // firefox code

           dob.sendKeys(day);
           dob.sendKeys(month);
           dob.sendKeys(year);

       }
        return this;

    }
    public NewCustomerPage setAddress(String address){
        Log.info("Setting Customer Address");
        addr.sendKeys(address);
        return this;
    }
    public NewCustomerPage setCity(String city){
        Log.info("Setting Customer City");
        this.city.sendKeys(city);
        return  this;
    }
    public NewCustomerPage setState(String state){
        Log.info("Setting Customer State");
        this.state.sendKeys(state);
        return this;
    }

    public NewCustomerPage setPin(String pin){
        Log.info("Setting Customer Pin");
        this.pinno.sendKeys(pin);
        return this;
    }
    public NewCustomerPage setMobileNumber(String mobileNumber){
        Log.info("Setting Customer Mobile Number");
        this.telephoneno.sendKeys(mobileNumber);
        return this;
    }
    public NewCustomerPage setEmail(String email){
        Log.info("Setting Customer Email Address");
        this.emailid.sendKeys(email);
        return this;
    }
    public NewCustomerPage setPassword(String password){
        Log.info("Setting Customer Password");
        this.password.sendKeys(password);
        return this;
    }
    public void performSubmit(){
        Log.info("Clicking  submit button");
        this.submit.click();
    }
}
