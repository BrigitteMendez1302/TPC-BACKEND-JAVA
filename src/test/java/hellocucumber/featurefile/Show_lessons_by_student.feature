#US 06
Feature:View list of reservations

  Scenario: Visualization of Lesson reservations by Student
    Given the student wishes to see all his reservations
    When the student views the list of lessons
    Then see a list of reservations

  Scenario: Visualization when the student has no reservation
    Given the student wishes to see all his reservations
    When the student views the list of lessons and there are no results
    Then system displays the message "There are no reservations"