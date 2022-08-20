# Title of the project #
Reservation Service

# Description of the project
Reservation service is the microservice that manages the booking of a bed in hospital.

Note 1 : the settings for this microservice are centralized in the configuration microservice. therefore, the latter must be fully operational in order to serve it its configuration parameters.

Note 2 : This microservice needs to register with the eureka registration service (registry-service). Therefore, the latter must be fully operational in order for it to register with it.

Note 3 : All access to this microservice must be done via the API gateway (Gateway-service). Therefore, the latter must be fully operational.

#Fonctionalities
1. Reserver un lit dans un hopital

#Endpoints
1. POST:/reservation: allows book a bed in a hospital
5. PUT:/reservation: used to end the booking

#Requirements
1. Java 17
2. Maven 3.8.6
3. 


#Dependencies
1. spring-boot-starter-data-jpa
2. spring-boot-starter-log4j2
3. spring-cloud-starter-netflix-eureka-client
4. spring-boot-starter-test
5. spring-cloud-starter-openfeign

#Data Storage
1. booking data are stored in a mysql database (just ont table). See file: resources/reservation_bdd.sql
2. Note : To update database configuration, got to the repo cloudConfig (https://github.com/SamanoCastre/cloudConfig)

#Tests
1. Unit Tests
2. Integration Tests
4. Acceptance Tests
3. Command line : mvn test (see the project's jenkinsfile)

##Automated tests 
1. using the jenkins tool (see the project's jenkinsfile)

##Tests Report
1. Use Jacoco
2. Command line : mvn jacoco:report (see project's jenkinsfile)

#Build
command line : mvn clean install (see jenkinsfile)

#Packaging the application
command line : mvn clean package -DskipTests (see the project's jenkins file)

#Deploy
command line : mvn spring-boot:run
