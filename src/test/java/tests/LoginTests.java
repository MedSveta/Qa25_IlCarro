package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void LoginSuccess() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("sveta12345@gmail.com", "1234567$Ru");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void LoginSuccessModel() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("sveta12345@gmail.com", "1234567$Ru");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void LoginNegativeWrongEmail() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("sveta12345gmail.com", "1234567$Ru");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtunNotActive());

    }

    @Test
    public void LoginNegativeWrongPassword() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("sveta12345@gmail.com", "1234567");
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().getMessage().contains("Login or Password incorrect"));
    }

    @Test
    public void LoginNegativeUnregisteredUser() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("sveta12345rec@gmail.com", "1234567$Rus");
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().getMessage().contains("Login or Password incorrect"));
    }

    @AfterMethod
    public void postCondition() {

        app.getHelperUser().closeWindowLoggedIn();
    }
}
