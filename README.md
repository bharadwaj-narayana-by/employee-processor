To run the application,

1) Download https://github.com/bharadwaj-narayana/employee-processor/blob/master/EMPLOYEE-PROCESSOR-0.0.1-SNAPSHOT.jar
2) Download https://github.com/bharadwaj-narayana/employee-processor/blob/master/src/main/resources/application.yml
3) Setup kafka and create below topics
    a) employee-feed
    b) DLQ
    c) employee-valid
 5) Run with following command java -jar EMPLOYEE-PROCESSOR-0.0.1-SNAPSHOT.jar --spring.config.location=application.yml
 
 
 Application reads data from topic 'employee-feed' and validates. If message is valid same gets pushed to topic 'employee-valid' else it will get pushed to topic 'DLQ'
 
 
 Sample data
 {"employeeName":"Bharadwaj","employeeId":"0123456789","address":"299, Shrinidi","org":"Blue Yonder","role":"Consultant"}
