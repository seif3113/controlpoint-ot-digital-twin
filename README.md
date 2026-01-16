# Asset vision

This is a full-stack application with a **Spring Boot backend** and an **Angular frontend**.

---

## Project Structure

```
my-project/
├── server/      ← Spring Boot (Maven)
├── web/     ← Angular
└── README.md
```

* `server/` contains the REST API and business logic.
* `web/` contains the Angular frontend application.

---

## Prerequisites

Before running the project, make sure you have installed:

* **Java 21** (for Spring Boot)
* **Maven 3.8+**
* **Node.js 18+** and **npm**
* Optional: **Angular CLI** for development (`npm install -g @angular/cli`)

---

## Running the Backend

1. Open a terminal and navigate to the backend folder:

```bash
cd server
```

2. Install dependencies and build:

```bash
mvn clean install
```

3. Run the Spring Boot application:

```bash
mvn spring-boot:run
```

* The backend runs by default on **[http://localhost:8080](http://localhost:8080)**

---

## Running the Frontend

1. Open another terminal and navigate to the frontend folder:

```bash
cd web
```

2. Install dependencies:

```bash
npm install
```

3. Start the Angular development server:

```bash
npm start
```

* The frontend runs by default on **[http://localhost:4200](http://localhost:4200)**

