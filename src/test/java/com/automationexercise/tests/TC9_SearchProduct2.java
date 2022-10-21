package com.automationexercise.tests;

import com.automationexercise.tests.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Locale;

public class TC9_SearchProduct2 {

    @Test
    public void verify_SearchedProducts_are_visible() {

//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
        Driver.getDriver().get("http://automationexercise.com");

//        3. Verify that home page is visible successfully
        WebElement homepage = Driver.getDriver().findElement(By.xpath("//a[@style='color: orange;']"));
        homepage.click();

//        4. Click on 'Products' button
        WebElement productsBtn = Driver.getDriver().findElement(By.xpath("//i[@style='font-size: 16px;']"));
        productsBtn.click();

//        5. Verify user is navigated to ALL PRODUCTS page successfully
        WebElement allProducts = Driver.getDriver().findElement(By.xpath("//h2[@class='title text-center']"));
        Assert.assertTrue(allProducts.isDisplayed());

//        6. Enter product name in search input and click search button
        WebElement searchBox = Driver.getDriver().findElement(By.xpath("//input[@id='search_product']"));
        searchBox.sendKeys("tops");
        WebElement searchBtn = Driver.getDriver().findElement(By.xpath("//button[@id='submit_search']"));
        searchBtn.click();

//        7. Verify 'SEARCHED PRODUCTS' is visible
        WebElement searchedProducts = Driver.getDriver().findElement(By.xpath("//h2[@class='title text-center']"));
        Assert.assertTrue(searchedProducts.isDisplayed());

//        8. Verify all the products related to search are visible
        List<WebElement> tops = Driver.getDriver().findElements(By.xpath("//div[@class='col-sm-4']/div/div/div/p"));
        WebElement eachTopsView = null;
        WebElement eachCategory = null;


        JavascriptExecutor js = ((JavascriptExecutor) Driver.getDriver());
        Actions action = new Actions(Driver.getDriver());
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 8);

        for (int i = 0; i < tops.size(); i++) {

            if (i == 0) {
                js.executeScript("window.scrollBy(0,300)");
            }

            wait.until(ExpectedConditions.titleIs("Automation Exercise - All Products"));
            eachTopsView = Driver.getDriver().findElement(By.xpath("(//a[text()='View Product'])[" + (i + 1) + "]"));
            action.moveToElement(eachTopsView);
//            System.out.println(tops.get(i).getText());

            try {
                eachTopsView.click();
            } catch (Exception e) {
                action.moveToElement(eachTopsView);
                js.executeScript("window.scrollBy(0,200)");
                eachTopsView.click();
            }

            wait.until(ExpectedConditions.titleIs("Automation Exercise - Product Details"));
            eachCategory = Driver.getDriver().findElement(By.xpath("(//div/p)[3]"));


            System.out.println(eachCategory.getText());
            Assert.assertTrue(eachCategory.getText().toUpperCase(Locale.ROOT).contains("TOP"));
            Driver.getDriver().navigate().back();

        }
    }
}