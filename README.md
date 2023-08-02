
# Overview:

This selenium based test Automation framework is designed to automate tests of the given web page. It is basically implemented using the page factory design pattern and TestNG library for writing tests. Also we used basic features of extent reporting for test execution reports.


# Package and Components of Framework:

Package Name - webpage.testcases

     HomeTests - This class holds all the tests written for the web page. It has tests denoted by @Test annotations and accepts required test data using @Parameters annotations for each test respectively. Each test method makes use of the helper functions defined in HomePage class to construct the test automation flow.

Package Name - webpage.pageObjects

    HomePage - This class contains all the element locators and reusable helper methods of home screen. These helper methods are used in the test classes for building the automation flows.

Package Name - webpage.utils

    BaseUtil - This class contains the @Before Suite, @BeforeMethod, @AfterSuite and @AfterMethod testNG annotations for performing the basic setup like Report initialization, browser setup, adding test status report and closing webdriver sessions. Also, it includes a method to add screenshot to the report if a test is failed during execution.

#### Application web page:
The given web page 'QE-index.html' file  is located in the below path of the framework root directory to access and launch the page in browser.

    /src/test/resources/app

#### testNG xml file:
This file is the source of test execution that has test suites and test methods for running the tests against given web page. Also parameter values required for the test methods are passed from here. This file is located in below path.

    /src/test/resources

#### pom file:
All the required maven dependencies and plugins of the framework are defined here and path to the testNG xml file is passed to the sure-fire plugin. It is located under the root directory of the project.

# How to run tests:

#### From Eclipse:
1. Import the 'TestAutomationFramework'  maven project to eclipse
2. Locate and right click on the pom.xml file
3. Select option 'Run As' and then 'Maven Test'

#### From Command line:
1. Use below command to navigate to project root directory
> cd <absolute-path-of-project-root-directory>

2. run below maven command
> mvn clean test

### Test Automation Report:
After test execution is completed, test report is available in the below path of the project. 
> src/target/reports/sparkReport.html

#### Note:
1. A sample report is already available in the path
>"src/test/resources/sampleReport/sparkReport.html".
2. In case of any failures in the test execution, the captured screenshots are located in below path

> src/target/reports/screenshots