/**
 * Requires the Docker Pipeline Plugin to be installed in Jenkins.
 */
node('docker') {
  checkout scm
  stage('build') {
    docker.image('ruby').inside {
      sh 'ruby --version'
    }
  }
}