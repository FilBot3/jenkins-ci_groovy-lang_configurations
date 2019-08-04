#!groovy

import jenkins.security.s2m.AdminWhitelistRule
import jenkins.model.Jenkins

/**
 * this sets some stuff according to this document:
 * @see https://wiki.jenkins.io/display/JENKINS/Slave+To+Master+Access+Control
 *
 * If you want to enable it, set to false
 * if you want to disable it, set to true
 *
 * super counter intuitive.
 */
Jenkins
  .instance
  .getInjector()
  .getInstance(AdminWhitelistRule.class)
  .setMasterKillSwitch(false)