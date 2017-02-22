package org.jahia.pageobject.tests;

import businessObjects.User;
import core.DriverController;
import core.utils.RandomWordGenerator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.LoginPage;

/**
 * Created by Sergey Mokhov (sergey) on 2017-01-10.
 */
public class EditEngineTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp(){
        this.driver = new DriverController().initDriver("chrome", null);
    }

    @Test
    public void editTest() {
        User root = new User();
        String siteName = RandomWordGenerator.generate(5);
        String siteKey = RandomWordGenerator.generate(7);

        Assert.assertTrue(
                new LoginPage(driver)
                        .login(root)
                        .goToAdministration()
                        .goToWebProjects()
                        .clickCreateSite()
                        .createDefaultEmptySite(siteName, siteKey)
                        .isSitePresentInTheTable(siteName),
                "Site is not found in the Web Projects table after site creation.");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
