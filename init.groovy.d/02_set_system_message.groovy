#!groovy

import java.util.logging.Logger
import jenkins.model.Jenkins

Logger logger = Logger.getLogger("")

Jenkins jenkins = Jenkins.getInstance()

jenkins.setSystemMessage("This is configured by Groovy.")

jenkins.save()
logger.info("System Message set.")
