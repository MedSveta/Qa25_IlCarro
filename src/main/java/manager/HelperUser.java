package manager;

import manager.HelperBase;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.cssSelector("a[href='/login?url=%2Fsearch']"));
    }

    public void fillLoginForm(String email, String password){
        type(By.xpath("//*[@id='email']"), email);
        type(By.xpath("//*[@id='password']"), password);
    }
    public void fillLoginForm(User user){
        type(By.xpath("//*[@id='email']"), user.getEmail());
        type(By.xpath("//*[@id='password']"), user.getPassword());
    }
    public  void submitLogin(){
        click(By.xpath("//button[@type='submit']"));
    }
    public  void closeWindowLoggedIn(){
        click(By.xpath("//button[text()='Ok']"));
    }
    public boolean isLogged() {
        return isElementPresent(By.cssSelector("a[href='/logout?url=%2Fsearch']"));
    }
    public void logout() {
        click(By.cssSelector("a[href='/logout?url=%2Fsearch']"));
    }
}
