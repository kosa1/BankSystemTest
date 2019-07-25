package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.FileUtils;

public class Utilities {
    public static String generateRandomIntIntRange() {
        Log.info("Random number generation function launched");
        // random number from 1-999 range
        Random r = new Random();
        int number =  r.nextInt((999 - 1) + 1) + 1;
        //Log.info("random number(added to email): " + number.toString());
        return Integer.toString(number);
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

    public static String drawValueFromExcel(String sheetName, String pathFile) throws Exception {
        try {
            ExcelUtilis.setExcelFile(pathFile,sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> valueList = new ArrayList<>();
        int lastRow = ExcelUtilis.ExcelWSheet.getLastRowNum();
        System.out.println("last row no: "+ lastRow);
        for(int i=1; i<=lastRow; i++){
            valueList.add(ExcelUtilis.getCellData(i,0));    // adding current value to the list
            System.out.println(ExcelUtilis.getCellData(i,0));
        }
        //System.out.println("last row: " + lastRow);
        // drawing value from the valueList
        Random r = new Random();
        int randomNumber = r.nextInt(lastRow); // [0...6]
        return valueList.get(randomNumber);
    }
}
