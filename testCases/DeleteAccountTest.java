package testCases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DeleteAccountPage;
import pageObjects.LoginPage;
import pageObjects.ManagerPage;
import utilities.Constant;
import utilities.ExcelUtilis;
import utilities.Log;
import utilities.Utilities;

public class DeleteAccountTest extends TestBase {
    private ManagerPage manager_page;

    // new branch
    //9
    // some new
    @Test()
    public void login() {
        manager_page = LoginPage.using(driver)
                .setCredentials(Constant.login_user, Constant.login_password)
                .clickLoginButton();
        Assert.assertEquals("Guru99 Bank Manager HomePage", driver.getTitle());
        Log.info("Login successful!!!\n");
    }

    @Test(priority = 1)
    public void deleteAccount() throws Exception {
        manager_page.clickOnDeleteAccountLink();
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(driver);
        String drawValue = Utilities.drawValueFromExcel("newAccountData", Constant.path_testData); // draw value
        Log.info("Value to be deleted: " + drawValue);

       // ExcelUtilis.setExcelFile(Constant.path_testData,"newAccountData");
        deleteAccountPage
                .setAccounNo(drawValue)
                .performSubmit();


        // switching to alert
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();

        // accept alert -> ok
        Assert.assertEquals(alertMessage, "Do you really want to delete this Account?");
        alert.accept();
        alertMessage = alert.getText();


        if (alertMessage.equals("Account does not exist")) {
            Log.info("alert message: " + alertMessage);
            alert.accept();
        } else if (alertMessage.equals("Account Deleted Sucessfully")) {
            Log.info("alert message: " + alertMessage);
            alert.accept();
            // we need to delete this account no from excel file (newAccountData sheet)
            //ExcelUtilis.deleteRow(drawValue);
            ExcelUtilis e = new ExcelUtilis();
            e.deleteRow("newAccountData",Constant.path_testData,2);
        }
    }
}
