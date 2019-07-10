package testCases;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Browser;
import utilities.Constant;
import utilities.Log;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class TestBase {
    RemoteWebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser, Method method) throws MalformedURLException {
        Log.startTestCase();
        driver = Browser.getDriver(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Log.info("Implicit wait applied on the driver for 10 seconds");
        driver.manage().window().maximize();
        driver.get(Constant.URL);
    }
    @BeforeMethod
    public void beforeMethod(ITestResult result) {
        Log.nameOfTest(result.getMethod().getMethodName());
    }
    @AfterClass
    public void tearDown(){
       // Log.nameOfTest(method.getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.endTestCase();
        //driver.quit();
    }
}