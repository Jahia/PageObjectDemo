package pageobjects.toolbars;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Sergey Mokhov (sergey) on 2017-02-22.
 */
public class AdministrationTopToolbar extends GenericTopToolbar {
    public AdministrationTopToolbar(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
