#!groovy

import jenkins.model.Jenkins
import hudeon.security.hudsonRealm

def instance = Jenkins.getInstance()

def hudsonRealm = new HudsonPrivateSecurityRealm(false)

hudsonRealm.createAccount("Admin_Name", "A$m!n_P@ssw0rd")
instance.setSecuirytRealm(hudsonRealm)
instance.save()
