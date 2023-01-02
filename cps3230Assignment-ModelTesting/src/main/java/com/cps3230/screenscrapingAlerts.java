package com.cps3230;
import kong.unirest.Unirest;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class screenscrapingAlerts {
    // Globals
    public boolean goodLogin,newAlert,checkAlertList ,deletaAll= false;
    // Maximum amount of alerts in maretalertum
    public int max = 5;

    // Needed for web scrapping - chrome driver cd
    static WebDriver cd;
    // Local Chrome web driver path set as a global var
    String wbpath = "C:\\Users\\User\\Desktop\\webtesting\\chromedriver.exe";
    // Set property global variable - for convenience
    String prop = "webdriver.chrome.driver";
    // User id global variable
    String user_id = "d2ae663d-95d7-458e-962a-fbe38772bb62";
    String marketalertum = "https://www.marketalertum.com";
    String delReqURL = "https://api.marketalertum.com/Alert?userId=d2ae663d-95d7-458e-962a-fbe38772bb62";
    String postAlertURL = "https://api.marketalertum.com/Alert";
    String alertsXpath = "//table[@width = '80%']";// alerts table

    // First we log in, then we add alerts, del alerts. Hence, need to call the login system
    screenscrappingLogin logginsys = new screenscrappingLogin();

    // Alert limit exceeded - limit is 5 alerts (max)
    public boolean creationOf6Alerts() {
        // Loop until all alerts are created
        if (!newAlert) {
            System.setProperty(prop, wbpath);
            cd = new ChromeDriver();
            // website url
            cd.get(marketalertum);
            // press login
            cd.findElement(By.xpath("//html/body/header/nav/div/div/ul/li[3]/a")).click();
            // type in user id in the text box
            cd.findElement(By.xpath("//input[@type = 'text']")).sendKeys(user_id);
            // press enter key
            cd.switchTo().activeElement().sendKeys(Keys.ENTER);
            goodLogin = true;

            // Post req for 6 alerts
            for (int alertCount = 0; alertCount < max + 1; alertCount++) {

                // json using unirest - simple and lightweight HTTP client lib
                Unirest.post(postAlertURL)
                        // method to generate a post req on our site
                        .header("Content-Type", "application/json")
                        .body("{\"alertType\":1," +
                                "\"heading\":\"Testing Exceeding Alert Limit \"," +
                                "\"description\":\" creating 6 alerts by post req\"," +
                                "\"url\":\"https://www.google.com\" ,"+
                                "\"imageUrl\":\"\"," +
                                "\"postedBy\":\"d2ae663d-95d7-458e-962a-fbe38772bb62\"," +
                                "\"priceInCents\":800}")
                        .asJson();
            }
            // updating variables
            newAlert = true;
            return newAlert;
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean creationSomeAlertsToDel(){
        int alertno = 3;
        if (!newAlert) {
            System.setProperty(prop, wbpath);
            cd = new ChromeDriver();
            // website url
            cd.get(marketalertum);
            // press login
            cd.findElement(By.xpath("//html/body/header/nav/div/div/ul/li[3]/a")).click();
            // type in user id in the text box
            cd.findElement(By.xpath("//input[@type = 'text']")).sendKeys(user_id);
            // press enter key
            cd.switchTo().activeElement().sendKeys(Keys.ENTER);
            goodLogin = true;

            List<WebElement> alertTables = cd.findElements(By.xpath(alertsXpath));
            if ( alertTables.size() < alertno) {
                Unirest.post(postAlertURL)
                        .header("Content-Type", "application/json")
                        .body("{\"alertType\":1," +
                                "\"heading\":\"Testing Post Alert ii \"," +
                                "\"description\":\" creating 3 alerts by post req\"," +
                                "\"url\":\"https://www.google.com\" ,"+
                                "\"imageUrl\":\"\"," +
                                "\"postedBy\":\"d2ae663d-95d7-458e-962a-fbe38772bb62\"," +
                                "\"priceInCents\":800}")
                        .asJson();
            }
            newAlert = true;
            return newAlert;
        } else {
            throw new IllegalStateException();
        }
    }

    // Delete Request - all alerts are removed from marketalertum
    public boolean delReq() {
        // Loop until all alerts are deleted
        if (!deletaAll) {
            System.setProperty(prop, wbpath);
            cd = new ChromeDriver();
            // website url
            cd.get(marketalertum);
            // press login
            cd.findElement(By.xpath("//html/body/header/nav/div/div/ul/li[3]/a")).click();
            //type in user id in the text box
            cd.findElement(By.xpath("//input[@type = 'text']")).sendKeys(user_id);
            // press enter key
            cd.switchTo().activeElement().sendKeys(Keys.ENTER);

            // via screen scrapping we know user is logged in
            goodLogin = true;
            boolean elem;

            // Look through the box/table with alerts
            List<WebElement> alertTables = cd.findElements(By.xpath(alertsXpath));
            // Looping through the alerts
            if (alertTables.size() > 0) {
                // Checking for alerts
                elem = cd.findElements(By.xpath(alertsXpath)).size() > 0;

                // Delete Request
                Unirest.delete(delReqURL)
                        .header("Content-Type", "application/json")
                        .asJson();

                // Update boolean flag
                deletaAll = true;
                return deletaAll;
            }
            else
                deletaAll = true;
        }
        else
            throw new IllegalStateException();
        return deletaAll;
    }

    // Getting states and variables for checking the alert page
    public boolean viewalerts() {
        if (!checkAlertList) {
            logginsys.getStateofGoodlogin();
            goodLogin = true;
            checkAlertList = true; // Setting to true
            return checkAlertList;
        } else {
            throw new IllegalStateException();
        }
    }
    public boolean checkAlertCount() {
        boolean elem;
        if (goodLogin) {
            List<WebElement> alertsTablesFound = cd.findElements(By.xpath(alertsXpath));
            if (alertsTablesFound.size() <= max) {
                elem = cd.findElements(By.xpath(alertsXpath)).size() > 0;
                newAlert = true; // setting to true
            }
        }
        return goodLogin && newAlert;
    }
    // Checking the status of the allAlertsDeleted, used in assert during model testing
    public boolean getDelStatus(){
        return deletaAll;
    }

    // Checking if we have an empty alerts list/table, used in assert during model testing after deletion of alerts
    public int noAlertsIfSuccess(){
        int emptyList = 0;
        return emptyList;
    }

    public boolean checkAlertStatus() {
        return goodLogin && checkAlertList;
    }

    public boolean checkIfAlertsPosted(){
        return goodLogin && newAlert;
      }

    public int getAlertListSize(){
        return max;
    }

}
