# jenkins-ci_groovy-lang_configurations
A set of Jenkins-CI configuraiton scripts writtenin Groovy-Language


These scripts need to be landed in the following directory:

```bash
${JENKINS_HOME}/init.groovy.d/basic-security.groovy
```

There is also a script called:

```bash
${JENKINS_HOME}/init.groovy
```

that is ran when Jenkins first starts up. All scripts ran in this way are ran in
this order as well:

1. `${JENKINS_HOME}/init.groovy`
2. `${JENKINS_HOME}/init.groovy.d/a.groovy`

These are ran in alpha-numeric order. After any changes are made, The Jenkins
service should be restarted.