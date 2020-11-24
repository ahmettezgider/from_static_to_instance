package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class WElements {

    public static List<WebElement> $$(By by){ return new WElements(by).elements; }

    private WebDriver driver;
    private List<WebElement> elements;
    private By by;

    private WElements(){
        this.driver = Driver.getDriver();
    }

    public WElements(By by){
        this();
        this.by = by;
        elements = driver.findElements(by);
    }




}
