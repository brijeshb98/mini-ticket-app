pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile -Drevision=${BUILD_NUMBER}'
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test -Drevision=${BUILD_NUMBER}'
            }
        }

        stage('SonarQube Analysis') {
            steps {

                withSonarQubeEnv('SonarQube') {

                    sh '''
                        mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
                        -Drevision=${BUILD_NUMBER} \
                        -Dsonar.projectKey=mini-ticket-app \
                        -Dsonar.projectName=mini-ticket-app
                    '''
                }
            }
        }

        stage('Quality Gate') {
            steps {

                timeout(time: 5, unit: 'MINUTES') {

                    waitForQualityGate abortPipeline: true

                }
            }
        }

        stage('Package') {
            steps {

                sh 'mvn package -DskipTests -Drevision=${BUILD_NUMBER}'

            }
        }

        stage('Publish to Nexus') {
            steps {

                withCredentials([
                    usernamePassword(
                        credentialsId: 'nexus-credentials',
                        usernameVariable: 'NEXUS_USERNAME',
                        passwordVariable: 'NEXUS_PASSWORD'
                    )
                ]) {

                    sh '''
                        cat > nexus-settings.xml <<EOF
<settings>
    <servers>
        <server>
            <id>nexus</id>
            <username>${NEXUS_USERNAME}</username>
            <password>${NEXUS_PASSWORD}</password>
        </server>
    </servers>
</settings>
EOF

                        mvn deploy \
                        -DskipTests \
                        -Drevision=${BUILD_NUMBER} \
                        -s nexus-settings.xml

                        rm -f nexus-settings.xml
                    '''
                }
            }
        }
    }
}
