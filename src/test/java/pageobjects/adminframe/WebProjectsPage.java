package pageobjects.adminframe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.GenericPage;

/**
 * Created by Sergey Mokhov (sergey) on 2017-02-22.
 */
public class WebProjectsPage extends GenericPage {
    @FindBy(id = "createSite")
    private WebElement createSiteButton;

    public WebProjectsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SiteCreationPage clickCreateSite(){
        switchToAdminFrame();
        clickOn(createSiteButton);
        switchToDefaultContent();
        return new SiteCreationPage(getDriver());
    }

    public boolean isSitePresentInTheTable(String siteName){
        boolean isPresent;

        switchToAdminFrame();
        isPresent = doesElementPresentOnPage(By.xpath("//table[@id='sitesTable']/tbody/tr[td[3][a[text()='"+siteName+"']]]"), 2);
        switchToDefaultContent();
        return isPresent;
    }
}
