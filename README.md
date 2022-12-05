# customer-statement-processor

This service is responsible for processing customer statements received from an external party. This tool Will be run to validate the customer statements and store them in case no validation errors occur. Currently two validations are done. Which are the following: 

- validate if the balance and the mutation are aligned
- validate if the reference number of the customer statement doesn't already exist

The application takes three command line arguments <inputfile> <outputdirectory> <filetype>.

In case validations fail a report entry for that statement is created. The reports are generated in the  <outputdirectory> folder. 
## build the app
run the gradle command `./gradlew shadowJar` to build the fat jar. Then run `java -jar customer-statement-processor-1.0-all.jar <inputfile> <outputdirectory> <filetype>`
## How to run the app


Run the gradle command `./gradlew run --args='<inputpath> <outputdirectory> <type>' `. Current supported filetypes are XML and CSV. 

### OWASP

- Run the gradle command `./gradlew dependencyCheckAnalyze` to verify if there are any vulnerabilities in the dependencies. 
- You can review the dependency report in build/reports locally and under the following link in circleCI: https://app.circleci.com/pipelines/github/daniel-jacob-test-organization/customer-statement-processor/1/workflows/f1644ded-738b-4eb5-867a-3e9e28340af0/jobs/1/artifacts


### Technical choices


- The choice was made for a console application. The reason for this is because the customer statements exist an a path somewhere and this tool will be run to trigger validations of those records. Therefore a command line application seemed the most logical choice.
- the choice for Kotlin instead of Java was made due to the conciseness, maintainability and readability of the Kotlin language. It is less verbose than java and you have more convenience methods to program in a functional way next to object oriented programming. Furthermore, it is just a light library on top of the JDK. 

### Assumptions

- The scope of the application was to build an application that processes customer statements. These statements are put on a path on a server. The party that uses the software would start this tool to process the statements. It seems therefore most logical to build a command line application that takes a path and a filetype and processes the file for that type. 
- The delivered file is syntactically correct
- CI pipeline can be found on https://app.circleci.com/pipelines/github/daniel-jacob-test-organization/customer-statement-processor
- Currently only XML and CSV are supported but in the future other formats might be added. I have used an interface here to ensure only a new implementation needs to be added. The same goes for the validations. 
- The transaction reference is the only thing that needs to be unique and no plans in the future to change that. Therefore, we only store the reference to verify if the reference is duplicate saving overhead in memory for the current implementation which stores the references in memory. 

### Improvements
- As it currently stands there is a code coverage of around 95%. In order to see that we would need to go into IntelliJ idea. It would be nice to host sonarqube on a server and integrate our application so we could see the report in the browser.
- We currently have owasp plugin in the pipeline to check for dependency vulnerabilities.
- As part of the CI/CD pipeline setup infrastructure as code to deploy the application. 
- Create a performance test to verify that large amounts of data can be processed by the application
- Add an architecture diagram for the application





