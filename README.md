# SeleniumSampleProject

##Test Execution
Run mvn clean verify to remove old target directories and force a fresh build.

**> mvn clean verify**

To run tests in multiple threads and generate allure-results folder in target/:

**> mvn clean verify -Dthreads=2**

To run tests with specific browser (for firefox, use -Dbrowser=firefox), available driver setup: chrome (Default), firefox, edge, safari 

**> mvn clean verify -Dthreads=2 -Dbrowser=firefox**

To run tests in headless mode:

**> mvn clean verify -Dthreads=2 -Dbrowser=chrome -Dheadless=yes**

To run test suites:

**> mvn -f ./testng-parameters-allure.xml clean verify**

##Report

Static generation - Processes test result and saves an HTML report into ./target/allure-report directory with configurations in allure3.js - Allure Report 3

**> allure generate target/allure-results --config allure3.js --output target/allure-report --clean**

Open HTML Report

**> allure open target/allure-report**



Local preview - Creates HTML Report and sets up a local server then displays the report in your browser.

**> allure serve target/allure-results --config allure3.js**

