@test
Feature: Select day again, hide 3 hourly forecast
  Scenario Outline:
    Given user is in Weather Forecast page with 3 hourly forcast for "<day>" displayed
    When the user clicks on "<day>"
    Then Status of 3 hourly forecast should be hidden for "<day>"
    Examples:
    | day |
    | Day0 |
    | Day1 |
    | Day2 |
    | Day3 |