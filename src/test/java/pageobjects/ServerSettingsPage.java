package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.adminframe.WebProjectsPage;
import pageobjects.toolbars.AdministrationTopToolbar;

/**
 * Created by Sergey Mokhov (sergey) on 2017-02-22.
 */
public class ServerSettingsPage extends AdministrationTopToolbar {
    @FindBy(xpath = "//div[@role='presentation']/span[text()='Web Projects']")
    private WebElement webProjectsButton;

    public ServerSettingsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebProjectsPage goToWebProjects(){
        clickOn(webProjectsButton);
        return new WebProjectsPage(getDriver());
    }
}
