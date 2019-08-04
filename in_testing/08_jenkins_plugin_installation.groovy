#!groovy

import hudson.util.VersionNumber
import java.util.logging.Logger
import jenkins.model.Jenkins

Logger logger = Logger.getLogger("")

plugin_manager = Jenkins.instance.pluginManager

update_center = Jenkins.instance.updateCenter

// Disable all the current plugins if they're available.
plugin_manager.plugins.each { plugin ->
  plugin.disable()
}

def deployed = false

/**
 * activatePlugin
 * Used to activate plugins and their dependencies when being installed.
 * This is a recursive function.
 * 
 * @param plugin [Object] The Jenkins plugin reference.
 *
 * @return nil
 */
def activatePlugin(plugin) {
  if (!plugin.isEnabled()) {
    plugin.enable()
    deployed = true
  }

  plugin.getDependencies().each { dep ->
    activatePlugin(plugin_manager.getPlugin(dep.shortName))
  }
}

/**
 * Determine if the plugins should be installed with versions or not.
 * This is mainly for the first run of the application because Jenkins needs to
 * get the initial set of plugins to run. Once that is installed, then we can
 * install the plugins again with the version we want, allowing us to lock them
 * down.
 */
plugin_versions = false

[
  'ant':'1.9',
  'build-timeout':'1.19',
  'email-ext':'2.63',
  'github-branch-source':'2.4.1',
  'gradle':'1.29',
  'jobConfigHistory':'2.19',
  'ldap':'1.20',
  'matrix-auth':'2.3',
  'antisamy-markup-formatter': '1.5',
  'pam-auth':'1.4',
  'workflow-aggregator':'2.6',
  'pipeline-github-lib':'1.0',
  'ssh-slaves':'1.29.1',
  'subversion':'2.12.1',
  'timestamper':'1.8.10'
  'ws-cleanup':'0.37'
].each { plugin, version ->
  // If the plugin is not installed
  if (! plugin_manager.getPlugin(plugin)) {
    // if plugin_verisons is true
    if (plugin_versions) {
      // Set the version number
      VersionNumber versionNumber = new VersionNumber(version)
      // Add that to the plugin metadata
      deployment = update_center.getPlugin(plugin, versionNumber).deploy(true)
    } else {
      deployment = update_center.getPlugin(plugin).deploy(true)
    }
    // Install the plugin
    deployment.get()
  }
  // activate the plugin
  activatePlugin(plugin_manager.getPlugin(plugin))
}

if 