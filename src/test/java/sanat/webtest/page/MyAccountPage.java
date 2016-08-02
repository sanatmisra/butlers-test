package sanat.webtest.page;

import sanat.helpers.CustomWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by misras on 29/07/16.
 */
public class MyAccountPage {
    WebDriver driver;
    CustomWait customWait;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        this.customWait = new CustomWait(driver);
        initPage();
    }

    private void initPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@title='Logout']")
    private WebElement logoutButton;

    public void performLogout() {
        customWait.waitForElementBeingClickable(logoutButton);
        logoutButton.click();
    }

    public boolean isUserLoggedIn() {
        return customWait.isElementPresent(logoutButton);
    }
}
