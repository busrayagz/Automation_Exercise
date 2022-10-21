package com.automationexercise.tests.utilities;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BrowserUtils {

    public static void sleep(int seconds){

        try{
            Thread.sleep(seconds*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.println("Exception happened in the sleep method!");
        }
    }

    public static void verifyTitle(WebDriver driver, String expectedTitle){

        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);

    }
}
