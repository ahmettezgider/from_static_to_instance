package pages;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.Condition;
import utilities.Driver;

import static utilities.WElement.$;
import static utilities.WElement.open;
import static utilities.WElements.$$;

public class Test01 implements Locators{

    @Test
    public void test1() {

        open(url);
        $(searchInput).sendKeys("summer").pressEnter();
        for (WebElement e : $$(productList))
            System.out.println(e.getText());

        $(loginLink).click();
        $(loginForm).find(loginUsername).sendKeys(username);
        $(loginForm).find(loginPassword).sendKeys(password).pressEnter();
        $(searchInput).clear().sendKeys("blouse").pressEnter();

        $(logoutLink).click();
        $(loginLink).shouldBe(Condition.appear);

        Driver.quitDriver();

    }
}

