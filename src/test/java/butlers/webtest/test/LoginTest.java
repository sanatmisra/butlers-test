package butlers.webtest.test;

import butlers.helpers.driver.WebDriverCreators;
import butlers.helpers.driver.WebDriverProvider;
import butlers.webtest.page.LoginPage;
import butlers.webtest.page.MyAccountPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by misras on 28/07/16.
 */
public class LoginTest {
    private LoginPage loginPage;
    private MyAccountPage myAccountPage;
    private WebDriver driver;
    private String url;

    @Before
    public void setUp() throws Exception {
        driver = new WebDriverProvider(WebDriverCreators.FIREFOX).getDriver();
        loginPage = new LoginPage(driver);
        myAccountPage = new MyAccountPage(driver);
        url = "https://www.butlers.com/mein-konto";
    }

    @After
    public void tearDown() throws Exception {
        this.driver.quit();
    }

    @Test
    public void loginTest(){
        //Load Login Page
        driver.get(url);

        //Perform Login
        loginPage.enterCredentialsAndLogin("butlerstest@mailinator.com", "qweqwe123");

        //Assert login was successful
        assertThat(myAccountPage.isUserLoggedIn()).isTrue();

        //Perform Logout
        myAccountPage.performLogout();

        //Assert that logout was successful
        assertThat(loginPage.isUserLoggedOut()).isTrue();
    }
}
