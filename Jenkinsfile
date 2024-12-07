pipeline {
    agent any
    tools {
        maven 'mvn-3.9.9' 
        jdk 'java11'
    }
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from repository...'
                checkout scm 
            }
        }
        stage('Download Dependencies') {
            steps {
                echo 'Downloading dependencies...'
                bat '''
                    mvn dependency:go-offline -Dmaven.repo.local=C:\\ProgramData\\Jenkins\\.m2\\repository
                '''
            }
        }
        stage('Unit Test and Code Coverage') {
            steps {
                echo 'Running Unit Tests...'
                bat '''
                    mvn clean test jacoco:prepare-agent jacoco:report -Dmaven.repo.local=C:\\ProgramData\\Jenkins\\.m2\\repository
                '''
            }
            post {
                always {
                    echo 'Publishing Jacoco Report...'
                    publishHTML(target: [
                        reportDir: 'target\\site\\jacoco',
                        reportFiles: 'index.html',
                        reportName: 'Jacoco Code Coverage'
                    ])
                }
            }
        }
        stage('SonarQube Analysis') {
            environment {
                SONAR_TOKEN = credentials('sonar-token')
            }
            tools {
                jdk 'java17'
            }
            steps {
                echo 'Running SonarQube Analysis...'
                bat '''
                    mvn sonar:sonar ^
                        -Dsonar.organization=vishalbhatia09 ^
                        -Dsonar.host.url=https://sonarcloud.io ^
                        -Dsonar.login=%SONAR_TOKEN% ^
                        -Dmaven.repo.local=C:\\ProgramData\\Jenkins\\.m2\\repository
                '''
            }
        }
        stage('Package') {
            steps {
                echo 'Packaging the application...'
                bat '''
                    mvn clean package -DskipTests -Dmaven.repo.local=C:\\ProgramData\\Jenkins\\.m2\\repository
                '''
            }
        }
    }

    post {
        always {
            echo 'Cleaning workspace...'
            cleanWs()
        }
    }
}
