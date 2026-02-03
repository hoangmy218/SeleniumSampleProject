# SeleniumSampleProject

##Test Execution
Run mvn clean install to remove old target directories and force a fresh build.

**> mvn clean install**

To run tests in multiple threads:

**> mvn clean install -Dthreads=2**

To run tests with specific browser (for firefox, use -Dbrowser=firefox), available driver setup: chrome (Default), firefox, edge, safari 

**> mvn clean verify -Dthreads=2 -Dbrowser=firefox**

To run tests in headless mode:

**> mvn clean install -Dthreads=2 -Dbrowser=chrome -Dheadless=yes**