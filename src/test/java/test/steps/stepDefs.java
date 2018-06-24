package test.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import test.hooks.hooks;
import test.pageObjects.fiveDayWeatherForecast;

public class stepDefs {

    WebDriver driver = hooks.driver;

    @Given("^user is in Weather Forecast page$")
    public void weatherForecastPage() throws Throwable{
        fiveDayWeatherForecast page = PageFactory.initElements(driver, fiveDayWeatherForecast.class);

    }
@When("^the user enters city as \"(.*)\"$")
    public void enterCity(String city) {
    fiveDayWeatherForecast page = PageFactory.initElements(driver, fiveDayWeatherForecast.class);
    page.enterCityInput(city);

    }

@Then("^user should see 5 day weather forcast for \"(.*)\"$")
    public void fiveDayWeatherForecast(String city) throws Throwable{
    fiveDayWeatherForecast page = PageFactory.initElements(driver, fiveDayWeatherForecast.class);
    //page.checkCity(city);
    }

@When("^the user clicks on \"(.*)\"$")
    public void userClickonDay(String day){
    fiveDayWeatherForecast page = PageFactory.initElements(driver, fiveDayWeatherForecast.class);
    page.clickDay(day);

}

    @Then("^user should see 3 hourly forcast for that \"(.*)\"$")
    public void dayExtend(String day) throws Throwable{
        fiveDayWeatherForecast page = PageFactory.initElements(driver, fiveDayWeatherForecast.class);
        page.checkItemExpanded(day);

    }

    @Given("user is in Weather Forecast page with 3 hourly forcast for \"(.*)\" displayed")
    public void pageWith3hourlyforcastOpen(String day){
        fiveDayWeatherForecast page = PageFactory.initElements(driver, fiveDayWeatherForecast.class);
        page.clickDay(day);

    }

    @Then("^Status of 3 hourly forecast should be hidden for \"(.*)\"$")
    public void dayHidden(String day) throws Throwable{
        fiveDayWeatherForecast page = PageFactory.initElements(driver, fiveDayWeatherForecast.class);
        page.checkItemCollapsed(day);

    }


    @Then("^user should see 3 hourly forcast for \"(.*)\"$")
    public void summarisedWeather(String day) throws Throwable{
        fiveDayWeatherForecast page = PageFactory.initElements(driver, fiveDayWeatherForecast.class);
            //page.summariseWeather(day);
        //page.maxTempCheck(day);
        //page.minTempCheck(day);
        //page.aggRainfallCheck(day);
            page.conditionCheck(day);
        //page.windSpeedCheck(day);

    }

    @Then("^daily forecast should be summary of th3 three hours data$")
    public void summarisedWeather1(String day) throws Throwable{
        fiveDayWeatherForecast page = PageFactory.initElements(driver, fiveDayWeatherForecast.class);
        page.summariseWeather(day);

    }

    @Then("^all values should be rounded down$")
    public void all_values_should_be_rounded_down() throws Throwable {

    }


}
