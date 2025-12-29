Feature: Get weather details for a city

  Scenario Outline: Get weather for City
    Given the weather API is available
    When I request the weather for "<City>"
    Then the response status code should be 200
    And the temperature should be in metric units

    Examples: 
      | City       |
      | Chennai,IN |
      | Mumbai,IN  |