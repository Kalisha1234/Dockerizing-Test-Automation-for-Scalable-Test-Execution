// package org.example.pages;

// import org.openqa.selenium.By;
// import org.openqa.selenium.JavascriptExecutor;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;
// import java.time.Duration;

// public class CheckoutPage {
//     private WebDriver driver;
//     private WebDriverWait wait;
//     private By firstNameField = By.id("first-name");
//     private By lastNameField = By.id("last-name");
//     private By postalCodeField = By.id("postal-code");
//     private By continueButton = By.id("continue");
//     private By finishButton = By.id("finish");
//     private By completeHeader = By.className("complete-header");
//     private By completeText = By.className("complete-text");

//     public CheckoutPage(WebDriver driver) {
//         this.driver = driver;
//         this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//     }

//     public void fillInformation(String firstName, String lastName, String postalCode) {
//         wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
//         driver.findElement(firstNameField).sendKeys(firstName);
//         driver.findElement(lastNameField).sendKeys(lastName);
//         driver.findElement(postalCodeField).sendKeys(postalCode);
//         driver.findElement(continueButton).click();
//     }

//     public void finishCheckout() {
//         // Wait for the checkout overview page to load
//         wait.until(ExpectedConditions.urlContains("checkout-step-two"));
        
//         // Try multiple possible selectors for the finish button
//         WebElement finishBtn = null;
//         try {
//             finishBtn = wait.until(ExpectedConditions.elementToBeClickable(finishButton));
//         } catch (Exception e) {
//             // Try alternative selectors
//             try {
//                 finishBtn = wait.until(ExpectedConditions.elementToBeClickable(By.name("finish")));
//             } catch (Exception e2) {
//                 try {
//                     finishBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Finish') or contains(text(),'FINISH')]"));
//                 } catch (Exception e3) {
//                     finishBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='finish']")))); 
//                 }
//             }
//         }
        
//         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finishBtn);
//         finishBtn.click();
//     }

//     public boolean isOrderComplete() {
//         try {
//             wait.until(ExpectedConditions.urlContains("checkout-complete"));
//             wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader));
//             return driver.findElement(completeHeader).getText().contains("Thank you for your order") ||
//                    driver.findElement(completeHeader).getText().contains("THANK YOU FOR YOUR ORDER");
//         } catch (Exception e) {
//             // Try alternative selector
//             try {
//                 wait.until(ExpectedConditions.visibilityOfElementLocated(completeText));
//                 return true;
//             } catch (Exception e2) {
//                 return false;
//             }
//         }
//     }
// }

package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By completeHeader = By.className("complete-header");
    private By completeText = By.className("complete-text");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void fillInformation(String firstName, String lastName, String postalCode) {
        WebElement firstNameInput = wait.until(ExpectedConditions.elementToBeClickable(firstNameField));
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        
        WebElement lastNameInput = driver.findElement(lastNameField);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        
        WebElement postalCodeInput = driver.findElement(postalCodeField);
        postalCodeInput.clear();
        postalCodeInput.sendKeys(postalCode);
        
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);
    }

    public void finishCheckout() {
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
        
        WebElement finishBtn = wait.until(ExpectedConditions.presenceOfElementLocated(finishButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finishBtn);
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", finishBtn);
    }

    public boolean isOrderComplete() {
        try {
            wait.until(ExpectedConditions.urlContains("checkout-complete"));
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader));
            String headerText = header.getText().toLowerCase();
            return headerText.contains("thank you for your order");
        } catch (Exception e) {
            return false;
        }
    }
}