package QKART_SANITY_LOGIN.Module1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/checkout";

    public Checkout(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCheckout() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    /*
     * Return Boolean denoting the status of adding a new address
     */
    public Boolean addNewAddress(String addresString) {
        try {
            /*
             * Click on the "Add new address" button, enter the addressString in the address
             * text box and click on the "ADD" button to save the address
             */
            WebElement addNewAddressButton = driver.findElement(By.id("add-new-btn"));
            addNewAddressButton.click();
            WebElement addressTextAreaElement =
                    driver.findElement(By.className("MuiOutlinedInput-input"));
            addressTextAreaElement.sendKeys(addresString);
            WebElement addButton = driver.findElement(By.className("MuiButton-contained"));
            addButton.click();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("address-item")));
            return true;
        } catch (Exception e) {
            System.out.println("Exception occurred while entering address: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting the status of selecting an available address
     */
    public Boolean selectAddress(String addressToSelect) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through all the address boxes to find the address box with matching
             * text, addressToSelect and click on it
             */
            List<WebElement> addressList =
                    driver.findElements(By.xpath("//div[contains(@class,'address-item')]//p"));
            for (WebElement address : addressList){
                if(address.getText().equalsIgnoreCase(addressToSelect))
                    address.click();
                else
                    System.out.println("Unable to find the given address");
                break;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Exception Occurred while selecting the given address: " + e.getMessage());
            return false;
        }

    }

    /*
     * Return Boolean denoting the status of place order action
     */
    public Boolean placeOrder() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find the "PLACE ORDER" button and click on it
            WebElement placeOrderButton =
                    driver.findElement(By.xpath("//button[text()='PLACE ORDER']"));
            placeOrderButton.click();
            return true;

        } catch (Exception e) {
            System.out.println("Exception while clicking on PLACE ORDER: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the insufficient balance message is displayed
     */
    public Boolean verifyInsufficientBalanceMessage() {
        boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 08: MILESTONE 7
            WebElement insufficientBalanceElement = driver.findElement(By.id("notistack-snackbar"));
            String insufficientBalanceMessage = insufficientBalanceElement.getText();
            if (insufficientBalanceMessage.trim()
            .equalsIgnoreCase("You do not have enough balance in your wallet for this purchase")) {
                status = true;
            }
        return status;
        } catch (Exception e) {
            System.out.println("Exception while verifying insufficient balance message: " + e.getMessage());
            return false;
        }
    }
}
