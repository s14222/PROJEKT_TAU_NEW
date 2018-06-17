package Selenium;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.openqa.selenium.support.ui.Select;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class SeleniumTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAllert = true;
    private StringBuffer verificationErrors = new StringBuffer();


    public String username = "ABCDefg";
    public String password = "123456abc";
    public String confPassword = password;


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:\\TAU\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://localhost:8083";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void invalidRegisterTest() throws Exception {
        driver.get(baseUrl);

        register();

        //fillPersonalInformation("ABCDEFGH", "maknaeLine95", "maknaeLine95");

        driver.findElement(By.id("usernameInputId")).clear();
        driver.findElement(By.id("usernameInputId")).sendKeys(username);

        driver.findElement(By.id("passwordInputId")).clear();
        driver.findElement(By.id("passwordInputId")).sendKeys(password);

        driver.findElement(By.id("passwordConfirmInputId")).clear();
        driver.findElement(By.id("passwordConfirmInputId")).sendKeys(confPassword);

        submit();

        if (checkIfErrorExist("#userForm")) {
            String alertMessage = driver.findElement(By.id("username.errors")).getText();
            String alertMessage1 = driver.findElement(By.id("password.errors")).getText();
            String alertMessage2 = driver.findElement(By.id("passwordConfirm.errors")).getText();

            if (alertMessage != null) {
                fillPersonalInformation(username, null, null);
                submit();
            }

            if (alertMessage1 != null) {
                fillPersonalInformation(null, password, null);
                submit();
            }

            if (alertMessage2 != null) {
                fillPersonalInformation(null, null, confPassword);
                submit();
            }

        }



        //sprawdza czy element jest wyswietlony
        assertEquals(true, driver.findElement(By.linkText("Logout")).isDisplayed());

        Thread.sleep(3000);
        logOut();
    }


    private boolean checkIfErrorExist(String cssSelector) throws Exception {
        Thread.sleep(3000);
        return driver.findElement(By.cssSelector(cssSelector)).isDisplayed();
    }


    private void fillForm(String username, String password, String confPassword) {

        fillPersonalInformation(username, password, confPassword);

    }


    private void fillPersonalInformation(String username, String password, String confPassword) {
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(username);

        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.id("passwordConfirm")).clear();
        driver.findElement(By.id("passwordConfirm")).sendKeys(confPassword);

    }

    private void register() {
        driver.findElement(By.linkText("Register")).click();
    }

    private void submit(){
        driver.findElement(By.cssSelector("#userForm > button")).click();
    }

    private void logOut() {
        driver.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}