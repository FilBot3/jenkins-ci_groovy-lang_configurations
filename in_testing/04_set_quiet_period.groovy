#!groovy

import java.util.logging.Logger
import jenkins.model.Jenkins

Logger logger = Logger.getLogger("")

Jenkins jenkins = Jenkins.getInstance()

jenkins.setQuietPeriod("5")

jenkins.save()
logger.info("Quiet Period is set.")
