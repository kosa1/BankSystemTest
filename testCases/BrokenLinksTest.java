package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ManagerPage;
import utilities.Constant;
import utilities.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class BrokenLinksTest extends TestBase {
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
    public void verifyBrokenLinks(){

        String homePage = "";
        String url = "";
        HttpURLConnection huc = null;
        int responseCode = 200;
        int counter_all = 0;
        int counter_valid = 0;

        // getting all <a> tag elements
        List<WebElement> links = driver.findElements(By.tagName("a"));

        Iterator<WebElement> iterator = links.iterator();      // iterator reads each element in the list

        while ((iterator.hasNext())){
            counter_all++;
            url = iterator.next().getAttribute("href");
            Log.info("url address: " + url);

            if(url== null || url.isEmpty()){
                Log.info("URL is not configured or is empty");
            }
            if(!url.startsWith(homePage)){
                counter_valid++;
                Log.info("URL belongs to different domain, skipping it");
                continue;
            }
            try{
                //Returns a URLConnection instance that represents a connection to the remote object referred to by the URL
                huc = (HttpURLConnection) (new URL(url).openConnection());

                //Set the method for the URL request, one of: GET POST HEAD OPTIONS PUT DELETE TRACE
                // are legal, subject to protocol restrictions.

                huc.setRequestMethod("HEAD");

                //Gets the status code from an HTTP response message. (int)
                responseCode = huc.getResponseCode();

                if(responseCode >= 400){
                    Log.info(url + " is a broken link");
                }
                else{
                    Log.info(url + " is a valid link");
                    counter_valid++;
                }
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
        Assert.assertEquals(counter_all,counter_valid);
        Log.info("----------------All links are valid!!!--------------------");
    }
}
