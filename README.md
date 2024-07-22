# Magento Softwaretestingboard Automation Framework

## Overview
This project is an automated testing framework for the Magento Softwaretestingboard e-commerce platform. It utilizes Selenium WebDriver, TestNG, and follows the Page Object Model (POM) design pattern to create robust, maintainable, and scalable automated tests.

## Features
- End-to-end user journey testing
- User registration and login functionality
- Product selection and attribute testing
- Shopping cart and checkout process validation
- Modular and reusable page objects
- Data-driven testing using TestNG data providers
- Configurable test execution with properties file
- Logging using Log4j2
- Allure reporting integration


## Configuration
- Update `src/main/resources/config.properties` to set browser type, application URL, and timeouts.
- Modify `src/main/resources/log4j2.xml` to adjust logging settings if needed.

## Running Tests
To run all tests in the project, use the following command:
mvn clean test

To run a specific test class:
mvn test -Dtest=TestClassName

If you want to ensure a clean build before running tests, you can use:
mvn clean test

## Reporting
After test execution, Allure reports can be generated using:
mvn allure:report

The report will be generated in `target/site/allure-maven-plugin/index.html`

