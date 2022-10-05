package org.nespresso.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    public static WebDriver driver;
    String webDriverType = "webdriver.chrome.driver";
    String webDriverPath = "C:/melbarrak/Applications/intellijSeleniumJarAndDrivers/chromedriver_win32/chromedriver.exe";

    public Driver() {
        System.setProperty(webDriverType, webDriverPath);
        this.driver = new ChromeDriver();
    }
    //driver.quit();
    //driver.get(URL);
}
