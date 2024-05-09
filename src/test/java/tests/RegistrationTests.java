package tests;

import models.User;
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
                .setEmail("Straf"+z+"gmail.com")
                .setPassword("Sstraf123456$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm();
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();


    }

}
