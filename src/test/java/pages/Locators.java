package pages;

import org.openqa.selenium.By;

public interface Locators {

    String url = "http://automationpractice.com";
    String username = "deneme@gmail.com";
    String password = "deneme";

    By loginForm = By.cssSelector("form#login_form");
    By loginLink = By.cssSelector("a.login");
    By logoutLink = By.cssSelector("a.logout");
    By loginUsername = By.cssSelector("input[id='email']");
    By loginPassword = By.cssSelector("input[id='passwd']");
    By loginSubmitButton = By.cssSelector("button[id='SubmitLogin']");

    By productList = By.cssSelector("ul.product_list>li");
    By searchInput = By.id("search_query_top");
    By searchButton = By.cssSelector("button[name='submit_search']");

}
