pipeline {
    agent any

    tools {
        //Install the Maven version
        maven "mvn"
    }

    stages {
        stage('Clone Repository') {
            steps {
                // Clones the repository
                git branch: "${BRANCH_NAME}", url: 'https://github.com/hoangmy218/SeleniumSampleProject.git' // Replace with your repo URL and branch
            }
        }


        stage('Run Test') {
            steps {
                 maskPasswords(varPasswordPairs: [[var: 'USER_PASSWORD']], varMaskRegexes: []) {
                    bat "mvn clean verify -Durl=${params.BASE_URL} -Demail=${params.USER_EMAIL} -Dpassword=${USER_PASSWORD}"
                }
            }
            // Optional: Post-build actions for reporting
            post {
                always {

                    allure allureVersion: '3', configPath: 'allure3.js', includeProperties: false, jdk: '', report: 'target/allure-report', resultPolicy: 'LEAVE_AS_IS', results: [[path: 'target/allure-results']]

                }
            }
        }
    }

    post {
        always {
            cleanWs(cleanWhenNotBuilt: false,
                    deleteDirs: true,
                    disableDeferredWipeout: true,
                    notFailBuild: true,
                    patterns: [[pattern: '.gitignore', type: 'INCLUDE']]
                    )
        }
    }
}