node {
  withEnv(['DISABLE_AUTH=true',
           'DB_ENGINE=sqlite']) {
    stage('build') {
      sh 'printenv'
    }
  }
}