# EventHub Automation Framework

Automated UI testing for the **EventHub Smart Event & Conference Management Platform**, a web application for browsing events, exploring venues, registering users, and managing event-related workflows.

**Live Application:** https://event-hub-eta-six.vercel.app/  
**GitHub:** https://github.com/Faisal30212/EventHub

## Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Cucumber (BDD)
- Maven
- Google Chrome
- Git and GitHub

## Framework Features

- Browser-based UI automation using Selenium WebDriver
- TestNG-based test execution and assertions
- Data-driven tests using multiple input combinations
- BDD scenarios written in Gherkin with Cucumber
- Organized test classes by application module
- Maven-based dependency management and test execution
- HTML report generation for Cucumber scenarios

## Project Structure

```text
EventHub-Automation/
├── pom.xml
├── README.md
├── src/
│   └── test/
│       ├── java/
│       │   └── com/eventhub/
│       │       ├── base/
│       │       ├── cucumber/
│       │       ├── pages/
│       │       └── tests/
│       │           ├── booking/
│       │           ├── datadriven/
│       │           ├── events/
│       │           ├── feedback/
│       │           ├── login/
│       │           ├── manager/
│       │           ├── registration/
│       │           └── venues/
│       └── resources/
│           └── features/
│               ├── homepage.feature
│               ├── login.feature
│               ├── booking.feature
│               ├── venues.feature
│               ├── registration.feature
│               ├── event-browsing.feature
│               ├── my-events.feature
│               └── feedback.feature
└── target/
    └── cucumber-report.html
```

_The `target/` directory contains generated build and test output. It is created by Maven and may not be committed to Git._

## Prerequisites

Install and configure:

- JDK compatible with the project configuration
- Apache Maven
- Google Chrome
- Git (optional, for repository operations)

Verify Java and Maven:

```bash
java -version
mvn -version
```

## Running Tests

Run the complete Maven test suite:

```bash
mvn clean test
```

Run the Cucumber test runner:

```bash
mvn -Dtest=CucumberTestRunner test
```

Run the data-driven test classes:

```bash
mvn -Dtest=DataDriven*Test test
```

Run a specific Selenium test class:

```bash
mvn -Dtest=CategoryTest test
```

Run the registration validation test:

```bash
mvn -Dtest=DataDrivenRegistrationValidationTest test
```

Run these commands from the `EventHub-Automation` directory.

## Test Reports

After a Maven test run, inspect:

- `target/surefire-reports/` — TestNG/Maven test result reports
- `target/cucumber-report.html` — HTML report configured for Cucumber execution

The Cucumber report is generated when the Cucumber runner executes. Open it in a browser to review the scenarios and their results.

## Current Test Coverage

The framework contains Selenium/TestNG tests, data-driven tests, and Cucumber feature scenarios organized around EventHub modules, including:

- Homepage and navigation
- Login and registration
- Event browsing and categories
- Venue browsing and details
- Booking-related workflows
- My Events
- Feedback and suggestions
- Manager-related pages

**Coverage note:** Some Cucumber scenarios and data-driven cases currently perform page-load or navigation checks rather than verifying the complete business workflow. Their presence does not imply that every underlying feature is fully validated. Further interaction-level assertions and end-to-end checks can extend coverage.

## Troubleshooting

- **`ERR_INTERNET_DISCONNECTED`:** Check your internet connection and confirm the live application opens in Chrome before rerunning the affected test.
- **Chrome DevTools Protocol (CDP) warnings:** These may appear when the installed Chrome version does not have a matching Selenium CDP implementation. If tests still pass, the warnings alone do not indicate a failed test.
- **Test failures:** Review the individual files in `target/surefire-reports/` to find the failing test and its exception.

## Author

**Faisal Khan**

GitHub: https://github.com/Faisal30212
