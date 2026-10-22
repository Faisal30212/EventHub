
Feature: Customer feedback

  Scenario: Open the EventHub homepage for feedback
    Given the browser is opened
    When the user visits the EventHub homepage
    Then the homepage should load successfully
