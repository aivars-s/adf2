pipeline {
    agent any

    parameters {
        string(name: 'SonarUrl', defaultValue: 'http://sonar:9000', description: 'Sonarqube URL')
    }

    stages {
        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Run tests') {
            steps {
                sh 'mvn verify'
            }
        }
        stage('Run Sonarqube') {
            steps {
                sh "mvn sonar:sonar -Dsonar.host.url=${params.SonarUrl}"
            }
        }
    }
}