Feature:Tutor modifies his personal data

  Scenario: Tutor modifies personal data
    Given the tutor wants to update his personal info
    When he fills the fields phonenumber, 9986552, firstname "Greg", lastname "Marid"
    Then the system updates his info and returns the response