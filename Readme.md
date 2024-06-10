# Cookie Analyzer Project

## Overview
The Cookie Analyzer project is a Java-based application designed to analyze cookie data stored in CSV log files.
It offers functionalities such as identifying the most active cookie of the day by reading cookie data,
analyzing it based on user-specified criteria, and outputting the analysis results to the console.

## Usage

### Prerequisite
* Java 17 or above [install Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

### Steps to run
To use the Cookie Analyzer project, follow these steps:

1. Clone [or] Download [here](https://github.com/anandha-krishnan-r/CookieAnalyzer/archive/refs/heads/main.zip) the project repository to your local machine.
    
    ```bash
    git clone https://github.com/anandha-krishnan-r/CookieAnalyzer
    ```
2.  Navigate to the project directory `cd CookieAnalyzer` and make the Gradle wrapper executable.
   
    ```bash
    chmod +x gradlew
    ```
3. Execute the application with appropriate command-line arguments.  

   ##### On Unix-like(Mac-os) systems:
   ```bash
    ./gradlew run --args="-f data/cookie-log.csv -d 2018-12-09 -p MOST_ACTIVE_COOKIE"
    ```
   ##### On windows systems:
   ```bash
   gradlew.bat run --args="-f data/cookie-log.csv -d 2018-12-09 -p MOST_ACTIVE_COOKIE"
   ```
   -f : path of the cookie data log file
   <br>
   -d : date to analyze the cookie data
   <br>
   -p: process to perform on the data, eg : MOST_ACTIVE_COOKIE
### Contact
For any queries please contact me through
Email : anandhakrishnan.r@outlook.com 


