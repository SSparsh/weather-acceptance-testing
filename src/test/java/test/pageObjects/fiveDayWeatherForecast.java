package test.pageObjects;

//import org.graalvm.compiler.loop.MathUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import test.hooks.hooks;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.*;

import static javax.swing.text.html.CSS.getAttribute;

public class fiveDayWeatherForecast {

    WebDriver driver = hooks.driver;
    SoftAssert softAssert = new SoftAssert();

    public fiveDayWeatherForecast(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterCityInput(String city) {
        //cityInput.click();
        cityInput.clear();
        cityInput.sendKeys(city+Keys.ENTER);
        //System.out.println(city);
    }

    public void checkCity(String city){
        String actualCity = cityInput.getText();
        softAssert.assertEquals(city,actualCity);
        softAssert.assertAll();
    }

    public void clickDay(String day){
        String lastChar = day.substring(day.length() - 1); //get the last character
        int numOfDay = Integer.parseInt(lastChar); //convert string to integer

        driver.findElements(By.className("summary")).get(numOfDay).click();

        //day.click();
    }

    public void checkItemExpanded(String day){
        String lastChar = day.substring(day.length() - 1); //get the last character
        int numOfDay = Integer.parseInt(lastChar); //convert string to integer
        //check Style max height is 2000
        String Style = driver.findElements(By.className("details")).get(numOfDay).getAttribute("style");
        if(!Style.contains("max-height: 2000px"))
        {
            Assert.fail("3 hourly forecast is hidden");
        }
    }

    public void checkItemCollapsed(String day){
        //check Style max height is 2000
        String Style = driver.findElement(By.className("details")).getAttribute("style");
        if(Style.contains("max-height: 2000px"))
        {
            Assert.fail("3 hourly forecast is displayed");
        }
    }

    public void summariseWeather(String day){
        String lastChar = day.substring(day.length() - 1); //get the last character
        int numOfDay = Integer.parseInt(lastChar); //convert string to integer
        int child = numOfDay+2;
        //check Style max height is 2000
        String condition = driver.findElements(By.cssSelector("#root > div:nth-child(1) > div:nth-child("+child+") > div:nth-child(1) > span:nth-child(2) > svg:nth-child(1)")).get(0).getAttribute("aria-label");
        String windSpeed = driver.findElements(By.cssSelector("#root > div > div:nth-child("+child+") > div.summary > span:nth-child(4) > span.speed")).get(0).getText();
        String style = driver.findElements(By.cssSelector("#root > div:nth-child(1) > div:nth-child("+child+") > div:nth-child(1) > span:nth-child(4) > span:nth-child(2) > svg:nth-child(1)")).get(0).getAttribute("style");
        String a = style.substring(style.lastIndexOf(" ")+1);
        String windDirection = a.substring(7,a.length()-2);
        String rainfall = driver.findElements(By.cssSelector("#root > div > div:nth-child("+child+") > div.summary > span:nth-child(5) > span.rainfall")).get(0).getText();
        String maxTemp = driver.findElements(By.cssSelector("#root > div > div:nth-child("+child+") > div.summary > span:nth-child(3) > span.max")).get(0).getText();
        String minTemp = driver.findElements(By.cssSelector("#root > div > div:nth-child("+child+") > div.summary > span:nth-child(3) > span.rmq-5ea3c959.min")).get(0).getText();

        System.out.println(condition+"   "+windSpeed+"   "+windDirection+"   "+rainfall+"   "+maxTemp+"   "+minTemp);

    }

    public void maxTempCheck(String day){
        String lastChar = day.substring(day.length() - 1); //get the last character
        int numOfDay = Integer.parseInt(lastChar); //convert string to integer
        int z = numOfDay+1;
        int child = numOfDay+2;


        String DailyForcastTemp = driver.findElement(By.cssSelector("#root > div:nth-child(1) > div:nth-child("+child+") > div:nth-child(1) > span:nth-child(3) > span:nth-child(1)")).getText();
        String first = DailyForcastTemp.substring(0, DailyForcastTemp.length() - 1);
        int DailyAvgTemp = Integer.parseInt(first);

//        System.out.println("Daily Avg Temp: "+DailyAvgTemp);

        //get number of elements (rows)
        int n = driver.findElements(By.cssSelector("#root > div > div:nth-child("+child+") > div.details > div.detail")).size();
  //      System.out.println(n);


        List<Integer> tempAll = new ArrayList<Integer>();

        for (int i=1; i<n+1;i++)
        {
            String text = driver.findElement(By.cssSelector("#root > div > div:nth-child(" + child + ") > div.details > div:nth-child("+i+") > span:nth-child(3) > span.max")).getText();
            int temp = Integer.parseInt(text.substring(0,text.length()-1)); //remove last character degrees and convert it to int
            //System.out.println(maxTemp);
            tempAll.add(temp);
        }

        int maxTemp = Collections.max(tempAll);

//        System.out.print(tempAll);
//        System.out.println("Max Daily Temp: = "+Collections.max(tempAll));

        //Check max daily temperature  matches the max temperature  from 3-hourly forecast
        softAssert.assertEquals(maxTemp,DailyAvgTemp);
        softAssert.assertAll();

    }

    public void minTempCheck(String day){
        String lastChar = day.substring(day.length() - 1); //get the last character
        int numOfDay = Integer.parseInt(lastChar); //convert string to integer
        int z = numOfDay+1;
        int child = numOfDay+2;

        //get number of elements (rows)
        int n = driver.findElements(By.cssSelector("#root > div > div:nth-child("+child+") > div.details > div.detail")).size();
        System.out.println(n);


        String minAvgDailyTemp = driver.findElement(By.cssSelector("#root > div:nth-child(1) > div:nth-child("+child+") > div:nth-child(1) > span:nth-child(3) > span:nth-child(2)")).getText();
        System.out.println(minAvgDailyTemp);
        String first = minAvgDailyTemp.substring(0, minAvgDailyTemp.length() - 1);
        int minDailyAvgTemp = Integer.parseInt(first);


        List<Integer> tempAll = new ArrayList<Integer>();

        for (int i=1; i<n+1;i++)
        {
            String text = driver.findElement(By.cssSelector("#root > div > div:nth-child(" + child + ") > div.details > div:nth-child("+i+") > span:nth-child(3) > span.max")).getText();
            int temp = Integer.parseInt(text.substring(0,text.length()-1)); //remove last character degrees and convert it to int
            //System.out.println(maxTemp);
            tempAll.add(temp);
        }

        int minTemp = Collections.min(tempAll);

        //Check min daily temperature  matches the min temperature from 3-hourly forecast
        softAssert.assertEquals(minTemp,minDailyAvgTemp);
        softAssert.assertAll();
    }

    public void aggRainfallCheck(String day){
        String lastChar = day.substring(day.length() - 1); //get the last character
        int numOfDay = Integer.parseInt(lastChar); //convert string to integer
        int z = numOfDay+1;
        int child = numOfDay+2;

        //get number of elements (rows)
        int n = driver.findElements(By.cssSelector("#root > div > div:nth-child("+child+") > div.details > div.detail")).size();
        System.out.println(n);

        //get agg rainfall
        String aggRAinfall = driver.findElement(By.cssSelector("#root > div:nth-child(1) > div:nth-child("+child+") > div:nth-child(1) > span:nth-child(5) > span:nth-child(1)")).getText();
        String first = aggRAinfall.substring(0, aggRAinfall.length() - 2);
        int dailyAggRainFall = Integer.parseInt(first); //convert String to int
        int sum = 0;


        List<Integer> tempAll = new ArrayList<Integer>();

        for (int i=1; i<n+1;i++)
        {
            String text = driver.findElement(By.cssSelector("#root > div:nth-child(1) > div:nth-child("+child+") > div:nth-child(2) > div:nth-child("+i+") > span:nth-child(5) > span:nth-child(1)")).getText();
            int aggRain = Integer.parseInt(text.substring(0,text.length()-2)); //remove last character degrees and convert it to int
            sum = sum + aggRain;
            System.out.println(aggRain);
            tempAll.add(aggRain);
        }

        //Check min daily aggregate rainfall matches the total rainfall of that day
        softAssert.assertEquals(dailyAggRainFall,sum,"Daily Aggregate rail fall is not equal to total daily rainfall");
        softAssert.assertAll();
    }


    public void windSpeedCheck(String day){
        String lastChar = day.substring(day.length() - 1); //get the last character
        int numOfDay = Integer.parseInt(lastChar); //convert string to integer
        int z = numOfDay+1;
        int child = numOfDay+2;

        //get number of elements (rows)
        int n = driver.findElements(By.cssSelector("#root > div > div:nth-child("+child+") > div.details > div.detail")).size();

        String avgWindSpeed = driver.findElement(By.cssSelector("#root > div:nth-child(1) > div:nth-child("+child+") > div:nth-child(1) > span:nth-child(4) > span:nth-child(1)")).getText();
        String first = avgWindSpeed.substring(0, avgWindSpeed.length() - 3);
        //System.out.println(first);
        int dailyWindSpeed = Integer.parseInt(first);

        String CurrentWindSpeed = driver.findElement(By.cssSelector("#root > div:nth-child(1) > div:nth-child("+child+") > div:nth-child(2) > div:nth-child(1) > span:nth-child(4) > span:nth-child(1)")).getText();
        System.out.println(CurrentWindSpeed);

        int mostRecentWindSpeed = Integer.parseInt(CurrentWindSpeed.substring(0,CurrentWindSpeed.length()-3));

        //Check min daily wind speed  matches the most Recent windspeed from 3-hourly forecast
        softAssert.assertEquals(dailyWindSpeed,mostRecentWindSpeed);
        softAssert.assertAll();
    }

    public void conditionCheck(String day){
        String lastChar = day.substring(day.length() - 1); //get the last character
        int numOfDay = Integer.parseInt(lastChar); //convert string to integer
        int z = numOfDay+1;
        int child = numOfDay+2;

        //get number of elements (rows)
        int n = driver.findElements(By.cssSelector("#root > div > div:nth-child("+child+") > div.details > div.detail")).size();

        String dailyCondition = driver.findElements(By.cssSelector("#root > div:nth-child(1) > div:nth-child("+child+") > div:nth-child(1) > span:nth-child(2) > svg:nth-child(1)")).get(0).getAttribute("aria-label");

        System.out.println("Daily Condition:"+dailyCondition);

        String currentCondition = driver.findElement(By.cssSelector("#root > div:nth-child(1) > div:nth-child("+child+") > div:nth-child(2) > div:nth-child(1) > span:nth-child(2) > svg:nth-child(1)")).getAttribute("aria-label");

        System.out.println("Current Condition "+currentCondition);

        //Check min daily wind speed  matches the most Recent windspeed from 3-hourly forecast
        softAssert.assertEquals(dailyCondition,currentCondition, "Current Weather condition does not match summary Weather Condition");
        softAssert.assertAll();
    }

    //WebElements
    @FindBy (how = How.ID, using = "city") public WebElement cityInput;
    @FindBy (how = How.CSS, using ="#root > div > div:nth-child(2) > div.summary > span:nth-child(1) > span.name") public WebElement day;
    @FindBy (how=How.CSS, using = "#root > div > div:nth-child(2) > div.summary > span:nth-child(1) > span.rmq-5ea3c959.date") public WebElement date;
    @FindBy (how=How.CSS, using ="#root > div > div:nth-child(2) > div.summary > span:nth-child(2)") public WebElement weather;
    @FindBy (how=How.CSS, using ="#root > div > div:nth-child(2) > div.summary > span:nth-child(3) > span.max") public WebElement tempMax;
    @FindBy (how=How.CSS, using = "#root > div > div:nth-child(2) > div.summary > span:nth-child(3) > span.rmq-5ea3c959.min") public WebElement tempMin;
    @FindBy (how=How.CSS, using = "#root > div > div:nth-child(2) > div.summary > span:nth-child(4) > span.speed") public WebElement windSpeed;
    @FindBy (how=How.CSS, using = "#root > div > div:nth-child(2) > div.summary > span:nth-child(4) > span.rmq-5ea3c959.direction") public WebElement windDirection;
    @FindBy (how=How.CSS, using = "#root > div > div:nth-child(2) > div.summary > span:nth-child(5) > span.rainfall") public WebElement rainFall;
    @FindBy (how=How.CSS, using = "#root > div > div:nth-child(2) > div.summary > span:nth-child(5) > span.rmq-5ea3c959.pressure") public WebElement pressure;
    //    @FindBy (how = How.CSS, using ="#root > div > div:nth-child(2) > div.summary > span:nth-child(2) > span.name") public WebElement Day2;
//    @FindBy (how = How.CSS, using ="#root > div > div:nth-child(2) > div.summary > span:nth-child(3) > span.name") public WebElement Day3;
//    @FindBy (how = How.CSS, using ="#root > div > div:nth-child(2) > div.summary > span:nth-child(4) > span.name") public WebElement Day4;
//    @FindBy (how = How.CSS, using ="#root > div > div:nth-child(2) > div.summary > span:nth-child(5) > span.name") public WebElement Day5;


}
