pipeline {
    agent any

    stages {
        stage('Build Test') {
            steps {
                bat "mvn -D clean test"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                   publishHTML([
                       allowMissing: false,
                       alwaysLinkToLastBuild: false,
                       keepAll: false,
                       reportDir: 'target/surefire-reports/',
                       reportFiles: 'emailable-report.html',
                       reportName: 'HTML Report',
                       reportTitles: '',
                       useWrapperFileDirectly: true])
                }
            }
        }

           stage('Generate Allure Report') {
                steps {
                  // Generate Allure report
                  script {
                      bat 'allure generate target\\allure-results --clean -o target\\allure-report'
                }
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

    }
  }