/**
 * Requires the Docker Pipeline plugin to be installed in Jenkins.
 */
node('docker') {
  checkout scm
  stage('build') {
    docker.image('maven:3.3.3').inside {
      sh 'mvn --version'
    }
  }
}