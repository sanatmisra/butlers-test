package sanat.helpers.driver;

import org.apache.commons.lang.SystemUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class WebDriverCreators {

    private static final Dimension WINDOW_SIZE_PC = new Dimension(1920, 955);
    private static final Dimension WINDOW_SIZE_MOBILE = new Dimension(600, 800);
    private static final Dimension WINDOW_SIZE_MOBILE_PORTRAIT = new Dimension(768, 955);
    private static final Dimension WINDOW_SIZE_MOBILE_LANDSCAPE = new Dimension(955, 768);
    private static final String USER_AGENT_IPHONE = "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3";
    private static final String USER_AGENT_ANDROID = "Mozilla/5.0 (Linux; Android 4.3; Nexus 7 Build/JSS15Q) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.72 Safari/537.36";
    private static final String PHANTOMJS_MAC_EXECUTABLE_PATH = "src/test/resources/drivers/macos/phantomjs";
    private static final String PHANTOMJS_WIN_EXECUTABLE_PATH = "src/test/resources/drivers/windows/phantomjs.exe";
    private static final String PHANTOMJS_LIN_32_EXECUTABLE_PATH = "src/test/resources/drivers/linux_32/phantomjs";
    private static final String PHANTOMJS_LIN_64_EXECUTABLE_PATH = "src/test/resources/drivers/linux_64/phantomjs";

    public static final WebDriverCreator FIREFOX = new WebDriverCreator() {
        @Override
        public WebDriver create() {
            WebDriver driver = new FirefoxDriver();
            setDefaultSettings(driver, WINDOW_SIZE_PC);
            return driver;
        }

    };

    public static final WebDriverCreator PHANTOMJS = new WebDriverCreator() {
        @Override
        public WebDriver create() {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setJavascriptEnabled(true);
            caps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                getProperPhantomExecutable()
            );
            return new PhantomJSDriver(caps);
        }
    };

    public static final WebDriverCreator FIREFOX_MOBILE_IPHONE = new WebDriverCreator() {
        @Override
        public WebDriver create() {
            FirefoxProfile mobileProfile = createFirefoxProfile(USER_AGENT_IPHONE);
            WebDriver driver = new FirefoxDriver(mobileProfile);
            setDefaultSettings(driver, WINDOW_SIZE_MOBILE_PORTRAIT);
            return driver;
        }

    };

    public static final WebDriverCreator FIREFOX_MOBILE_ANDROID_PORTRAIT = new WebDriverCreator() {
        @Override
        public WebDriver create() {
            FirefoxProfile mobileProfile = createFirefoxProfile(USER_AGENT_ANDROID);
            WebDriver driver = new FirefoxDriver(mobileProfile);
            setDefaultSettings(driver, WINDOW_SIZE_MOBILE_PORTRAIT);
            return driver;
        }

    };

    public static final WebDriverCreator FIREFOX_MOBILE_ANDROID_LANDSCAPE = new WebDriverCreator() {
        @Override
        public WebDriver create() {
            FirefoxProfile mobileProfile = createFirefoxProfile(USER_AGENT_ANDROID);
            WebDriver driver = new FirefoxDriver(mobileProfile);
            setDefaultSettings(driver, WINDOW_SIZE_MOBILE_LANDSCAPE);
            return driver;
        }

    };

    public static final WebDriverCreator CHROME = new WebDriverCreator() {
        @Override
        public WebDriver create() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--lang=en");
            ChromeDriver driver = new ChromeDriver(options);
            setDefaultSettings(driver, WINDOW_SIZE_PC);
            return driver;
        }

    };

//    public static final WebDriverCreator PROXY_CHROME_MOBILE_IPHONE = () -> {
//
//        BrowserMobProxy server = BrowserProxy.getProxyServer();
//        server.setHarCaptureTypes(CaptureType.getResponseCaptureTypes());
//        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(server);
//
//        // configure it as a desired capability
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
//        ChromeOptions chromeOptions = createChromeOptions(USER_AGENT_IPHONE);
//        chromeOptions.addArguments("--enable-precise-memory-info");
//        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//
//        WebDriver driver = new ChromeDriver(capabilities);
//        setDefaultSettings(driver, AddExtensionHeight(WINDOW_SIZE_MOBILE));
//
//        return driver;
//    };

    public static final WebDriverCreator DEFAULT = FIREFOX;

    private static void setDefaultSettings(WebDriver driver, Dimension dimension) {
        driver.manage().window().setSize(dimension);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    }

    private static FirefoxProfile createFirefoxProfile(String userAgent) {
        FirefoxProfile mobileProfile = new FirefoxProfile();
        mobileProfile.setPreference("general.useragent.override", userAgent);
        mobileProfile.setPreference("intl.accept_languages", "no,en-us,en");
        return mobileProfile;
    }

    private static ChromeOptions createChromeOptions(String userAgent) {
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.setBinary("src/test/resources/drivers/chromedriver");
        chromeOptions.addArguments("--user-agent=" + userAgent);
        chromeOptions.addArguments("--lang=en-us,en");
        return chromeOptions;
    }

    private static Dimension AddExtensionHeight(Dimension windowSize) {
        Dimension newWindowSize = new Dimension(windowSize.width, windowSize.height);
        String addFirebug = System.getProperty("firebug");
        Boolean addExtensions = false;
        if (addFirebug != null) {
            addExtensions = Boolean.valueOf(addFirebug);
        }
        if (addExtensions) {
            newWindowSize = new Dimension(windowSize.width, windowSize.height + 800);
        }
        return newWindowSize;
    }

    private static String getProperPhantomExecutable() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (SystemUtils.IS_OS_MAC) {
            System.out.println("Mac OS X detected, assigning proper executable.");
            return PHANTOMJS_MAC_EXECUTABLE_PATH;
        } else if (SystemUtils.IS_OS_WINDOWS) {
            System.out.println("Windows detected, assigning proper executable.");
            return PHANTOMJS_WIN_EXECUTABLE_PATH;
        } else if (osName.contains("linux")) {
            if (System.getProperty("os.arch").contains("64")) {
                return PHANTOMJS_LIN_64_EXECUTABLE_PATH;
            } else {
                return PHANTOMJS_LIN_32_EXECUTABLE_PATH;
            }
        }
        return null;
    }
}
