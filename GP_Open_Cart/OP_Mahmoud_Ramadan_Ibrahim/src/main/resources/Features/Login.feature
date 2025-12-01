Feature: Login
  Scenario: Test Case 2: Login User with correct email and password
    Given the user navigates to homepage
    And the homepage is displayed successfully
    When the user clicks on 'Signup-Login'
    Then "Login to your account" should be visible

    When the user enters email "validemail@example.com" and password "Valid123"
    Then "Logged in as username" should be visible

    When the user clicks "Delete Account"
    Then "ACCOUNT DELETED!" should be visible


  Scenario: Test Case 3: Login User with incorrect email and password
    Given the user navigates to homepage
    And the user clicks on 'Signup-Login'
    When the user clicks on 'Signup-Login'
    Then "Login to your account" should be visible

    When the user enters email "invalid@email.com" and password "Invalid"
    Then the error message should be visible