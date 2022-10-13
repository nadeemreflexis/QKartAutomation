package QKART_SANITY_LOGIN.Module1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app";

    public Home(RemoteWebDriver driver) {
    this.driver = driver;
    }

    public void navigateToHome() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
}
    }

    public Boolean PerformLogout() throws InterruptedException {
        try {
            // Find and click on the Logout Button
            WebElement logout_button = driver.findElement(By.className("MuiButton-text"));
            logout_button.click();

            // SLEEP_STMT_10: Wait for Logout to complete
            // Wait for Logout to Complete
            Thread.sleep(3000);

            return true;
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }

    /*
     * Returns Boolean if searching for the given product name occurs without any
     * errors
     */
    public Boolean searchForProduct(String product) {
        WebElement searchBoxElement;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Clear the contents of the search box and Enter the product name in the search
            // box
            searchBoxElement = driver.findElement(By.name("search"));
            searchBoxElement.clear();
            searchBoxElement.sendKeys(product);
            Thread.sleep(3000);
            // WebDriverWait wait = new WebDriverWait(driver, 30);
            // wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[contains(@class,'css-1qw96cp')]/p"), product));
            return true;
        } catch (Exception e) {
            System.out.println("Error while searching for a product: " + e.getMessage());
            return false;
        }
    }

    /*
     * Returns Array of Web Elements that are search results and return the same
     */
public List<WebElement> getSearchResults() {
        List<WebElement> searchResults = new ArrayList<WebElement>() {
        };
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Find all webelements corresponding to the card content section of each of
            // search results
            searchResults = driver.findElements(By.className("MuiCardContent-root"));
            return searchResults;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return searchResults;

        }
    }

    /*
     * Returns Boolean based on if the "No products found" text is displayed
     */
    public Boolean isNoResultFound() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Check the presence of "No products found" text in the web page. Assign status
            // = true if the element is *displayed* else set status = false
            WebElement NoProductElement =
                    driver.findElement(By.cssSelector(".loading.MuiBox-root>h4"));
            status = NoProductElement.isDisplayed();
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if add product to cart is successful
     */
    public Boolean addProductToCart(String productName) {
        try {
            /*
             * Iterate through each product on the page to find the WebElement corresponding
             * to the matching productName
             * 
             * Click on the "ADD TO CART" button for that element
             * 
             * Return true if these operations succeeds
             */
            List<WebElement> searchedProducts =
                    driver.findElements(By.className("MuiCardContent-root"));
            WebElement addToCarElement = driver.findElement(By.cssSelector(".MuiCardActions-root>button"));
            for (WebElement searchedProduct : searchedProducts) {
                String searchProductName = searchedProduct.findElement(By.tagName("p")).getText();
                if (searchProductName.trim().equals(productName)) {
                    addToCarElement.click();
                    WebDriverWait wait = new WebDriverWait(driver, 30);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='MuiBox-root css-1gjj37g']/div[1][text()='"+productName+"']")));
                    return true;
                }
            }
            System.out.println("Unable to find the given product: " + productName);
            return false;
        } catch (Exception e) {
            System.out.println("Exception while performing add to cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting the status of clicking on the checkout button
     */
    public Boolean clickCheckout() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find and click on the the Checkout button
            WebElement checkoutButton = driver.findElement(By.cssSelector(".cart-footer>button"));
            checkoutButton.click();
            status = true;
            return status;
        } catch (Exception e) {
            System.out.println("Exception while clicking on Checkout: " + e.getMessage());
            return status;
        }
    }

    /*
     * Return Boolean denoting the status of change quantity of product in cart
     * operation
     */
    public Boolean changeProductQuantityinCart(String productName, int quantity) {
        boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 06: MILESTONE 5

            // Find the item on the cart with the matching productName
            WebElement parentCartElement =
                    driver.findElement(By.xpath("//div[contains(@class,'cart MuiBox-root')]"));
            List<WebElement> cartElements = parentCartElement
                    .findElements(By.xpath("//div[@class='MuiBox-root css-1gjj37g']"));
            for (WebElement cartElement : cartElements) {
                WebElement cartProductName = cartElement.findElement(By.tagName("div"));
                if (cartProductName.getText().equals(productName)) {
                    WebElement currentProductQuantity =
                            cartElement.findElement(By.xpath("//div[contains(text(),'" + productName
                                    + "')]/following-sibling::div//div[@data-testid='item-qty']"));
                    int currentQuantity = Integer.valueOf(currentProductQuantity.getText());
                    WebElement increaseQuantityButton =
                            cartElement.findElement(By.xpath("//div[contains(text(),'" + productName
                                    + "')]/following-sibling::div//button[2]"));
                    WebElement decreaseQuantityButton =
                            cartElement.findElement(By.xpath("//div[contains(text(),'" + productName
                                    + "')]/following-sibling::div//button[1]"));
                    WebDriverWait wait;
                    while (currentQuantity != quantity) {
                        if (currentQuantity < quantity) {
                            increaseQuantityButton.click();
                            wait = new WebDriverWait(driver, 30);
                            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[contains(text(),'" + productName
                                    + "')]/following-sibling::div//div[@data-testid='item-qty']"), String.valueOf(
                                            currentQuantity + 1)));
                            currentQuantity++;
                            status = true;
                        } else if (currentQuantity > quantity) {
                            decreaseQuantityButton.click();
                            wait = new WebDriverWait(driver, 30);
                            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                                    By.xpath("//div[contains(text(),'" + productName
                                            + "')]/following-sibling::div//div[@data-testid='item-qty']"),
                                    String.valueOf(currentQuantity - 1)));
                            if (currentQuantity > 1) {
                                currentQuantity = Integer.valueOf(currentProductQuantity.getText());
                            }
                            else if (currentQuantity == 1) {
                                currentQuantity--;
                            }
                            status = true;
                        } else {
                            status = false;
                        }
                    }
                }
            }
            return status;
        } catch (Exception e) {
            if (quantity == 0)
                return true;
            System.out.println("exception occurred when updating cart: " + e.getMessage());
            return false;
        }
    }
    
    /*
     * Return Boolean denoting if the cart contains items as expected
     */
    public Boolean verifyCartContents(List<String> expectedCartContents) {
        boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 07: MILESTONE 6

            // Get all the cart items as an array of webelements
            List<WebElement> cartElements = driver.findElements(By.xpath(
                    "//div[contains(@class,'cart MuiBox-root')]//div[contains(@class,'image')]/following-sibling::div"));

            // Iterate through expectedCartContents and check if item with matching product
            for (WebElement cartElement : cartElements) {
                WebElement cartProductName = cartElement.findElement(By.tagName("div"));
                for(String expectedCartContent:expectedCartContents){
                    if (cartProductName.getText().equalsIgnoreCase(expectedCartContent)) {
                        status = true;
                        break;
                    }
                }
            }
            // name is present in the cart
            return status;
        } catch (Exception e) {
            System.out.println("Exception while verifying cart contents: " + e.getMessage());
            return false;
        }
    }
}

