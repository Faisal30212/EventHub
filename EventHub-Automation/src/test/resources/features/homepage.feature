
Feature: EventHub homepage

  Scenario: Open the EventHub homepage
    Given the browser is opened
    When the user visits the EventHub homepage
    Then the homepage should load successfully
