#!groovy

import java.util.logging.Logger
import jenkins.model.Jenkins
import jenkins.model.JenkinsLocationConfiguration

Logger logger = Logger.getLogger("")

Jenkins jenkins = Jenkins.getInstance()

JenkinsLocationConfiguration locationConfig = JenkinsLocationConfiguration.get()

locationConfig.setAdminAddress("DL_CWx_ETS_TEAM_FORGE@cerner.com")
locationConfig.setUrl("http://localhost:8080/")
locationConfig.save()
logger.info("Admin email and URL set.")
