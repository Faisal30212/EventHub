# EventHub — Smart Event & Conference Management Platform

EventHub is a full-stack event planning and management platform that helps customers submit event requests and enables managers to coordinate bookings, appointments, quotations, payments, and billing.

## Features

- Customer registration and login
- Event booking and venue selection by city
- Guest-capacity validation
- Manager dashboard for accepting or rejecting requests
- Appointment scheduling and meeting notes
- Quotations and office-based payment tracking
- Automatic bill generation and customer bill viewing
- Previous-events gallery

## Technology Stack

**Frontend:** React.js, JavaScript, HTML, CSS, Bootstrap  
**Backend:** Java, Spring Boot, REST APIs, Spring Data JPA, Hibernate  
**Database:** MySQL  
**Tools:** Maven, Git, GitHub, Postman

## Project Structure

- `eventhub-frontend/` — React application
- `eventhub-backend/` — Spring Boot REST API

## Getting Started

### Prerequisites

- Node.js and npm
- Java 21
- Maven
- MySQL

### Frontend

Open a terminal in `eventhub-frontend` and run:

```bash
npm install
npm run dev
```

### Backend

Configure your MySQL connection using the environment variables `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`.

Open a terminal in `eventhub-backend` and run:

```bash
mvn spring-boot:run
```

The backend uses port `8080` by default.

## Database Configuration

Create a MySQL database named `eventhub`. Configure the database URL, username, and password through environment variables before running the backend.

## Project Status

The project is being developed as a full-stack portfolio project. Manual and Selenium testing documentation will be added as testing progresses.
