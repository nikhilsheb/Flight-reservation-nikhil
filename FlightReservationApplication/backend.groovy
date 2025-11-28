pipeline{
    agent any 
    stages{
        stage('Pull'){
            steps{
                git branch: 'main', url: 'https://github.com/nikhilsheb/Flight-reservation-nikhil.git'
            }
        }
        // stage('Build'){
        //     steps{
                
        //     }
        // }
        // stage('QA-Test'){
        //     steps{
                       
        //     }
        // }
        // stage('Docker-build'){
        //     steps{
                
        //     }
        // }
       
    }
}
