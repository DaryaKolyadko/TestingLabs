@Run
Feature: Open specific page after log in

  Scenario Outline: Open page
    Given I open the browser
    When I open test site
    When I navigate to the <startPage>
    And I enter email <email>
    And I enter password <password>
    Then I push login button
    And Then the result is <feedPage>
    Then I click on friends page
    And Then the result is <targetPage>
    Then I close browser

    Examples:
      | startPage | email         | password         | feedPage | targetPage  |
      | LoginPage | correct_email | correct_password | FeedPage | FriendsPage |