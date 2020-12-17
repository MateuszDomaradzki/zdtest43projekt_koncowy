Feature: basic dev to functionalities
  Scenario: Select first podcast and play it
    Given DevTo main page is open
    When User click on podcasts
    And User select first podcast
    Then User can see its title
    And User can play it
  Scenario: Open First Video Page
    Given DevTo main page is open
    When User click on Videos button
    And User click on first Video
    Then Video page is open
  Scenario: Open Tags on First Page
    Given DevTo page is open
    When User click on Tag button
    Then Page with Tags is open
Scenario: Searching for right phrase
  Given DevTo main page is open
  When  User search "testing"
  Then  Top result should contain the word "testing"