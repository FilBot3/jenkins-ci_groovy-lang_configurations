#!groovy

import java.util.logging.Logger
import jenkins.model.Jenkins
import hudson.security.HudsonPrivateSecurityRealm

Logger logger = Logger.getLogger("")

Jenkins jenkins = Jenkins.getInstance()

def hudsonRealm = new HudsonPrivateSecurityRealm(false)

// Create a User in the context of the hudsonRealm we're wanting to use. These
// are currently local accounts, local to the Jenkins install.
hudsonRealm.createAccount("phil", "mysupersecretpassword")

// Set our instance's Security Realm.
jenkins.setSecurityRealm(hudsonRealm)

// Save to our current Jenkins instance.
jenkins.save()
logger.info("Finished creating users")
