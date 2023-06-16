package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class CommonMethods {

    public static WebDriver driver;

    public static void  openBrowserAndLaunchApplication(String URL, String browser) {

        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
    public static void sendText(String text, WebElement element) {
        element.clear();
        element.sendKeys(text);
    }

    //method to select from dropdown(single select)
    public static void dropDownSelect(WebElement element, String byVisibleText) {
        var object = new Select(element);
        object.selectByVisibleText(byVisibleText);
        //System.out.println("DropDown selected Element is: " + byVisibleText);
    }

    // method for taking the screenshots
    public static void takeScreenshot(String name) {
        var ts = (TakesScreenshot) driver;
        var file = ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(file, new File(Constants.SCREENSHOT_FOLDER_PATH + name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    }
