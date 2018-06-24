Feature: Daily forecast should summarise the 3 hour data:

  Most dominant (or current) condition
  Most dominant (or current) wind speed and direction
  Aggregate rainfall
  Minimum and maximum temperatures

  @test
  Scenario Outline:
    Given user is in Weather Forecast page
    When the user clicks on "<day>"
    Then user should see 3 hourly forcast for "<day>"
    Examples:
    | day  |
    | Day0 |
    | Day1 |
    | Day2 |
    | Day3 |
    | Day4 |
