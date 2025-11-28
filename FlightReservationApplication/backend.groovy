pipeline{
    agent any 
    stages{
        stage('Pull'){
            steps{
                git branch: 'main', url: 'https://github.com/nikhilsheb/Flight-reservation-nikhil.git'
            }
        }
        stage('Build'){
            steps{
                sh '''
                    cd FlightReservationApplication
                    mvn clean package
                ''' 
            }
        }
        stage('QA-Test'){
            steps{
                withSonarQubeEnv(installationName: 'sonar', credentialsId: 'sonar-token') {
                    sh '''
                        cd FlightReservationApplication
                        mvn sonar:sonar  -Dsonar.projectKey=flight-reservation
                    '''
                }   
            }
        }

       stage('Docker-build'){
            steps{
                sh '''
                    cd FlightReservationApplication
                    docker build -t nikhilsheb/Flight-reservation-nikhil:latest .
                    docker push nikhilsheb/Flight-reservation-nikhil:latest
                    docker rmi nikhilsheb/Flight-reservation-nikhil:latest
                '''
            }
        }
        stage('Deploy'){
            steps{
                sh '''
                    cd FlightReservationApplication
                    kubectl apply -f k8s/
                '''
            }
        }
       
    }
}
