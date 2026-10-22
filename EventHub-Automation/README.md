# EventHub – Selenium & Cucumber Automation

An automated testing project for the EventHub Smart Event & Conference Management Platform, built using Selenium WebDriver, Java, TestNG, Maven, and Cucumber BDD.

## Technologies Used

- Java 21
- Selenium WebDriver 4.38.0
- TestNG 7.11.0
- Cucumber 7
- Maven
- Chrome WebDriver
- Git and GitHub

## Application Under Test

**EventHub:** https://event-hub-eta-six.vercel.app/

## Project Structure

```text
EventHub-Automation/
├── src/test/java/com/eventhub/
│   ├── cucumber/
│   ├── pages/
│   └── tests/
│       ├── booking/
│       ├── events/
│       ├── feedback/
│       ├── login/
│       ├── manager/
│       ├── registration/
│       └── venues/
├── src/test/resources/
│   └── features/
│       ├── booking.feature
│       ├── event-browsing.feature
│       ├── feedback.feature
│       ├── homepage.feature
│       ├── login.feature
│       ├── my-events.feature
│       ├── registration.feature
│       └── venues.feature
└── pom.xml
```

## Test Coverage

### Selenium WebDriver

- Homepage navigation
- Customer registration and login
- Event browsing and categories
- Booking forms and booking management
- Venue browsing and details
- Customer feedback and suggestions
- Manager dashboard and login

### Cucumber BDD

- Homepage navigation
- Customer login page navigation
- Booking entry-page navigation
- Venue entry-page navigation
- Registration page navigation
- Event browsing entry-page navigation
- My Events entry-page navigation
- Homepage navigation for feedback

## How to Run

### Prerequisites

- Java JDK 21
- Maven installed and configured in PATH
- Google Chrome installed

### Run Cucumber Tests

```bash
mvn -Dtest=CucumberTestRunner test
```

### Run a Specific Selenium Test

```bash
mvn -Dtest=CategoryTest test
```

### Run the Selenium Test Suite

```bash
mvn clean test
```

## Notes

- The application is hosted on Vercel.
- Cucumber scenarios currently focus primarily on navigation checks.
- Some application routes and feature interactions may require further verification.
- Test results depend on application availability and browser compatibility.

## Author

**Faisal Khan**

GitHub: https://github.com/Faisal30212
