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
    }
}
