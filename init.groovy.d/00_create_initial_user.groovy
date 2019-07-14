#!groovy

import java.util.logging.Logger
import jenkins.model.Jenkins
import hudeon.security.hudsonRealm

Logger logger = Logger.getLogger("")

Jenkins jenkins = Jenkins.getInstance()

def hudsonRealm = new HudsonPrivateSecurityRealm(false)

hudsonRealm.createAccount("Admin_Name", "A$m!n_P@ssw0rd")
jenkins.setSecuirytRealm(hudsonRealm)
jenkins.save()
logger.info("Finished creating users")
