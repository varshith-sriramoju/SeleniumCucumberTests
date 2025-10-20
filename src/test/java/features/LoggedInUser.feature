Feature: LoggedIn User View

  Scenario: Validate user signs in from the home page
    Given User opens the site home page (sign-in)
    When User signs in with valid credentials
    Then User should see the dashboard overview page