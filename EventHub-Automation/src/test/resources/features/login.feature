
Feature: EventHub customer login page

  Scenario: Open the customer login page
    Given the browser is opened
    When the user visits the customer login page
    Then the customer login page should load successfully
