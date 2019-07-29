package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ManagerPage;
import pageObjects.NewAccountFormPage;
import pageObjects.NewAccountPage;
import utilities.Constant;
import utilities.ExcelUtilis;
import utilities.Log;

import java.io.IOException;

public class CreateNewAccountTest extends TestBase {

    LoginPage login_page;
    ManagerPage manager_page;

    @Test()
    public void login() {
        login_page = new LoginPage(driver);
        manager_page = login_page
                .setCredentials(Constant.login_user,Constant.login_password)
                .clickLoginButton();
        Assert.assertEquals("Guru99 Bank Manager HomePage", driver.getTitle());
        Log.info("Login successful!!!\n");
    }

    @Test(priority = 1)
    public void createNewAccount(){
        manager_page.clickOnNewAccountLink();
        NewAccountPage newAccountPage = new NewAccountPage(driver);
        newAccountPage.setCustomierId("39812")
                .setSelectAccount("Current")
                .setInitialDeposit("900")
                .performClick();
        Assert.assertEquals(driver.getTitle(), "Gtpl Bank Customer Registration Page");
        Log.info("New account created successfully!!!");
        NewAccountFormPage newAccountFormPage = new NewAccountFormPage(driver);
        String account_id = newAccountFormPage.getAccount_id();
        try {
            ExcelUtilis.writeExcel(Constant.folder_path, "TestData.xlsx", "NewAccountData", account_id);
            Log.info("Account ID correctly saved in Excel file");
        } catch (IOException e) {
            e.printStackTrace();
            Log.error("Account ID could not be saved because of the error");
        }


    }
}
