pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK21'
        git 'Git'
    }

    environment {
        GITHUB_CREDENTIALS = credentials('evy')
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/evyco3/EcommerceUIAutomation.git', branch: 'evy', credentialsId: 'evy'
            }
        }
        stage('Build and Test') {
            steps {
                bat 'mvn clean test'
            }
        }
        stage('Generate Allure Report') {
            steps {
                bat 'mvn allure:report'
            }
        }
        stage('Publish Allure Report') {
            steps {
                allure includeProperties: false, results: [[path: 'target/allure-results']]
            }
        }
    }
}
