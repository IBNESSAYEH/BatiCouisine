# BatiCouisine

**BatiCouisine** is a Java-based application designed for managing kitchen renovation projects. The project uses a multi-layered architecture following the principles of MVC (Model-View-Controller).

## Project Architecture

The project is organized into the following packages under `src/com/BatiCouisine/`:

src/ 
└── com/ 
    └── BatiCouisine/ 
        ├── controller/ 
        ├── entities/  
        ├── repository/ 
        ├── service/
        ├── util/ 
        └── Main.java 

markdown
Copy code

## Features

- **Kitchen Renovation Management**: Create, update, and track renovation projects.
- **Layered Architecture**: Follows a structured architecture separating concerns for cleaner code and better maintenance.
- **Database Integration**: Uses PostgreSQL for data storage and retrieval.
- **User Interaction**: Allows interaction with different services through a console-based interface.

## Prerequisites

- **Java SE**: Ensure Java is installed and configured.
- **PostgreSQL**: The project relies on PostgreSQL for database management.
- **Gradle**: Build tool for compiling and running the project.
- **Docker**: Optional, for containerized setup of the database.

## How to Run the Project

1. **Clone the Repository:**
    ```bash
    git clone https://github.com/IBNESSAYEH/BatiCouisine.git
    ```

2. **Navigate to the Project Directory:**
    ```bash
    cd BatiCouisine
    ```

3. **Build the Project with Gradle:**
    ```bash
    gradle build
    ```

4. **Run the Application:**
    ```bash
    java -cp build/classes/java/main com.BatiCouisine.Main
    ```

## License

This project is licensed under the MIT License.
