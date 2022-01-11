#US 02
Feature: find tutoring by course

  Scenario: find tutoring by course with results
    Given that the student wants to enroll in a workshop
    When the student selects search by course
    Then the system filters and lists the workshops available for that course

  Scenario: find tutoring by course without results
    Given that the student wants to enroll in a workshop
    When the student selects search by course and there are no results
    Then the system displays the message "There are no workshops available for this course"