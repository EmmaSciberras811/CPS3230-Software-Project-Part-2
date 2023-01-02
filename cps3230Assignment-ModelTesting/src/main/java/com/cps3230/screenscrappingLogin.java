package com.cps3230;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class screenscrappingLogin {

    // Globals
    private boolean goodlogin, viewAlertPage, viewLoginPage = false;
    //Driver instance
    static WebDriver cd;
    String path = "C:\\Users\\User\\Desktop\\webtesting\\chromedriver.exe"; // chrome driver path
    String prop = "webdriver.chrome.driver"; // chrome drive set property
    String url = "https://www.marketalertum.com";
    String userid = "d2ae663d-95d7-458e-962a-fbe38772bb62";
    String tablexpath ="//table[@width = '80%']";

    // good login i.e valid user id
    public boolean correctUserIdGoodLogin() {
        if(!goodlogin) {
            //Entering credentials
            System.setProperty(prop, path);
            cd = new ChromeDriver();
            // go to marketalertum website
            cd.get(url);
            // press login
            cd.findElement(By.xpath("//html/body/header/nav/div/div/ul/li[3]/a")).click();
            // type in user id in the text box
            cd.findElement(By.xpath("//input[@type = 'text']")).sendKeys(userid);
            // press enter key
            cd.switchTo().activeElement().sendKeys(Keys.ENTER);
            goodlogin = true;

            viewAlertsPage();
        }else
            throw new IllegalStateException();
        return viewAlertsPage();
    }

    public boolean viewAlertsPage() {
        int loadingtime = 3; // in seconds
        if (goodlogin) {
            goodlogin = true;

            //checks that the user can view alerts page
            List<WebElement> alertBoxes = cd.findElements(By.xpath(tablexpath));
            boolean alertTable;
            if (alertBoxes.size() <= 0 ) {
                alertTable = cd.findElements(By.xpath("//html/body/div/main")).size() > 0;    // table is present but list is empty ie no alerts present in: <main role="main" class="pb-3">
                WebDriverWait wait = new WebDriverWait(cd, Duration.ofSeconds(loadingtime));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html/body/div/main")));
            }else{
                // an alert can be seen
                alertTable = cd.findElements(By.xpath(tablexpath)).size() > 0; // size of alerts is more than 0 as we can see and alert in the html table
                WebDriverWait wait = new WebDriverWait(cd, Duration.ofSeconds(loadingtime));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tablexpath)));
            }
            // since we are logged in, the user can view the alert page no the login page
            viewAlertPage= true;
            viewLoginPage = false;
            return alertTable;
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean badlogin() {
        if (!goodlogin) {
            System.setProperty(prop, path);
            cd = new ChromeDriver();
            // website url
            cd.get(url);
            // press login
            cd.findElement(By.xpath("//html/body/header/nav/div/div/ul/li[3]/a")).click();
            // type in user id in the text box
            cd.findElement(By.xpath("//input[@type = 'text']")).sendKeys("invaliduserid");
            // press enter key
            cd.switchTo().activeElement().sendKeys(Keys.ENTER);
            goodlogin = false;
            userNotLoggedinIEinLoginPage();
        }
        return userNotLoggedinIEinLoginPage();
    }

    public boolean getStateofGoodlogin() {
        return goodlogin && viewAlertPage;
    }

    public boolean userNotLoggedinIEinLoginPage() {
            if (!goodlogin) {
                goodlogin = false;
                // checks that the user is back in log in screen therefore, the alerts table is not visible
                // xpath below should not be found
                boolean userDoesNotSeeAlerts  = cd.findElements(By.xpath("//html/body/div/main")).size() > 0;
                WebDriverWait viewLogginScreen = new WebDriverWait(cd, Duration.ofSeconds(10));
                // though through the ExpectedConditions in selenium web driver wew ensure that the form ie login bar is being shown on the screen
                viewLogginScreen.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html/body/div/main/form']"))); // xpath of form: /alerts/login

                viewAlertPage = false;
                viewLoginPage = true;
                return userDoesNotSeeAlerts;
            } else {
                throw new IllegalStateException();
            }
    }
}

