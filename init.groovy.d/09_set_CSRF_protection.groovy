#!groovy

import hudson.security.csrf.DefaultCrumbIssuer
import java.util.logging.Logger
import jenkins.model.Jenkins

Logger logger = Logger.getLogger("")

Jenkins jenkins = Jenkins.getInstance()

jenkins.setCrumbIssuer(new DefaultCrumbIssuer(true))

jenkins.save()
logger.info("CSRF Protection Enabled.")
