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
                always {
                            allure([
                                includeProperties: false,
                                results: [[path: 'GitTest/allure-results']] // Replace with your report path
                            ])
                        }
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
                    tool 'ALLURE_Home'
                    // Run Allure command to generate report
                    bat 'allure generate allure-results --clean -o GitTest\\allure-report'
                }
            }
        }

        stage('Open Allure Report') {
            steps {
                script {
                    // Open Allure report in the default web browser
                    bat 'allure open GitTest\\allure-report'
                }
            }
        }
    }
}
