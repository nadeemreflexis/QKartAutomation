package QKART_SANITY_LOGIN.Module1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
    WebElement parentElement;

    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;
    }

    /*
     * Return title of the parentElement denoting the card content section of a
     * search result
     */
    public String getTitleofResult() {
        String titleOfSearchResult = "";
        // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
        // Find the element containing the title (product name) of the search result and
        // assign the extract title text to titleOfSearchResult
        titleOfSearchResult=this.parentElement.findElement(By.tagName("p")).getText();    
        return titleOfSearchResult;
    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    public Boolean openSizechart() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // Find the link of size chart in the parentElement and click on it
            WebElement sizeChartElement = this.parentElement.findElement(By.tagName("button"));
            sizeChartElement.click();
        Thread.sleep(3000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);
            action.sendKeys(Keys.ESCAPE);
            action.perform();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Check if the size chart element exists. If it exists, check if the text of
             * the element is "SIZE CHART". If the text "SIZE CHART" matches for the
             * element, set status = true , else set to false
             */
            WebElement sizeCharElement = this.parentElement.findElement(By.tagName("button"));
            if (sizeCharElement.isDisplayed()) {
                if(sizeCharElement.getText().toUpperCase().equals("SIZE CHART"))
                        status = true;
                    else
                        status = false;
                    return status;
              } 
          } catch (Exception e) {
              return status;
          }
          return status;
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the
     * expected values
     */
public Boolean validateSizeChartContents(List<String> expectedTableHeaders, List<List<String>> expectedTableBody,
            WebDriver driver) {
        // Boolean headerStatus = false, status=false;
        boolean status = false;

         try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Locate the table element when the size chart modal is open
             * 
             * Validate that the contents of expectedTableHeaders is present as the table
             * header in the same order
             * 
             * Validate that the contents of expectedTableBody are present in the table body
             * in the same order
             */
        	// int colElementCount =0;
            // List<WebElement> tableHeaders = driver.findElements(By.xpath("//table/thead/tr/th"));
            // for (WebElement tableHeader : tableHeaders) {
            //     for (String expectedTableHeader : expectedTableHeaders) {
            //         if (tableHeader.getText().trim().equals(expectedTableHeader)) {
            //             colElementCount = colElementCount+1;
            //             break;
            //         }
            //     } 
            // }
            // if(colElementCount == tableHeaders.size()) {
            // 	headerStatus = true;
            // }
            // List<List<String>> actualTableBody = new ArrayList<List<String>>();
            // List<WebElement> tableBodyRows = driver.findElements(By.xpath("//table/tbody/tr"));
            //     for(int i=1;i<=tableBodyRows.size();i++) {
            //     	// specific row
            //     	String specificRow = "//table/tbody/tr["+ i +"]";
            //     	List<WebElement> allSpecificRowElements = driver.findElement(By.xpath(specificRow))
        	// 				.findElements(By.tagName("td"));
            //     	List<String> eachRowElements = new ArrayList<String>();
            //     	for (WebElement rowElement : allSpecificRowElements) {
            //     		eachRowElements.add(rowElement.getText().trim());
            //         }
            //     	actualTableBody.add(eachRowElements);
            //    }
            // if (headerStatus && actualTableBody.equals(
            //         expectedTableBody)) 
            //     status = true;
            // return status;
            WebElement sizeChartParent = driver.findElement(By.className("MuiTableContainer-root"));
            WebElement tableElement = sizeChartParent.findElement(By.tagName("table"));
            List<WebElement> tableHeader = tableElement.findElement(By.tagName("thead")).findElements(By.tagName("th"));

            // Check table headers match
            String tempHeaderValue;
            for (int i = 0; i < expectedTableHeaders.size(); i++) {
                tempHeaderValue = tableHeader.get(i).getText();

                if (!expectedTableHeaders.get(i).equals(tempHeaderValue)) {
                    System.out.println("Failure in Header Comparison: Expected:  " + expectedTableHeaders.get(i)
                            + " Actual: " + tempHeaderValue);
                    status = false;
                }
            }

            List<WebElement> tableBodyRows = tableElement.findElement(By.tagName("tbody"))
                    .findElements(By.tagName("tr"));

            // Check table body match
            List<WebElement> tempBodyRow;
            for (int i = 0; i < expectedTableBody.size(); i++) {
                tempBodyRow = tableBodyRows.get(i).findElements(By.tagName("td"));

                for (int j = 0; j < expectedTableBody.get(i).size(); j++) {
                    tempHeaderValue = tempBodyRow.get(j).getText();

                    if (!expectedTableBody.get(i).get(j).equals(tempHeaderValue)) {
                        System.out.println("Failure in Body Comparison: Expected:  " + expectedTableBody.get(i).get(j)
                                + " Actual: " + tempHeaderValue);
                        status = false;
                    }
                }
            }
            return status;
        } catch (Exception e) {
            System.out.println("Error while validating chart contents");
            return false;
        }
    }

    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // If the size dropdown exists and is displayed return true, else return false
            WebElement sizeDropdown = driver.findElement(By.id("uncontrolled-native"));
            if(sizeDropdown.isDisplayed())
                    status = true;
                return status;
        } catch (Exception e) {
            return status;
        }
    }
}