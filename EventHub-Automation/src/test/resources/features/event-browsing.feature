
Feature: Event browsing

  Scenario: Open the events page
    Given the browser is opened
    When the user visits the events page
    Then the events page should load successfully
