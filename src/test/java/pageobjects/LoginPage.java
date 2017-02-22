package pageobjects;

import businessObjects.User;
import core.ApiController;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Sergey Mokhov (sergey) on 2017-01-10.
 */
public class LoginPage extends GenericPage {
    private String pageUrl = ApiController.getBaseURL()+"/qa/start";

    @FindBy(how = How.NAME, using = "username")
    private WebElement usernameField;
    @FindBy(how = How.NAME, using = "password")
    private WebElement passwordField;
    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Login')]")
    @CacheLookup
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get(pageUrl);
        PageFactory.initElements(driver, this);
    }


    public MyDashboardPage login(User user) {
        typeInto(usernameField, user.getUsername());
        typeInto(passwordField, user.getPassword());
        clickOn(loginButton);
        return new MyDashboardPage(getDriver());
    }
}
