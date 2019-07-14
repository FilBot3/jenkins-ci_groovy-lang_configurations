#!groovy

import java.util.logging.Logger
import jenkins.model.Jenkins

Logger logger = Logger.getLogger("")

Jenkins jenkins = Jenkins.getInstance()

jenkins.setLabelString("master")

jenkins.save()
logger.info("Labels are set.")
