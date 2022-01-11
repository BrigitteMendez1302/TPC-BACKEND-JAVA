#US 09
Feature: Select tutoring modality

  Scenario: Sign up for tutoring
    Given that a student reaches the option of choosing a tutoring modality in his reservation
    When the student chooses to save it with the individual modality option
    Then the system saves your reservation in individual mode and receive a message "Ok"

  Scenario: Enroll in group tutoring
    Given that a student reaches the option of choosing a tutoring modality in his reservation
    When the student chooses to save it with the group modality option and try to save
    Then the system sends him a message asking him to register his colleagues "Please register the other users"