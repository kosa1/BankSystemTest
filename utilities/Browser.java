package utilities;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class Browser {


    public static RemoteWebDriver getDriver(String browser) throws MalformedURLException {
        return new RemoteWebDriver(new URL(Constant.hubUrl), getBrowserCapabilities(browser));
    }

    private static DesiredCapabilities getBrowserCapabilities(String browserType) {

        if (browserType.equals("firefox")) {
            System.out.println("Opening firefox driver");
            Log.info("Name of browser: " + browserType);
            return DesiredCapabilities.firefox();
        }
        else if (browserType.equals("chrome")) {
            System.out.println("Opening chrome driver");
            Log.info("Name of browser: " + browserType);
            return DesiredCapabilities.chrome();
        }
        else if(browserType.equals("IE")){
            //System.out.println("Opening IE driver");
            Log.info("Name of browser: " + browserType);
            return DesiredCapabilities.internetExplorer();
        }
        else{
            Log.info("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
            return DesiredCapabilities.firefox();
        }

    }
}
