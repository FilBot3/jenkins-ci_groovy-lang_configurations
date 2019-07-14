node {
  try {
    stage('No-op') {
      sh 'ls'
    }
  }
  catch (exc) {
    echo 'This happens when a build fails.'
  }
  finally {
    if (currentBuild.result == 'UNSTABLE') {
      echo 'I am unsable!'
    } else {
      echo 'I am always ran, no matter what.'
    }
  }
}