#US 08
Feature:View list of suggestions

  Scenario: Visualization of Suggestions
    Given the coodinator whishes to see the list of suggestions
    When the coordinator see the list
    Then the system display a list of suggestions

  Scenario: Visualization of Suggestions with a list empty
    Given the coodinator whishes to see the list of suggestions
    When the coordinator see the list of suggestions and there are no results
    Then x system displays the message "There are no suggestions"
