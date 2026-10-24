# EventHub — Smart Event & Conference Management Platform

EventHub is a full-stack event planning and management platform that enables customers to submit event requests and helps managers coordinate bookings, appointments, quotations, office-based payments, and billing through a centralized system.

## Features

- **Customer Authentication:** Customer registration and login.
- **Event Booking:** Submit event requests and select venues based on city and event requirements.
- **Guest Capacity Validation:** Validate guest capacity against event and venue requirements.
- **Manager Dashboard:** Accept, reject, and manage customer booking requests.
- **Appointment Management:** Schedule appointments and maintain meeting notes.
- **Quotation Management:** Generate and manage quotations for event bookings.
- **Office-Based Payment Tracking:** Record payments collected at the EventHub office.
- **Automatic Bill Generation:** Generate bills after payment confirmation and allow customers to view their bills.
- **Previous Events Gallery:** Showcase previously organized events.

## Technology Stack

- **Frontend:** React.js, JavaScript, HTML5, CSS3, Bootstrap
- **Backend:** Java 21, Spring Boot, REST APIs, Spring Data JPA, Hibernate
- **Database:** MySQL
- **Testing:** Manual Testing, Selenium WebDriver, TestNG, Maven
- **Tools:** Git, GitHub, Postman, Eclipse, VS Code

## Project Structure

```text
EventHub/
├── eventhub-frontend/     # React frontend application
├── eventhub-backend/      # Spring Boot REST API
└── EventHub-Automation/   # Selenium automation test suite
```

_Note: Adjust the folder names above if your actual repository structure differs._

## Live Demo

- **Frontend:** https://event-hub-eta-six.vercel.app/
- **Backend API:** https://eventhub-production-4dcd.up.railway.app

## Getting Started

### Prerequisites

Install the following before running the project locally:

- Node.js and npm
- Java Development Kit (JDK) 21
- Apache Maven
- MySQL Server

### 1. Clone the Repository

```bash
git clone https://github.com/Faisal30212/EventHub.git
cd EventHub
```

### 2. Configure the Database

Create a MySQL database named `eventhub`.

Configure the database connection using these environment variables:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

Use your own local database credentials and ensure they match the application's configuration.

### 3. Run the Backend

Open a terminal in the backend directory:

```bash
cd eventhub-backend
mvn spring-boot:run
```

The backend uses port `8080` by default, unless configured otherwise.

### 4. Run the Frontend

Open another terminal in the frontend directory:

```bash
cd eventhub-frontend
npm install
npm run dev
```

Open the local URL displayed in the terminal to access the application.

## Testing

EventHub includes manual testing and Selenium automation testing to verify important application workflows.

The Selenium test suite uses:

- **Selenium WebDriver** for browser automation
- **TestNG** for test execution and assertions
- **Maven** for dependency management and test execution

The automated tests cover customer authentication, customer event views, manager authentication, dashboard access, booking management, appointment and quotation workflows, feedback management, payment processing, and bill viewing.

## Future Enhancements

- **Expand City Coverage:** Add more cities to give customers a wider choice of event destinations.
- **Add More Venues:** Expand the venue database with additional venues across supported cities.
- **Venue Search:** Allow customers to search for venues by name and city.
- **Advanced Venue Filters:** Add filters for event type, guest capacity, and budget.
- **User Interface Improvements:** Continue improving responsive design, usability, and navigation.
- **Additional Validation:** Strengthen input validation and error handling across the application.
- **Performance Improvements:** Improve API efficiency and overall application reliability.

## Project Status

EventHub is a full-stack portfolio project built to demonstrate frontend development, backend API development, database integration, booking management, and software testing skills. The application is deployed online, with further enhancements planned for future iterations.

## Author

**Faisal Khan**

- GitHub: https://github.com/Faisal30212
