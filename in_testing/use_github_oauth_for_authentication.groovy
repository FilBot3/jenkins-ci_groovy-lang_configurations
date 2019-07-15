#!groovy

import jenkins.model.Jenkins
import org.jenkinsci.plugins.GithubSecurityRealm

Jenkins.instance.securityRealm = new GithubSecurityRealm(
    'https://github.com', 
    'https://api.github.com', 
    'x', 
    'y'
)

permissions = new hudson.security.GlobalMatrixAuthorizationStrategy()

permissions.add(Jenkins.ADMINISTER, 'aespinosa')
permissions.add(Jenkins.ADMINISTER, '#{resources('jenkins_user[chef]').id}')
permissions.add(hudson.model.View.READ, 'anonymous')
permissions.add(hudson.model.Item.READ, 'anonymous')
permissions.add(Jenkins.READ, 'anonymous')

Jenkins.instance.authorizationStrategy = permissions

Jenkins.instance.save()
