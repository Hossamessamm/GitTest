pipeline {
    agent any

    stages {
        stage('Build Test') {
            steps {
                script {
                    bat "mvn clean test"
                }
            }
    stage('Generate Allure Report') {
        steps {
            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'allure-results']]
            ])
        }
    }
    stage('Open Allure Report') {
                steps {
                    // Open Allure report in the default web browser
                    script {
                        bat 'allure open target\\allure-report'
                    }
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
    }
}
