
@test
Feature: Select day, get 3 hourly forecast
  Scenario Outline:
    Given user is in Weather Forecast page
    When the user clicks on "<day>"
    Then user should see 3 hourly forcast for that "<day>"
    Examples:
    | day  |
    | day0 |
    | day1 |
    | day2 |
    | day3 |