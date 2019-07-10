package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.EditCustomerFormPage;
import pageObjects.EditCustomerPage;
import pageObjects.LoginPage;
import pageObjects.ManagerPage;
import utilities.Constant;
import utilities.ExcelUtilis;
import utilities.Log;

public class EditCustomerTest extends TestBase {
    private ManagerPage manager_page;
    private int counter = 0;

    @DataProvider(name = "CustomerDataID")
    public static Object[][] customerData(){
        return new Object[][]{
                {"Polna 12", "Warszawa"},
                {"Smolna 10", "Gdansk"},
                {"Zielona 1", "Krakow"},
                {"Krzyzakow 33", "Wroclaw"}
        };
    }

    @Test()
    public void login() {
        Log.info("login test method is starting...");
        LoginPage login_page = new LoginPage(driver);
        login_page.setCredentials(Constant.login_user,Constant.login_password);
        manager_page = login_page.clickLoginButton();
        Assert.assertEquals("Guru99 Bank Manager HomePage", driver.getTitle());
    }

    @Test(priority = 1, dataProvider = "CustomerDataID")
    public void editCustomer(String address, String city) {
        counter++;
        Log.info("EditCustomer test method is starting         " + counter);
        Log.info("Following data: " + address + ", " + city);
        manager_page.clickOnEditCustomerLink();
        EditCustomerPage ect = new EditCustomerPage(driver);
        try {
            ExcelUtilis.setExcelFile(Constant.path_testData,"CustomerIDs");
            ect.setCustomerId(ExcelUtilis.getCellData(1,0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ect.clickSubmitBtn();
        EditCustomerFormPage ecfp = EditCustomerFormPage.using(driver)
                .setAddress(address)
                .setCity(city)
                .clickSubmit();
        Assert.assertEquals(ecfp.getAssert_string(), "Customer details updated Successfully!!!");

    }
}
