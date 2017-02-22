package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.toolbars.GenericTopToolbar;

/**
 * Created by Sergey Mokhov (sergey) on 2017-01-10.
 */
public class MyDashboardPage extends GenericTopToolbar{

    public MyDashboardPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }


}
