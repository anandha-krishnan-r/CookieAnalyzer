# Cookie Analyzer Project

## Overview
The Cookie Analyzer project is a Java-based application designed to analyze cookie data stored in CSV log files.
It provides functionalities to read cookie data from files, analyze the data based on user-specified criteria,
and write the analysis results to various output destinations.

## Usage

### Prerequisite
* Java 17 or above [install Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* Gradle 8.7 or above [Recommended] [install Gradle](https://docs.gradle.org/current/userguide/installation.html)

### Steps to run
To use the Cookie Analyzer project, follow these steps:

1. Clone the Repository: Clone [or] download the project repository to your local machine.
    
    ```bash
    git clone https://github.com/anandha-krishnan-r/CookieAnalyzer
    ```
2. Build the Project: Navigate to the project directory and build the project using Gradle

    ```bash
    ./gradlew assemble
    ```
3. Run the Application: Execute the application with appropriate command-line arguments.  
    
   ```bash
   ./gradlew run --args="-f data/cookie-log.csv -d 2018-12-09 -pt MOST_ACTIVE_COOKIE"
    ```

### Contact
For any queries please send a mail to
Email : anandhakrishnan.r@outlook.com 


