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
            script {
                // Determine color and status banner text based on build outcome
                def buildStatus = currentBuild.result ?: 'SUCCESS'
                def bannerColor = '#28a745' // Green for success

                if (buildStatus == 'FAILURE') {
                    bannerColor = '#dc3545' // Red for failure
                } else if (buildStatus == 'UNSTABLE') {
                    bannerColor = '#ffc107' // Yellow for unstable/test failures
                } else if (buildStatus == 'ABORTED') {
                    bannerColor = '#6c757d' // Gray for aborted
                }

                emailext (
                    to: 'nthmy988@gmail.com',
                    subject: "${buildStatus}: Job '${env.JOB_NAME}' [Build #${env.BUILD_NUMBER}]",
                    recipientProviders: [developers(), requestor()],
                    mimeType: 'text/html',
                    body: """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <style>
                            body { font-family: Arial, sans-serif; margin: 0; padding: 20px; color: #333; }
                            .banner { background-color: ${bannerColor}; color: white; padding: 15px; font-size: 20px; font-weight: bold; border-radius: 4px 4px 0 0; }
                            .content { border: 1px solid #ddd; padding: 20px; border-radius: 0 0 4px 4px; background-color: #f9f9f9; }
                            table { border-collapse: collapse; width: 100%; margin-top: 15px; }
                            th, td { text-align: left; padding: 12px; border-bottom: 1px solid #ddd; }
                            th { background-color: #f2f2f2; width: 30%; }
                            .btn { display: inline-block; padding: 10px 20px; margin-top: 20px; background-color: #007bff; color: white !important; text-decoration: none; border-radius: 4px; font-weight: bold; }
                        </style>
                    </head>
                    <body>
                        <div class="banner">
                            Build ${buildStatus}
                        </div>
                        <div class="content">
                            <p>The Jenkins pipeline execution has completed. Below are the summary details:</p>
                            <table>
                                <tr><th>Project Name</th><td>${env.JOB_NAME}</td></tr>
                                <tr><th>Build Number</th><td>#${env.BUILD_NUMBER}</td></tr>
                                <tr><th>Duration</th><td>${currentBuild.durationString}</td></tr>
                                <tr><th>Triggered By</th><td>${currentBuild.description ?: 'Automated Trigger'}</td></tr>
                            </table>
                            <a href="${env.BUILD_URL}" class="btn">View Console Output</a>
                        </div>
                    </body>
                    </html>
                    """
                )
            }

            cleanWs(cleanWhenNotBuilt: false,
                    deleteDirs: true,
                    disableDeferredWipeout: true,
                    notFailBuild: true,
                    patterns: [[pattern: '.gitignore', type: 'INCLUDE']]
                    )
        }
    }
}