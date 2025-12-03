# OrangeHRM Automation Framework

# Introduction

This repository contains a robust Test Automation Framework for the OrangeHRM Demo Application. It is built using Selenium WebDriver with Java and follows the Page Object Model (POM) design pattern to ensure maintainability and scalability.

The framework is designed to handle dynamic web elements, loading spinners, and complex workflows like Candidate Recruitment and Performance Reviews.

# Key Features

Hybrid Framework: Combines POM, Data-Driven Testing, and Modular design.

Robust Synchronization: Implemented waitForSpinner() logic to handle OrangeHRM's asynchronous loading, eliminating TimeoutException and flaky tests.

Dynamic Data Generation: Automatically generates unique Employee IDs and Usernames using timestamps to prevent "Duplicate Record" errors during regression.

Advanced Reporting: Integrated Allure Reports for detailed execution logs, steps, and failure analysis.

Cross-Browser Ready: Uses Selenium Manager for automatic driver management.

Lazy Initialization: optimized Page Object instantiation to ensure thread safety and driver availability.

# Tech Stack

Language: Java (JDK 21 Recommended)

Automation Tool: Selenium WebDriver 4.x

Test Runner: TestNG

Build Tool: Maven

Reporting: Allure Framework

IDE: IntelliJ IDEA

# Prerequisites

Java JDK 21 (Required for Allure Report compatibility).

Maven (Installed and configured in system path).

IntelliJ IDEA (Recommended).

Installation

Clone the repository:

git clone [https://github.com/mariam464/OrangeHRM-Automation.git](https://github.com/your-username/OrangeHRM-Automation.git)


Navigate to the project directory:

cd OrangeHRM-Automation


Install dependencies:

mvn clean install -DskipTests


How to Run Tests

Using Command Line (Maven)

To run the entire regression suite defined in testng.xml:

mvn clean test


Using IntelliJ IDEA

Right-click on testng.xml file.

Select Run '...testng.xml'.

Generating Reports (Allure)

After the test execution is complete, use the following command to generate and view the report in your browser:

mvn allure:serve


Note: If you encounter an "Unsupported class file major version" error, ensure you are running Maven with JDK 21 or 17, not JDK 24 (Early Access).

Scenarios Covered

Login: Valid, Invalid, and Empty credential validations.

PIM: Add Employee (with/without login details), Upload Photo.

Admin: User Management (Add/Search/Delete Users).

My Info:

Personal Details, Contact Details, Emergency Contacts.

Dependents, Immigration.

Qualifications (Work Exp, Edu, Skills, Languages, Licenses).

Memberships.

Recruitment: End-to-End Hiring Flow (Add -> Shortlist -> Interview -> Hire).

Performance: KPI & Tracker Management, Review Search.

Claim: Submit Claim requests.

