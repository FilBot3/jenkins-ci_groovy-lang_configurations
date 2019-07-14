#!groovy

import java.util.logging.Logger
import jenkins.model.Jenkins

Logger logger = Logger.getLogger("")

Jenkins jenkins = Jenkins.getInstance()

jenkins.setNumExecutors(2)

jenkins.save()
logger.info("Number of Executors are set.")
