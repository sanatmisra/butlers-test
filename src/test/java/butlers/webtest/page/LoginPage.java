package butlers.webtest.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by misras on 28/07/16.
 */
public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        initPage();
    }

    private void initPage() {
        PageFactory.initElements(driver, this);
    }
}
