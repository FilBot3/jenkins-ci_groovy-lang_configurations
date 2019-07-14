pipeline {
  agent any
  stages {
    stage('No-op') {
      steps {
        sh 'ls'
      }
    }
  }
  post {
    always {
      echo 'This is always executed.'
      deleteDir()
    }
    success {
      echo 'This happens when the build succeeds.'
    }
    unstable {
      echo 'This happens when the build becomes unstable.'
    }
    failure {
      echo 'This happens when the build fails.'
    }
    changed {
      echo "This happens when the build changes statuses."
    }
  }
}