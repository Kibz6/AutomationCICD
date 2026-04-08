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
│   │   │   ├── tests
│   │   │   ├── pages
│   │   │   ├── utilities
│   │   │   └── listeners
│
├── testng.xml
├── pom.xml
└── README.md
```

How to Run the Project:

1. Clone the Repository
git clone https://github.com/Kibz6/AutomationCICD.git

2. Push Project to Your Own GitHub

    Create a new repository on your GitHub and push the project:

     - git remote set-url origin <your-repo-url>
     - git push -u origin main

3. Set Up Jenkins Locally

    - Install Jenkins and run it on:
      http://localhost:8080
    - Install required plugins:
       - Git Plugin
       - Maven Integration Plugin
       - Pipeline Plugin

4. Expose Jenkins Using Ngrok

    To allow external services (like GitHub and Selenium Grid) to access your Jenkins instance:

    Setup and run Ngrok with - ngrok http 8080

    This will generate a public URL like - https://xxxx.ngrok.io

    Use this URL in your pipeline/webhooks if needed.

5. Configure Jenkins Job
    Create a new Pipeline Job
    Connect it to your GitHub repository
    Configure:
      - GitHub webhook (optional for auto-trigger)
      - Branch: main

6. Selenium Grid Setup

     Ensure your Selenium Grid is running and accessible.
   
     Update your test configuration to point to the Grid URL:

    - http://<grid-url>:4444/wd/hub

    This allows tests to run remotely instead of locally.

7. Run the Tests

    You can now run tests in multiple ways:

    Locally:
   - mvn test
   
    Via Jenkins:
   - Trigger build manually or via GitHub webhook
  
     
9. View Test Reports

    After execution:

    - Open Extent Reports generated in the project
    - Review logs and screenshots for failed tests

    Notes:
   
 - Jenkins must be publicly accessible (via ngrok) for full CI/CD workflow
 - Selenium Grid must be running before triggering tests
 - Ensure correct configuration of WebDriver hub URL

 Author:

Bojan Djeviki

GitHub: https://github.com/Kibz6
