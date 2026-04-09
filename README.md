**AutomationCICD – End-to-End Test Automation Framework** :

An end-to-end test automation framework built using Selenium, Java, and TestNG, integrated with CI/CD pipelines (Jenkins & GitHub Actions) to enable continuous testing and faster feedback.
This project demonstrates how modern QA engineers design scalable, maintainable automation frameworks and integrate them into real-world DevOps workflows.

**Tech Stack** :
- Java
- Selenium WebDriver
- TestNG
- Maven
- Jenkins
- GitHub Actions
- Extent Reports
- ThreadLocal WebDriver (Parallel Execution)

**Framework Design** :

This framework follows best practices used in real-world automation projects:

- Page Object Model (POM) for maintainability
- TestNG for test execution and configuration
- ThreadLocal WebDriver for parallel test execution
- Retry Analyzer for handling flaky tests
- Listeners for reporting and logging
- Clean separation of concerns (Pages, Tests, Utilities)

**CI/CD Integration** :

GitHub → Jenkins → Maven Build → Test Execution → Reports

Flow:
1. Code is pushed to GitHub
2. Jenkins detects changes (via webhook)
3. Jenkins triggers a build
4. Maven runs the test suite
5. Test reports are generated
 
**Reporting** :
- Extent Reports integration
- Screenshots captured on test failure
- Detailed logs for debugging and analysis

**Project Structure** :
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

**Quick Start (Selenium Grid – Required)**

This framework is designed to run on Selenium Grid for scalable and parallel execution.

1. Start Selenium Grid (Standalone Mode)
   
       java -jar selenium-server-<version>.jar standalone

   This starts a local Grid (Hub + Node) at:

       http://localhost:4444

2. Run Tests
   
       mvn clean test

Tests will execute on the Selenium Grid node.

**CI/CD Setup (Advanced)**

This project supports running tests through Jenkins using a public URL.

Steps:
1. Setup Jenkins locally (http://localhost:8080)
2. Connect GitHub repository using a Personal Access Token

3. Expose Jenkins using ngrok:

       ngrok http 8080

4. Configure GitHub webhook → Jenkins

This enables automated test execution triggered from GitHub.

**GitHub Actions (Optional)**

This project can also run using GitHub Actions:

- Trigger on push
- Execute Maven tests
- Generate reports
  
 Author:

Bojan Djeviki

GitHub: https://github.com/Kibz6
