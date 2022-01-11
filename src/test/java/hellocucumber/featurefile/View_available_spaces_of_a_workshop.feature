#US 04
Feature:View available spaces of a workshop

  Scenario: Visualization of available workshop spaces
    Given the student wishes to register for a workshop
    When the student views the list of workshops or a specific one
    Then see that the workshops show the number of available seats they have.

  Scenario: Viewing unavailable workshop spaces
    Given the student wishes to register for a workshop
    When the student views the list of workshops or a specific one
    Then see that unavailable workshops only show the message "#vacancies/#vacancies Full Workshop"
