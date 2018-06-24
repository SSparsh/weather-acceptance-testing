@test
Feature: Enter city name, get 5 day weather forecast
Scenario Outline: Test with multiple cities
    Given user is in Weather Forecast page
    When the user enters city as "<city>"
    Then user should see 5 day weather forcast for "<city>"

    Examples:
    | city      |
    | aberdeen  |
    | dundee    |
    | edinburgh |
    | glasgow   |
    | perth     |
    | stirling  |