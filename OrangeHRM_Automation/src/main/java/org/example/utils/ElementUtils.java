package org.example.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ElementUtils {

    private WebDriver driver;
    private WebDriverWait wait;

    public ElementUtils(WebDriver driver) {
        // FAIL FAST: Check if driver is null immediately
        if (driver == null) {
            throw new IllegalArgumentException("Driver passed to ElementUtils is NULL. Ensure the Page Object is initialized AFTER the browser is launched (inside @Test, not as a class field).");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void robustClear(By locator) {
        // Ensure wait is not null (though constructor guarantees it)
        if (wait == null) throw new IllegalStateException("WebDriverWait is not initialized");

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        // 1. Click to focus
        try {
            element.click();
        } catch (Exception ignored) {}

        // 2. Standard Clear
        try {
            element.clear();
            if (isEmpty(element)) return;
        } catch (Exception ignored) {}

        // 3. CTRL+A + DELETE
        try {
            String selectAll = Keys.chord(Keys.CONTROL, "a");
            element.sendKeys(selectAll);
            element.sendKeys(Keys.DELETE);
            if (isEmpty(element)) return;
        } catch (Exception ignored) {}

        // 4. Backspace loop
        try {
            String value = element.getAttribute("value");
            if (value != null && !value.isEmpty()) {
                for (int i = 0; i < value.length(); i++) {
                    element.sendKeys(Keys.BACK_SPACE);
                }
                if (isEmpty(element)) return;
            }
        } catch (Exception ignored) {}

        // 5. Javascript fallback
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value = '';", element);
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", element);
            try { Thread.sleep(100); } catch (InterruptedException e) {} // Short wait
            if (isEmpty(element)) return;
        } catch (Exception ignored) {}
    }

    private boolean isEmpty(WebElement el) {
        try {
            String val = el.getAttribute("value");
            return val == null || val.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}