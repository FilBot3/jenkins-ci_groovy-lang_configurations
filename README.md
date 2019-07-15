# jenkins-ci_groovy-lang_configurations

## Overview

These are examples of how to setup and configure Jenkins using Groovy-Lang. This
also includes using the scripting console, and init.groovy.d scripts. Using 
Docker as the means for deploying the Jenkins application and storing the 
changes. This involves mounting the `/var/jenkins_home/` directory as a Docker
Volume so there is persistent storage for the next time the container is ran.

When using the container, mount the location where the `init.groovy.d/` folder
is to the `/var/jenkins_home/init.groovy.d/` location in the Docker Image. Then 
when the Jenkins instance starts up, it can read the changes that are required.
There is a file named `${JENKINS_HOME}/init.groovy` that is loaded before all 
other things, then the `${JENKINS_HOME}/init.groovy.d/`. The files contained in
the `.d/` folder are loaded alpha-numerically, so name accordingly.

Using the scripting console inside of Jenkins isn't as friendly as you would
want it to be. You'll need to write your code, then copy and paste it into the
scripting console, unless you write it completely in the scripting console. I 
would recommend against that because then you do not keep your code source
controlled and might lose long periods of work. The following libraries are
already imported into the scripting console in Jenkins:

* `jenkins.*`
* `jenkins.model.*`
* `hudson.*`
* `hudson.model.*`

However, Groovy-Lang itself automatically imports these libraries:

* `java.io.*`
* `java.lang.*`
* `java.math.BigDecimal`
* `java.math.BigInteger`
* `java.net.*`
* `java.util.*`
* `groovy.lang.*`
* `groovy.util.*`

While these are automatically imported, it should be made good habit to include
these explicitly so that way when someone reads the code in the future, they can
figure out what was going on and there isn't any assumptions that could lead to
issues.

## Usage

These scripts need to be landed in the following directory:

```bash
${JENKINS_HOME}/init.groovy.d/basic-security.groovy
```

There is also a script called:

```bash
${JENKINS_HOME}/init.groovy
```

that is ran when Jenkins first starts up. 

## References

These are some examples and references

* [Jenkins Documentation](https://jenkins.io/doc/)
* [Jenkins Wiki](https://wiki.jenkins.io/display/JENKINS/Use+Jenkins)
* [GitHub: edx/jenkins-configuration](https://github.com/edx/jenkins-configuration)
* [rtyler: Dev Groovy Scripts for Jenkins](https://brokenco.de/2017/07/24/groovy-automation-for-jenkins.html)
* [Jenkins JavaDoc](https://javadoc.jenkins.io/)
* [Jenkins Plugins JavaDoc](https://javadoc.jenkins.io/plugin/)
* [Google Groups: Jenkins Users](https://groups.google.com/forum/#!forum/jenkinsci-users)
* [Groovy-Lang Home Page](http://groovy-lang.org/)