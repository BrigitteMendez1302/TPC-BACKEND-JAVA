Feature: LessonStudentSignUp
  Scenario: Student signs up for a lesson
    Given the Student is created
    And the lesson is created
    When the student signs up for the first time for this lesson
    Then what returns should be ""

  Scenario: Student signs up for the same lesson
    Given the Student is created
    And the lesson is created
    When the student signs up for the second time for this lesson
    Then the returned result should be "You are already part of this lesson"

  Scenario: Student signs up for a full lesson
    Given the Student is created
    And the lesson is created
    When the student signs up for the a lesson that is full
    Then the result for this operation should be "This lesson is full"