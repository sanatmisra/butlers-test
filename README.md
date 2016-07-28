# butlers-test
selenium web tests for butlers.com

# This repository contains Selenium Web tests for butlers.com website

Pre-requisites:
- Java 8
- Maven
- Firefox


# To run the tests, do the following:
- clone repository
- run command: mvn clean install
- once above steps are completed successfully, tests could be run
- open project in IDE, right click on LoginTest and select 'Run LoginTest'

# To generate reports, This is what I do:
                     
   - Run tests and generate .xml reports:
        _mvn test_
                     
   - Convert .xml reports into .html report, but without the CSS or images:
        _mvn surefire-report:report-only_
                     
   - Put the CSS and images where they need to be without the rest of the time-consuming stuff:
        _mvn site -DgenerateReports=false_
 
   - Go to DIRECTORY 'target/site/surefire-report.html' for the report.
