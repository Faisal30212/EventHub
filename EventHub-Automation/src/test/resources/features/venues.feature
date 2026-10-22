
Feature: EventHub venue browsing

  Scenario: Open the venues entry page
    Given the browser is opened
    When the user visits the venues page
    Then the venues page should load successfully
