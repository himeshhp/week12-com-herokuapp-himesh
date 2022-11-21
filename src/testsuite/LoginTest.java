package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseURL = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseURL);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        //Find username and enter in field element
        driver.findElement(By.name("username")).sendKeys("tomsmith");

        //Find password and enter in field element
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");

        //Find login button and click
        driver.findElement(By.xpath("//button/i[@class='fa fa-2x fa-sign-in']")).click();

        //Verify dashboard text after login
        String expectedMessage = "Secure Area";

        //Find Log out text element and get the text
        WebElement actualTextMessageElement = driver.findElement(By.xpath("//h2[text()=' Secure Area']"));
        String actualMessage = actualTextMessageElement.getText();

        //Validate actual and expected
        Assert.assertEquals("Text not displayed", expectedMessage, actualMessage);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        //Find username and enter in field element
        driver.findElement(By.name("username")).sendKeys("tomsmith1");

        //Find password and enter in field element
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");

        //Find login button and click
        driver.findElement(By.xpath("//button/i[@class='fa fa-2x fa-sign-in']")).click();

        //Verify dashboard text after login
        String expectedMessage = "Your username is invalid!\n×";

        //Find Log out text element and get the text
        WebElement actualTextMessageElement = driver.findElement(By.xpath("//div[@id='flash']"));
        String actualMessage = actualTextMessageElement.getText();

        //Validate actual and expected
        Assert.assertEquals("Error message not displayed", expectedMessage, actualMessage);
    }

    @Test
    public void verifyThePasswordErrorMessage() {
        //Find username and enter in field element
        driver.findElement(By.name("username")).sendKeys("tomsmith");

        //Find password and enter in field element
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword");

        //Find login button and click
        driver.findElement(By.xpath("//button/i[@class='fa fa-2x fa-sign-in']")).click();

        //Verify dashboard text after login
        String expectedMessage = "Your password is invalid!\n×";

        //Find Log out text element and get the text
        WebElement actualTextMessageElement = driver.findElement(By.xpath("//div[@id='flash']"));
        String actualMessage = actualTextMessageElement.getText();

        //Validate actual and expected
        Assert.assertEquals("Error message not displayed", expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
