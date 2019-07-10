package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.apache.commons.io.FileUtils;

public class Utilities {
    public static String generateRandomIntIntRange() {
        Log.info("Random number generation function launched");
        // random number from 1-999 range
        Random r = new Random();
        Integer number =  r.nextInt((999 - 1) + 1) + 1;
        //Log.info("random number(added to email): " + number.toString());
        return number.toString();
    }

    public static void takeScreenshot(WebDriver driver) {
        DateFormat dateFormat = new SimpleDateFormat("H_mm_ss_SSS");
        Date date = new Date();

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            String dateTemp = dateFormat.format(date);
            String temp = "D:\\Projekty\\IdeaProjects\\BankSystemTest\\screenshots\\" + dateTemp +".png";
            Log.info("Screenshot saved: " + temp);
            FileUtils.copyFile(scrFile, new File(temp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
