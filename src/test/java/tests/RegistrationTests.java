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
                .setEmail("Straf"+z+"@gmail.com")
                .setPassword("Sstraf123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");


    }
    @Test
    public void registrationNameEmpty(){
        int z = (int) (System.currentTimeMillis()/1000)%3600;

        User user = new User()
                .setFirstName("")
                .setLastName("Straf")
                .setEmail("straf@gmail.com")
                .setPassword("Sstraf123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isTextInElementPresent(By.xpath("//div[@class='error']"), "Name is required", 5));
    }
    @Test
    public void registrationLastNameEmpty() {
        int z = (int) (System.currentTimeMillis() / 1000) % 3600;

        User user = new User()
                .setFirstName("Anna")
                .setLastName("")
                .setEmail("straf@gmail.com")
                .setPassword("Sstraf123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isTextInElementPresent(By.xpath("//div[@class='error']"), "Last name is required", 5));
    }
        @Test
    public void registrationEmailEmpty(){
        int z = (int) (System.currentTimeMillis()/1000)%3600;

        User user = new User()
                .setFirstName("Anna")
                .setLastName("Straf")
                .setEmail("")
                .setPassword("Sstraf123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isTextInElementPresent(By.xpath("//div[@class='error']"), "Email is required", 5));
    }
    @Test
    public void registrationWrongEmail(){
        int z = (int) (System.currentTimeMillis()/1000)%3600;

        User user = new User()
                .setFirstName("Anna")
                .setLastName("Straf")
                .setEmail("ssss.gmail.com")
                .setPassword("Sstraf123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isTextInElementPresent(By.xpath("//div[@class='error']/div"), "Wrong email format", 5));
    }
    @Test
    public void registrationPasswordEmpty(){
        int z = (int) (System.currentTimeMillis()/1000)%3600;

        User user = new User()
                .setFirstName("Anna")
                .setLastName("Straf")
                .setEmail("straf@gmail.com")
                .setPassword("");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isTextInElementPresent(By.xpath("//div[@class='error']"), "Password is required", 5));
    }
    @AfterMethod
    public void postCondition() {

        app.getHelperUser().closeWindowLoggedIn();
    }

}
