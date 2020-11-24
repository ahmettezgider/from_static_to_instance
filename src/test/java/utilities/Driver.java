package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class Driver {

    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
    public static ThreadLocal<String> browsers = new ThreadLocal<>();

    /**
     * this method returns the threadlocal webDriver
     * @return WebDriver
     */
    public static WebDriver getDriver(){

        if (browsers.get() == null){
            browsers.set("chrome");
        }

        if (drivers.get() == null){
            switch (browsers.get()){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    drivers.set(new ChromeDriver());
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    drivers.set(new FirefoxDriver());
                    break;
                case "ie":
                case "internet explorer":
                    WebDriverManager.iedriver().setup();
                    drivers.set(new InternetExplorerDriver());
                    break;
                case "edge":
                case "msedge":
                    WebDriverManager.edgedriver().setup();
                    drivers.set(new EdgeDriver());
                    break;
                case "opera":
                    WebDriverManager.operadriver().setup();
                    drivers.set(new OperaDriver());
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    drivers.set(new ChromeDriver());
                    break;
            }

        }
        return drivers.get();
    }

    /**
     * quit the threadLocal WebDriver
     */
    public static void quitDriver(){
        if (drivers.get() != null){
            drivers.get().quit();
            drivers.set(null);
        }
    }


    /**
     * arrange the browser size
     * @param size as BrowserSize enum
     */
    public static void setBrowserSizeTo(BrowserSize size){
        switch (size){
            case big:
                drivers.get().manage().window().setPosition(new Point(0, 0));
                drivers.get().manage().window().setSize(new Dimension(1350, 730));
                break;
            case half:
                drivers.get().manage().window().setPosition(new Point(0, 0));
                drivers.get().manage().window().setSize(new Dimension(700, 730));
                break;
            default:
                drivers.get().manage().window().maximize();
                break;
        }
    }


}
