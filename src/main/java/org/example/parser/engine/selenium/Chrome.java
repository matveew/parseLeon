package org.example.parser.engine.selenium;


import org.example.entity.Proxy;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Chrome {

    private WebDriver driver;


    public Chrome(Proxy proxy) {
        start(proxy);
    }

    public Chrome() {
        start(null);
    }

    public void start(Proxy proxy) {
        try {

            System.out.println("Start");

            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver/chromedriver.exe");
            System.setProperty("webdriver.chrome.whitelistedIps", "");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
           // options.addArguments("start-maximized"); // open Browser in maximized mode
            options.addArguments("disable-infobars"); // disabling infobars
            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            options.addArguments("--no-sandbox"); // Bypass OS security model
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            System.out.println("profile.default_content_setting_values.notifications");
            options.addArguments("--disable-notifications");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36");

            if (proxy != null) {
                setProxy(options, proxy);
                System.out.println("start Extensions");
                options.addExtensions(new File("src/main/resources/chromedriver/extension/Proxy-Auto-Auth_v2.0.crx"));
                System.out.println("started Extensions");
            }


            driver = new ChromeDriver(options);


            if (proxy != null) {
                proxyAuthenticationCase(driver, proxy);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setProxy(ChromeOptions options, Proxy proxy) {
        System.out.println("proxy - " + proxy.getIp() + ":" + proxy.getPort());
        org.openqa.selenium.Proxy seleniumProxy = new org.openqa.selenium.Proxy();
        seleniumProxy.setSslProxy(proxy.getIp() + ":" + proxy.getPort());
        seleniumProxy.setFtpProxy(proxy.getIp() + ":" + proxy.getPort());
        seleniumProxy.setSocksUsername(proxy.getUserLogin());
        seleniumProxy.setSocksPassword(proxy.getUserPassword());
        options.setCapability(CapabilityType.PROXY, seleniumProxy);
    }

    private boolean proxyAuthenticationCase(WebDriver driver, Proxy proxy) {

        for (int i = 0; i < 2; i++) {
            try {


                delaySecond(1);

                ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

                driver.switchTo().window(tabs.get(0));

                delaySecond(1);

                driver.findElement(By.cssSelector("#login")).sendKeys(proxy.getUserLogin());

                delaySecond(2);

                driver.findElement(By.cssSelector("#password")).sendKeys(proxy.getUserPassword());

                delaySecond(2);

                driver.findElement(By.cssSelector("#save")).click();

                delaySecond(2);
                System.out.println("Proxy: " + proxy.getIp() + " registered successfully!");
                return true;


            } catch (Exception e) {
                System.out.println("Error proxyAuthentication");
                e.printStackTrace();
            }
        }
        return false;
    }

    public void get(String var1) {
        driver.get(var1);
        delaySecond(4); // can add a check for the appearance of a certain element on the page to see if the information has been loaded
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void delaySecond(int delay) {
        delay = delay * 1000;

        delay = (int) (delay + (Math.random() * 10));
        try {
            Thread.currentThread().sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
