# SILOGISTIK - Logistics Information System

![Dashboard](https://cdn.discordapp.com/attachments/1176441469725327430/1225475472029126686/Screenshot_2024-04-04_at_23.01.53.png?ex=662143f8&is=660ecef8&hm=f6753e0c4b5634a6836307ccd7a3908886e9933c1fcfd9c64c065a92ad475aba&)

## Introduction

SILOGISTIK is a Spring Boot application designed to streamline logistics operations for APAP Express (fictional). The application serves as a central platform to integrate shipment request information, manage inventory, and handle customer service inquiries efficiently. It's built with an emphasis on user experience and inter-departmental coordination to solve logistical challenges.

## Key Features

- **Dashboard:** A comprehensive view of logistics operations, including shipment statuses, inventory levels, and more.
- **Warehouse Management:** Facilities to list, detail, and manage warehouse inventories with real-time updates.
- **Goods Tracking:** Advanced searching and filtering options to track goods across various warehouses.
- **Restocking Functionality:** Easy-to-use interface for restocking goods with instant reflections in the inventory.
- **Shipping Request Handling:** Systematic approach to create, view, and manage shipping requests with the option to cancel within 24 hours.
- **Interactive User Interface:** Utilizing Bootstrap and Thymeleaf, the application provides an engaging and responsive user interface.
- **Data Management:** Implementation of DTOs to ensure data transfer objects are efficiently handled between the model and the view.

## Technologies Used

- **Spring Boot:** For the back-end application framework.
- **Thymeleaf:** As the template engine for rendering the front-end.
- **Bootstrap:** To design a responsive and mobile-friendly interface.
- **MVC Architecture:** Employed to separate the application into interconnected components, increasing maintainability.
- **DTOs (Data Transfer Objects):** For a clean separation of concerns and reduced network load.
- **Gradlew:** For dependency management and project build.

## Setup and Installation

1. Clone the repository
2. Navigate to the project directory: `cd SILOGISTIK`
3. Configure PostgreSQL database
4. Build the project: `./gradlew build`
5. Run the application: `./gradlew bootRun`
6. Access the application at `localhost:8080`

## Contributors

- **Fariz Eda** - Initial work, feature implementation, and documentation.

*This README is a brief overview of the SILOGISTIK project. For more details on each feature, please refer to the in-depth documentation included within the project.*
