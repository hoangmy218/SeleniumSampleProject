# SeleniumSampleProject

##Test Execution

To run tests in multiple threads:

**> mvn clean install -Dthreads=2**

To run tests with specific browser (for firefox, use -Dbrowser=firefox), available driver setup: chrome (Default), firefox, edge, safari 

**> mvn clean install -Dthreads=2 -Dbrowser=firefox**

To run tests in headless mode:

**> mvn clean install -Dthreads=2 -Dbrowser=chrome -Dheadless=yes**