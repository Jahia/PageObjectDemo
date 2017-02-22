package pageobjects.toolbars;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import pageobjects.ServerSettingsPage;
import pageobjects.GenericPage;

/**
 * Created by Sergey Mokhov (sergey) on 2017-02-22.
 */
public class GenericTopToolbar extends GenericPage {
    @FindBy(xpath = "//em[@class='x-btn-arrow']/button[text()='Mode']")
    protected WebElement modeButon;
    @FindBy(xpath="//span[contains(., 'Administration')]")
    protected WebElement administartionMenuItem;
    @FindBy(xpath="//sregwergwergwergwergewrgwergation')]")
    protected  WebDriver test;
    public GenericTopToolbar (WebDriver driver){
        super(driver);
        ElementLocatorFactory finder =  new AjaxElementLocatorFactory(driver, 3);
        PageFactory.initElements(finder, this);
    }

    public ServerSettingsPage goToAdministration(){
        clickOn(modeButon);
        waitForElementToBeVisible(administartionMenuItem);
        clickOn(administartionMenuItem);
        return new ServerSettingsPage(getDriver());
    }
}
