pipeline{
    agent any
    triggers{
		pollSCM('* * * * *')
	}
    stages{
        stage("Compile the source code")	{
            steps	{
	 			bat "mvn compile"
            }
        }
        stage("Test the source code")	{
            steps	{
           		bat "mvn test"
            }
        }
         stage("Code coverage")	{
            steps	{
	            bat "mvn jacoco:report"
	            publishHTML	(target:	[
					reportDir:	'target/site/jacoco',
					reportFiles:	'index.html',
					reportName:	"CodeCoverageReport"
				])
	            bat "mvn clean verify"
            }
        }
		stage("Package the application")	{
            steps	{
            	bat "mvn clean package -DskipTests"
            }
        }
	
		stage("Deploy to the staging")	{
		    steps	{
			    bat "mvn spring-boot:run"
			}
		}
    }
}
