package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        System.out.println(i);

        //***************//
       int z = (int) (System.currentTimeMillis()/1000)%3600;
        System.out.println(z);

        User user = new User()
                .setFirstName("Ira")
                .setLastName("Straf")
                .setEmail("str"+z+"@gmail.com")
                .setPassword("Sstraf123456$");
        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");


    }
    @Test
    public void registrationNameEmpty(){

        User user = new User()
                .setFirstName("")
                .setLastName("Straf")
                .setEmail("straf@gmail.com")
                .setPassword("Sstraf123456$");
        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isTextInElementPresent
                (By.xpath("//div[@class='error']"), "Name is required", 5));
        Assert.assertTrue(app.getHelperUser().isYallaButtunNotActive());
    }
    @Test
    public void registrationLastNameEmpty() {

        User user = new User()
                .setFirstName("Anna")
                .setLastName("")
                .setEmail("straf@gmail.com")
                .setPassword("Sstraf123456$");
        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isTextInElementPresent(By.xpath("//div[@class='error']"), "Last name is required", 5));
        Assert.assertTrue(app.getHelperUser().isYallaButtunNotActive());
    }
        @Test
    public void registrationEmailEmpty(){

        User user = new User()
                .setFirstName("Anna")
                .setLastName("Straf")
                .setEmail("")
                .setPassword("Sstraf123456$");
            logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isTextInElementPresent(By.xpath("//div[@class='error']"), "Email is required", 5));
            Assert.assertTrue(app.getHelperUser().isYallaButtunNotActive());
    }
    @Test
    public void registrationWrongEmail(){

        User user = new User()
                .setFirstName("Anna")
                .setLastName("Straf")
                .setEmail("ssss.gmail.com")
                .setPassword("Sstraf123456$");
        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isTextInElementPresent(By.xpath("//div[@class='error']/div"), "Wrong email format", 5));
        Assert.assertTrue(app.getHelperUser().isYallaButtunNotActive());
    }
    @Test
    public void registrationPasswordEmpty(){

        User user = new User()
                .setFirstName("Anna")
                .setLastName("Straf")
                .setEmail("straf@gmail.com")
                .setPassword("");
        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isTextInElementPresent(By.xpath("//div[@class='error']"), "Password is required", 5));
        Assert.assertTrue(app.getHelperUser().isYallaButtunNotActive());
    }
    @Test
    public void registrationWrongPassword(){

        User user = new User()
                .setFirstName("Anna")
                .setLastName("Straf")
                .setEmail("straf@gmail.com")
                .setPassword("ssssss");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        Assert.assertTrue(app.getHelperUser().isYallaButtunNotActive());
    }
    @AfterMethod
    public void postCondition() {

        app.getHelperUser().closeWindowLoggedIn();
    }

}
