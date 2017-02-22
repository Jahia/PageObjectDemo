package pageobjects.adminframe;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.GenericPage;

/**
 * Created by Sergey Mokhov (sergey) on 2017-02-22.
 */
public class SiteCreationPage extends GenericPage{
    @FindBy(id = "title")
    private WebElement nameField;
    @FindBy(id = "serverName")
    private WebElement serverNameField;
    @FindBy(id = "siteKey")
    private WebElement siteKeyField;
    @FindBy(xpath = "//button[contains(., 'Next')]")
    private WebElement nextButton;

    public SiteCreationPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebProjectsPage createDefaultEmptySite(String name, String siteKey){
        switchToAdminFrame();
        typeInto(nameField, name);
        typeInto(siteKeyField, siteKey);
        clickOn(nextButton);
        clickOn(nextButton);
        clickOn(nextButton);
        switchToDefaultContent();
        return new WebProjectsPage(getDriver());
    }
}
