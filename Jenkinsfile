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
                 // No need for tool block if Allure is accessible globally
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
