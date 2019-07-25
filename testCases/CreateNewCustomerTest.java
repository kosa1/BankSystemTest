package testCases;


import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import pageObjects.LoginPage;
import pageObjects.ManagerPage;
import pageObjects.NewCustomerPage;
import utilities.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;


public class CreateNewCustomerTest extends TestBase {
    private ManagerPage manager_page;

    @Test()
    public void login() {
        /*
        LoginPage login_page = new LoginPage(driver);
        login_page.setCredentials(Constant.login_user,Constant.login_password);
        manager_page = login_page.clickLoginButton();
        */
       manager_page = LoginPage.using(driver)
                .setCredentials(Constant.login_user, Constant.login_password)
                .clickLoginButton();
        Assert.assertEquals("Guru99 Bank Manager HomePage", driver.getTitle());
        Log.info("Login successful!!!\n");

    }

    @Test(priority = 1)
    public void createNewCustomer(){
        boolean correct_file = Constant.file_format.equals("xml") || Constant.file_format.equals("excel");
        //Assert.assertTrue(correct_file);
        // ----------------------- code if execution when xml is data source-----------------------------
        // private static final Logger log = Logger.getLogger(CreateNewCustomerTest.class.getName());
        NewCustomerPage ncp;
        if (Constant.file_format.equals("xml")) {

            manager_page.clickOnNewCustomerLink();
            try {
                Map<String, String> cust_temp = XmlUtilis.getCustomer(Constant.xml_file, 1);
                ncp = new NewCustomerPage(driver);
                ncp.setCustomerName(cust_temp.get("name"))
                    .setGender(cust_temp.get("gender"))
                    .setDate(cust_temp.get("date"))
                    .setAddress(cust_temp.get("address"))
                    .setCity(cust_temp.get("city"))
                    .setState(cust_temp.get("state"))
                    .setPin(cust_temp.get("pin"))
                    .setMobileNumber(cust_temp.get("number"))
                    .setEmail(Utilities.generateRandomIntIntRange() + cust_temp.get("email"))
                    .setPassword(cust_temp.get("password"))
                    .performSubmit();
                Assert.assertEquals(driver.getTitle(), "Guru99 Bank Customer Registration Page"); // simplify assertion on page title
                Log.info("New Customer created correctly!!!");
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        }
        // ----------------------- code if execution when excel is data source-----------------------------
        else if (Constant.file_format.equals("excel")) {
            Log.info("createNewCustomer test method is starting...excel source");
            manager_page.clickOnNewCustomerLink();
            ncp = new NewCustomerPage(driver);
            try {
                ExcelUtilis.setExcelFile(Constant.path_testData, "newCustomerData");
            } catch (Exception e) {
                e.printStackTrace();
                Log.error("Excel file can not be found");
            }
            try {
                ncp.setCustomerName(ExcelUtilis.getCellData(0, 1))
                        .setGender(ExcelUtilis.getCellData(1, 1))
                        .setAddress(ExcelUtilis.getCellData(3, 1));
                if (driver.getCapabilities().getBrowserName().equals("chrome")) {
                    ncp.setDate(ExcelUtilis.getCellData(2, 1));
                } else if (driver.getCapabilities().getBrowserName().equals("firefox")) {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("document.getElementById('dob').value='11.12.2017'");

                    ncp.setDate(ExcelUtilis.getCellData(2,1));
                }
                ncp.setCity(ExcelUtilis.getCellData(4, 1))
                        .setState(ExcelUtilis.getCellData(5, 1))
                        .setPin(ExcelUtilis.getCellData(6, 1))
                        .setMobileNumber(ExcelUtilis.getCellData(7, 1))
                        .setEmail(Utilities.generateRandomIntIntRange() + ExcelUtilis.getCellData(8, 1))
                        .setPassword(ExcelUtilis.getCellData(9, 1))
                        .performSubmit();
                Assert.assertEquals(driver.getTitle(), "Guru99 Bank Customer Registration Page"); // simplify assertion on page title
                Log.info("New Customer created correctly!!!");
            } catch (Exception e) {
                //e.printStackTrace();
                Log.error("Customer could not be created");
            }
        }
        // ----------------------- code if execution when JSON is data source-----------------------------
        else if (Constant.file_format.equals("json")){
            Log.info("createNewCustomer test method is starting...JSON source");
            manager_page.clickOnNewCustomerLink();
            ncp = new NewCustomerPage(driver);
            Map<String, String> customer;
            customer = JSONUtilis.getCustomerJsonMap(Constant.JSON_file_path);
            assert customer != null;
            ncp.setCustomerName(customer.get("name"))
                    .setGender(customer.get("gender"))
                    .setAddress(customer.get("address"))
                    .setDate(customer.get("date"))
                    .setCity(customer.get("city"))
                    .setState(customer.get("state"))
                    .setPin(customer.get("pin"))
                    .setMobileNumber(customer.get("number"))
                    .setEmail(Utilities.generateRandomIntIntRange()+customer.get("email"))
                    .setPassword(customer.get("password"))
                    .performSubmit();
            Assert.assertEquals(driver.getTitle(), "Guru99 Bank Customer Registration Page"); // simplify assertion on page title
            Log.info("New Customer created correctly!!!");
        }
        else{
            Log.error("File XML/Excel/Json could not be found");
        }
        // taking screenshot and saving to the disk
        Utilities.takeScreenshot(driver);
        try {
            // taking id from URL address
            String currentUrl = driver.getCurrentUrl();
            String[] split_CurrentUrl = currentUrl.split("cid=");
            String CurrentId = split_CurrentUrl[1];
            // saving id to excel file
            ExcelUtilis.writeExcel(Constant.folder_path, "TestData.xlsx", "CustomerIDs", CurrentId);
            Log.info("ID customer Successfully saved in Excel file");
        } catch (org.openqa.selenium.UnhandledAlertException e) {
            Log.error("Unhandled Alert occurred");
        } catch (IOException e) {
            e.printStackTrace();
            Log.error("Customer id can not be saved - check sheet's" +
                    " tile or localization");
        }

    }
}
