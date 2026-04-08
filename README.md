AutomationCICD – End-to-End Test Automation Framework :

An end-to-end test automation framework built using Selenium, Java, and TestNG, integrated with CI/CD pipelines (Jenkins & GitHub Actions) to enable continuous testing and faster feedback.
This project demonstrates how modern QA engineers design scalable, maintainable automation frameworks and integrate them into real-world DevOps workflows.

Tech Stack :
- Java
- Selenium WebDriver
- TestNG
- Maven
- Jenkins
- GitHub Actions
- Extent Reports
- ThreadLocal WebDriver (Parallel Execution)

Framework Design :

This framework follows best practices used in real-world automation projects:

- Page Object Model (POM) for maintainability
- TestNG for test execution and configuration
- ThreadLocal WebDriver for parallel test execution
- Retry Analyzer for handling flaky tests
- Listeners for reporting and logging
- Clean separation of concerns (Pages, Tests, Utilities)

CI/CD Integration :

The framework is fully integrated with CI/CD pipelines:

- GitHub Actions
   - Automatically triggers test execution on every push
   - Provides quick feedback on build status
- Jenkins
   - Supports scheduled and manual test runs
   - Enables continuous testing in a real CI environment
- Automated Reporting
   - Test reports generated after each execution
   - Easy debugging with logs and screenshots
 
Reporting :
- Extent Reports integration
- Screenshots captured on test failure
- Detailed logs for debugging and analysis

Project Structure :
```
AutomationCICD
│
├── src
│   ├── test
│   │   ├── java
│   │   │   ├── tests         #Test Classes
│   │   │   ├── pages         #Page Object Classes
│   │   │   ├── utilities     #Utility/Helper Classes
│   │   │   └── listeners     #TestNG Listeners (logging, reports)
│
├── testng.xml                #TestNG suite file
├── pom.xml                   #Maven Dependencies
```

How to Run Locally :

1. Clone the Repository
git clone https://github.com/Kibz6/AutomationCICD.git

2. Install prerequisites
 - Java 17+
 - Maven 3.6+
 - Browser drivers (ChromeDriver, GeckoDriver, etc.)

3. Start Selenium Grid
Download Selenium Server Standalone / Grid
   - Go to https://www.selenium.dev/documentation/grid/getting_started/ and follow the instructions on how to setup Selenium Grid
   - Open a terminal and run the Hub:
      
      java -jar selenium-server-<version>.jar hub

   - Start one or more nodes and register them to the Hub:

     java -jar selenium-server-<version>.jar standalone

Make sure Make sure the Hub is running on http://localhost:4444 before running the tests.

4. Run tests

   Once Selenium Grid is running open CommandPromt/Terminal and run:
   
   mvn clean test
   
Or run a specific TestNG suite:

  mvn test -DsuiteXmlFile=testng.xml


5. Notes:
   
 - Tests will connect to Selenium Grid and run on registered nodes.
 - Test reports are generated in AutomationCICD/reports/
 - No Jenkins or GitHub Actions is needed for local execution.

 Author:

Bojan Djeviki

GitHub: https://github.com/Kibz6
