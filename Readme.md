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

1. Clone the project repository [or] Download [link](https://github.com/anandha-krishnan-r/CookieAnalyzer/archive/refs/heads/main.zip)  to your local machine.
    
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

 ### Behind the scenes
 At a high level, the execution workflow is well astracted for extensibility [ApplicationExecutor](src/main/java/com/cookieanalyzer/execution/ApplicationExecutor.java), which consists of the following steps:
  <br>
   1. Parse raw user request into a structured format using [CLIInputParser](src/main/java/com/cookieanalyzer/execution/inputparser/CLIInputParser.java)
  <br><br>
   2. Fetch cookie data from data source, such as a CSV file, via [CsvLogFileFetcher](src/main/java/com/cookieanalyzer/execution/datafetcher/CsvLogFileFetcher.java)
  <br><br>
   3. Analyze the cookie data to produce the user-requested result using [ActiveCookieAnalyzer](src/main/java/com/cookieanalyzer/execution/processor/ActiveCookieAnalyzer.java)
  <br><br>
   4. Output the result to the specified output stream, such as the console, with [CLIResultWriter](src/main/java/com/cookieanalyzer/execution/resultwriter/CLIResultWriter.java)
  
### Contact
For any queries please contact me through
Email : anandhakrishnan.r@outlook.com 


