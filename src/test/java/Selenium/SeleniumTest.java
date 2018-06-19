package Selenium;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;


import static org.junit.Assert.*;


public class SeleniumTest {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:\\TAU\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://localhost:8086";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void invalidRegisterTest() throws Exception {
        driver.get(baseUrl);

        register();

        //wpisz do forma
        fillPersonalInformation("qkied", "qwertyui", "qwertyui");
        submit();

        if (checkIfErrorExist("#userForm")) {
            //sprawdz czy jest blad w username
            String alertMessage = driver.findElement(By.id("username.errors")).getText();

            assertNotNull(alertMessage);

            if (alertMessage != null) {

                //popraw wpisujac to do forma
                fillPersonalInformation(usernameRandom("drugitest"), "qwer", "qwertyui");
                submit();

                if (checkIfErrorExist("#userForm")) {

                    String alertMessage1 = driver.findElement(By.id("password.errors")).getText();
                    assertNotNull(alertMessage1);

                    if (alertMessage1 != null) {

                        //popraw i wpisz
                        fillPersonalInformation(usernameRandom("trzecitest"), "qwertyui", "qwertyuio");
                        submit();

                        if (checkIfErrorExist("#userForm")) {

                            String alertMessage2 = driver.findElement(By.id("passwordConfirm.errors")).getText();
                            assertNotNull(alertMessage2);

                            if (alertMessage2 != null) {
                                //popraw wpisujac to do forma
                                fillPersonalInformation(usernameRandom("czwarty"), "qwertyui", "qwertyui");
                                submit();
                            }
                        } else {
                            fillPersonalInformation(usernameRandom("qkilled"), "qwertyui", "qwertyui");
                            submit();
                        }
                    }
                } else {
                    fillPersonalInformation(usernameRandom("qkilled"), "qwertyui", "qwertyui");
                    submit();
                }
            }
        } else {
            fillPersonalInformation(usernameRandom("qkilled"), "qwertyui", "qwertyui");
            submit();
        }

        //sprawdzanie tekstu elementu DOM
        assertEquals("Strength", driver.findElement(By.cssSelector("#tableAddUndead > thead > tr > th:nth-child(2)")).getText());

        //sprawdza czy element jest wyswietlony
        assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("target/screenshotAfterTest.png"));

        Thread.sleep(3000);
        logOut();
    }

    private boolean checkIfErrorExist(String cssSelector) throws Exception {
        Thread.sleep(3000);
        return driver.findElement(By.cssSelector(cssSelector)).isDisplayed();
    }

    private void fillPersonalInformation(String username, String password, String confPassword) {
        driver.findElement(By.id("usernameInputId")).clear();
        driver.findElement(By.id("usernameInputId")).sendKeys(username);

        driver.findElement(By.id("passwordInputId")).clear();
        driver.findElement(By.id("passwordInputId")).sendKeys(password);

        driver.findElement(By.id("passwordConfirmInputId")).clear();
        driver.findElement(By.id("passwordConfirmInputId")).sendKeys(confPassword);
    }

    private void register() {
        driver.findElement(By.linkText("Register")).click();
    }

    private void submit() {
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

    private String usernameRandom(String username) {
        Integer c;
        Random rand = new Random();
        c = rand.nextInt(30000);
        username = username + c.toString();
        return username;
    }

}