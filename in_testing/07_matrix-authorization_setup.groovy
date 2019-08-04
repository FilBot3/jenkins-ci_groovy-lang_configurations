#!groovy

/**
 * This requires that matrix-auth plugin is installed.
 */

import com.cloudbees.plugins.credentials.CredentialsProvider
import jenkins.model.Jenkins
import hudson.security.GlobalMatrixAuthorizationStrategy
import hudson.security.HudsonPrivateSecurityRealm

Jenkins instance = Jenkins.getInstance()

// Create a local Jenkins user account called phil with the specified password. 
def hudsonRealm = new HudsonPrivateSecurityRealm(false)

// Create an account named phil, with the specified password.
hudsonRealm.createAccount("phil", "mysupersecretpassword")

// Set our Authentication Strategy/Realm
instance.setSecurityRealm(hudsonRealm)

// Make the phil user an Administrator on the Jenkins machine. 
def strategy = new GlobalMatrixAuthorizationStrategy()

// Anonymous Users
strategy.add( Jenkins.READ, "anonymous" ) // Overall Read
strategy.add( hudson.model.View.READ, "anonymous" ) // See Views
strategy.add( hudson.model.Item.BUILD, "anonymous" ) // Kick off Builds
strategy.add( hudson.model.Item.READ, "anonymous" ) // View the Builds

// Authenticated Users
strategy.add( Jenkins.READ, "authenticated" ) // Overall Read
strategy.add( hudson.model.View.READ, "authenticated" )
strategy.add( hudson.model.Item.BUILD, "authenticated" )
strategy.add( hudson.model.Item.READ, "authenticated" )

// Administrative Users

// Jenkins Overall Permissions
strategy.add( Jenkins.ADMINISTER, "phil" )
strategy.add( Jenkins.RUN_SCRIPTS, "phil" )
strategy.add( Jenkins.READ, "phil" )

// Credentials
strategy.add( CredentialsProvider.CREATE, "phil" )
strategy.add( CredentialsProvider.UPDATE, "phil" )
strategy.add( CredentialsProvider.VIEW, "phil" )
strategy.add( CredentialsProvider.DELETE, "phil" )
strategy.add( CredentialsProvider.MANAGE_DOMAINS, "phil" )

// Agent
strategy.add( Jenkins.MasterComputer.BUILD, "phil" )
strategy.add( Jenkins.MasterComputer.CONFIGURE, "phil" )
strategy.add( Jenkins.MasterComputer.CONNECT, "phil" )
strategy.add( Jenkins.MasterComputer.CREATE, "phil" )
strategy.add( Jenkins.MasterComputer.DELETE, "phil" )
strategy.add( Jenkins.MasterComputer.DISCONNECT, "phil" )

// Job/Item Permissions
strategy.add( hudson.model.Item.BUILD, "phil" )
strategy.add( hudson.model.Item.CANCEL, "phil" )
strategy.add( hudson.model.Item.CONFIGURE, "phil" )
strategy.add( hudson.model.Item.CREATE, "phil" )
strategy.add( hudson.model.Item.DELETE, "phil" )
strategy.add( hudson.model.Item.DISCOVER, "phil" )
strategy.add( hudson.model.Item.READ, "phil" )
strategy.add( hudson.model.Item.WIPEOUT, "phil" )
strategy.add( hudson.model.Item.WORKSPACE, "phil" )

// Run
strategy.add( hudson.model.Run.DELETE, "phil" )
strategy.add( hudson.model.Run.UPDATE, "phil" )
strategy.add( hudson.model.Run.ARTIFACTS, "phil" )

// View
strategy.add( hudson.model.View.CONFIGURE, "phil" )
strategy.add( hudson.model.View.CREATE, "phil" )
strategy.add( hudson.model.View.DELETE, "phil" )
strategy.add( hudson.model.View.READ, "phil" )

// SCM
strategy.add( hudson.scm.SCM.TAG, "phil" )

// Plugin Manager
//strategy.add( hudson.model.Hudson.UPLOAD_PLUGINS, "phil" )
//strategy.add( hudson.PluginManager.UPLOAD_PLUGINS, "phil" )
//strategy.add( hudson.PluginManager.CONFIGURE_UPDATECENTER, "phil" )

// Set our Authorization Strategy
instance.setAuthorizationStrategy(strategy)

// Save our changes. 
instance.save()

