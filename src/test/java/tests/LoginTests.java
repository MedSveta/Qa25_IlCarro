package tests;

import org.testng.Assert;
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
        app.getHelperUser().submitLogin();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        app.getHelperUser().closeWindowLoggedIn();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }

}
