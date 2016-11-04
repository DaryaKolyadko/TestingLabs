@Run
Feature: Log in process

  Scenario Outline: Login
    Given I open the browser
    When I open test site
    When I navigate to the <targetPage>
    And I enter email <email>
    And I enter password <password>
    Then I push login button
    Then Then the result is <resultPage>
    Then I close browser

    Examples:
      | targetPage | email         | password         | resultPage       |
      | LoginPage  | correct_email | correct_password | FeedPage         |
      | LoginPage  | lalala        | lalala           | LoginFailurePage |