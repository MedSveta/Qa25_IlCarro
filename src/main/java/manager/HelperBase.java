package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text) {
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        clearNew(element);
        if (text != null) {
            element.sendKeys(text);
        }
    }

    public void clearNew(WebElement element) {
        element.sendKeys("a");
        element.sendKeys(Keys.BACK_SPACE);
    }

    public void submit() {
        click(By.xpath("//button[@type='submit']"));
    }

    public void click(By locator) {
        WebElement element = wd.findElement(locator);
        element.click();
    }

    public boolean isElementPresent(By locator) {
        List<WebElement> list = wd.findElements(locator);
        return list.size() > 0;
    }

    public String getMessage() {
        pause(5000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }

    public boolean isTextInElementPresent(By locator, String text, int time) {
        try {
            return new WebDriverWait(wd, 5)
                    .until(ExpectedConditions.textToBePresentInElement(wd.findElement(locator), text));
        } catch (TimeoutException exception) {
            exception.printStackTrace();
            System.out.println("Create exception");
            return false;
        }
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void getScreen(String link) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp, new File("src/test/resources/screenshots/"+link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getErrorText() {
        return wd.findElement(By.cssSelector("div.error")).getText();
    }
    public boolean isYallaButtunNotActive() {
        boolean res = isElementPresent(By.cssSelector("button[disabled]"));

        WebElement element = wd.findElement(By.cssSelector("button[type='submit']"));
        boolean result = element.isEnabled();
        return res && !result;
    }
    public void clearTextBox(By locator) {
        WebElement element = wd.findElement(locator);
        String os = System.getProperty("os.name");
        if (os.startsWith("Win")) {
            element.sendKeys(Keys.CONTROL, "a");

        } else {

            element.sendKeys(Keys.COMMAND, "a");
        }
        element.sendKeys(Keys.DELETE);
    }
}
