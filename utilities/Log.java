package utilities;
import org.apache.log4j.Logger;

public class Log {
    //Initialize Log4j instance
    private static Logger Log = Logger.getLogger(Log.class.getName());

    //We can use it when starting tests
    public static void startTestCase (){
        Log.info("***************************************************************************");
        Log.info("***************************************************************************");
        Log.info("$$$$$$$$$$$$$$$$$    S T A R T            $$$$$$$$$$$$$$$$$");
        Log.info("***************************************************************************");
        Log.info("***************************************************************************");
    }

    //We can use it when ending tests
    public static void endTestCase (){
        Log.info("*******************   E    N     D    **************************************");
        Log.info("*******************   E    N     D    **************************************");
        Log.info("*******************   E    N     D    **************************************");
    }

    public static void nameOfTest(String nameOfTest){
        Log.info("name of Method (Test):     " + nameOfTest);
    }

    //Info Level Logs
    public static void info (String message) {
        Log.info(message);
    }

    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(message);
    }

    //Error Level Logs
    public static void error (String message) {
        Log.error(message);
    }

    //Fatal Level Logs
    public static void fatal (String message) {
        Log.fatal(message);
    }

    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }
}
