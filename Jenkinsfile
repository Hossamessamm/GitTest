pipeline {
    agent any

    stages {
        stage('Build Test') {
            steps {
                script {
                    bat "mvn clean test"
                }
            }
            post {
                success {
                    publishHTML(target: [
                        allowMissing: false,
                        alwaysLinkToLastBuild: false,
                        keepAll: true,
                        reportDir: 'target/surefire-reports',
                        reportFiles: 'index.html',
                        reportName: 'Test Results'
                    ])
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'allure-results']],
                    allureInstallation: 'NameOfYourAllureInstallation' // Specify the name of your Allure installation
                ])
            }
        }

        stage('Open Allure Report') {
            steps {
                script {
                    bat 'allure open target\\allure-report'
                }
            }
        }
    }
}
