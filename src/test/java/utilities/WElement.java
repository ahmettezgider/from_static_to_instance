package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static utilities.WElements.$$;


public class WElement {
    /**
     * static method that returns basqar element
     * @param by locator
     * @return Basqar element
     */
    public static WElement $(By by){ return new WElement(by); }

    /**
     * static method that returns basqar element
     * @param e WebElement
     * @return Basqar element
     */
    public static WElement $(WebElement e){ return new WElement(e); }

    /**
     * static method that opens url
     * @param url url as string
     */
    public static void open(String url){ new WElement().openUrl(url); }

    /**
     * instance variables
     */
    private WebDriver driver;
    private WebElement element;
    private WebDriverWait wait;
    private By by;


    /**
     * constructor with no parameter
     */
    private WElement(){
        this.driver = Driver.getDriver();
    }

    /**
     * constructor with a parameter
     * @param by gets selector
     */
    private WElement(By by){
        this();
        this.by = by;
        wait = new WebDriverWait(driver, 4);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by)).get(0);
        if (element == null) {
            elementNotFound();
        }
    }

    /**
     * constructor with a parameter
     * @param e gets element
     */
    private WElement(WebElement e){
        this();
        this.element = e;
        wait = new WebDriverWait(driver, 4);
    }

    /**
     * instance openUrl method
     * @param url gets url as string
     */
    private void openUrl(String url){
        Driver.setBrowserSizeTo(BrowserSize.max);
        driver.get(url);
    }

    /**
     * click to element void method
     */
    public void click(){
        element.click();
    }

    /**
     * sendkes method
     * @param text takes a string
     * @return BasqarElement
     */
    public WElement sendKeys(String text){
        element.sendKeys(text);
        return this;
    }

    /**
     * pressEnter void method
     */
    public void pressEnter(){
        element.sendKeys(Keys.ENTER);
    }

    /**
     * pressEscape void method
     */
    public void pressEscape(){
        element.sendKeys(Keys.ESCAPE);
    }

    /**
     * element clear method
     * @return BasqarElement
     */
    public WElement clear(){
        element.clear();
        return this;
    }

    /**
     * if element is exists
     * @return true if element is exists
     */
    public boolean isExist(){
        return $$(by).size()>0;
    }

    /**
     * if element is displayed
     * @return true if it is displayed
     */
    public boolean isVisible(){
        return element.isDisplayed();
    }

    /**
     * if element is enabled
     * @return true if element is enabled
     */
    public boolean isEnabled(){
        return element.isEnabled();
    }

    /**
     * simple assertion conditions
     * @param cond Condition type enum
     * @return BasqarElement
     */
    public WElement shouldBe(Condition cond){
        switch (cond){
            case exists:
            case appear:
                try {
                    element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by)).get(0);
                }catch (Exception e){
                    elementNotFound();
                }
                break;
            case visible:
                try {
                    element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by)).get(0);
                }catch (Exception e){
                    elementNotFound();
                }
                break;
            case clickable:
            case enabled:
                try {
                    element = wait.until(ExpectedConditions.elementToBeClickable(element));
                }catch (Exception e){
                    elementNotFound();
                }
                break;
            case disappear:
            case invisible:
                try {
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
                }catch (Exception e){
                    elementNotFound();
                }
                break;
        }
        return this;
    }

    /**
     * scrollIntoView
     * @return BasqarElement
     */
    public WElement scrollIntoView(){
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
        return this;
    }

    /**
     * returns the text of element
     * @return string
     */
    public String getText(){
        shouldBe(Condition.visible);
        return element.getText();
    }

    /**
     * return WebElement of BasqarElement
     * @return WebElement
     */
    public WebElement getElement(){
        return element;
    }

    /**
     * find an element in BasqarElement
     * @param by locator of inner element
     * @return BasqarElement
     */
    public WElement find(By by){
        WebElement e = element;
        element = e.findElement(by);
        return this;
    }

    /**
     * if the element contains required text
     * @param text String
     * @return boolean
     */
    public boolean containsText(String text){
        return element.getText().toLowerCase().contains(text.toLowerCase());
    }

    private void elementNotFound(){
        throw new RuntimeException("Failed to find the element located : ' " + by.toString() + " '");
    }

    public WElement selectOptionWithText(String visibleText){
        WebElement element = null;
        for (WebElement e : $$(by)){
            if ($(e).containsText(visibleText)) {
                $(e).scrollIntoView().shouldBe(Condition.enabled).click();
                element = e;
                break;
            }
        }
        return $(element);
    }

    public WElement selectOptionWithIndex(int optionIndex){
        List<WebElement> elements = $$(by);

        if (optionIndex >= elements.size())
            optionIndex = elements.size()-1;
        else if (optionIndex < 0)
            optionIndex = 0;

        return $(elements.get(optionIndex));
    }
}
