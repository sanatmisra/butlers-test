package butlers.webtest.page;

import butlers.helpers.CustomWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by misras on 28/07/16.
 */
public class LoginPage {
    WebDriver driver;
    CustomWait customWait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.customWait = new CustomWait(driver);
        initPage();
    }

    private void initPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "dwfrm_login_username")
    private WebElement loginTextField;

    @FindBy(id = "dwfrm_login_password")
    private WebElement passwordTextField;

    @FindBy(name = "dwfrm_login_login")
    private WebElement loginButton;

    public void enterCredentialsAndLogin(String email, String password) {
        customWait.waitForElementPresent(loginTextField);
        loginTextField.sendKeys(email);
        customWait.waitForElementPresent(passwordTextField);
        passwordTextField.sendKeys(password);
        customWait.waitForElementBeingClickable(loginButton);
        loginButton.click();
    }

    public boolean isUserLoggedOut() {
        return customWait.isElementPresent(loginTextField) && customWait.isElementPresent(passwordTextField);
    }
}
