# Title of the project #
Reservation Service

# Description of the project
Reservation service is the microservice that manages the booking of a bed in hospital.

Note 1 : the settings for hopital-service are centralized in the configuration microservice. therefore, the config-service must be fully operational in order to serve configuration parameters every other microservices.

Note 2 : The microservice "reservation-service" needs to register with the eureka registration service (registry-service). Therefore, the microservice "registre-service" must be fully operational in order for other microservices to be registrered.

Note 3 : All access to the microservice "reservation-service" must be done via the API gateway (Gateway-service). Therefore, the gateway-service must be fully operational.

#Fonctionalities
1. Reserver un lit dans un hopital

#Endpoints
1. POST:/reservation: allows book a bed in a hospital
5. PUT:/reservation: used to end the booking

#Requirements
1. Java 17
2. Maven 3.8.6
3. 

#Data Storage
1. booking data are stored in a mysql database (just ont table). See file: resources/reservation_bdd.sql
2. Note : To update database configuration, got to the repo cloudConfig (https://github.com/SamanoCastre/cloudConfig)

#Database creating
1. Create a database with the name "reservation_bdd"
2. import the file reservation_bdd.sql located under the subfolder /resource

#CI-CD Pipelines
1. See the project's configuration Jenkinsfile located in the classpath of the project

#Tests
1. Unit Tests
2. Integration Tests
4. Acceptance Tests
3. Command line : mvn test (see the project's jenkinsfile)

#Automated tests 
1. using the jenkins tool (see the project's jenkinsfile)
2. use of JMeter (see TestPlan.jmx in the classpath of the project)

#Tests Report
1. Use Jacoco
2. Command line : mvn jacoco:report (see project's jenkinsfile)

#Build
command line : mvn clean install (see jenkinsfile)

#Packaging the application
command line : mvn clean package -DskipTests (see the project's jenkins file)

#Deploy
command line : mvn spring-boot:run

#Full system deployment order
1. Deploy the MS config-service as indicated in the readme.md located the classpath of the config-service project.
2. Deploy the MS registre-service as indicated in the readme.md located the classpath of the registre-service project.
3. Deploy the MS gateway-service as indicated in the readme.md located the classpath of the gateway-service project.
4. Deploy the MS hopital-service as indicated in the readme.md located the classpath of the hopital-service project.
5. Deploy the MS reservation-service as indicated in the readme.md located the classpath of the reservation-service project.
