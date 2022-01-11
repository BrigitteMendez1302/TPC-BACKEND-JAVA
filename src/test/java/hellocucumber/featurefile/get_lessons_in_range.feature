Feature:Get lessons in range

  Scenario: Tutor gets a list of lessons according to a range of dates
    Given the tutor wants to list lessons according to a range of dates
    When the tutor enters the params start "05/05/2021", end "05/07/2021"
    Then the result for this scenario should be the number of lessons in this range 3

  Scenario: Tutor doens't get any list of lessons according to a range of dates
    Given the tutor wants to list lessons according to a range of dates
    When the tutor enters the params start "05/05/2021", end "05/07/2021"
    And any lesson is returned start "05/05/2021", end "05/07/2021"
    Then the message that returns for this scenario should return 0