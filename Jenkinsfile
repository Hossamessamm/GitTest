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
                script {
                    // Select the Allure installation
                    tool 'NameOfYourAllureInstallation'
                    // Run Allure command to generate report
                    bat 'allure generate allure-results --clean -o target\\allure-report'
                }
            }
        }

        stage('Open Allure Report') {
            steps {
                script {
                    // Open Allure report in the default web browser
                    bat 'allure open target\\allure-report'
                }
            }
        }
    }
}
