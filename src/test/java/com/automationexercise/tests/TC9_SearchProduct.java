package com.automationexercise.tests;

import com.automationexercise.tests.utilities.ConfigurationReader;
import com.automationexercise.tests.utilities.Driver;
import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Locale;

public class TC9_SearchProduct {

    @Test
    public void verify_SearchedProducts_are_visible() {

//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
        Driver.getDriver().get(ConfigurationReader.getProperty("env"));

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

        List<WebElement> tops = Driver.getDriver().findElements(By.xpath("//p[.='Category: Kids > Tops & Shirts']"));
        String topsText = "";
        for (WebElement eachTop : tops) {
            topsText += eachTop.getText() + "";
            Assert.assertTrue(eachTop.getText().toLowerCase().contains("top"));
            System.out.println(eachTop.getAttribute("name"));

        }
        System.out.println(topsText);
    }

}