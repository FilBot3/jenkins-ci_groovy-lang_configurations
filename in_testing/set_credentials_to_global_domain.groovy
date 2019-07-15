#!groovy

/**
 * Adds the Jenkins Credentials to the Global Store.
 * Maybe?
 *
 * @see https://support.cloudbees.com/hc/en-us/articles/217708168-create-credentials-from-groovy 
 */

import jenkins.model.*
import com.cloudbees.hudson.plugins.folder.*;
import com.cloudbees.hudson.plugins.folder.properties.*;
import com.cloudbees.hudson.plugins.folder.properties.FolderCredentialsProvider.FolderCredentialsProperty;
import com.cloudbees.plugins.credentials.impl.*;
import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.domains.*;

jenkins = Jenkins.instance

String id = java.util.UUID.randomUUID().toString()
Credentials c = new UsernamePasswordCredentialsImpl(
  CredentialsScope.GLOBAL, 
  id, 
  "description:"+id, 
  "user", 
  "password"
)

for (folder in jenkins.getAllItems(Folder.class)) {
  if(folder.name.equals('FolderName')){
        AbstractFolder<?> folderAbs = AbstractFolder.class.cast(folder)
    FolderCredentialsProperty property = folderAbs.getProperties().get(FolderCredentialsProperty.class)
    property.getStore().addCredentials(Domain.global(), c)
    println property.getCredentials().toString()
  }
}
