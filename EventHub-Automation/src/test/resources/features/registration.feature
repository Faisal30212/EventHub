
Feature: EventHub customer registration

  Scenario: Open the customer registration page
    Given the browser is opened
    When the user visits the customer registration page
    Then the customer registration page should load successfully
