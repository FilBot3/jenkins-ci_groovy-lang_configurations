#!groovy

import jenkins.model.Jenkins
import jenkins.model.IdStrategy
import jenkins.security.plugins.ldap.FromGroupSearchLDAPGroupMembershipStrategy
import jenkins.security.plugins.ldap.LDAPConfiguration
import hudson.security.LDAPSecurityRealm
import hudson.security.SecurityRealm
import hudson.util.Secret

// Getting a reference of the current Jenkins Instance
Jenkins instance = Jenkins.getInstance()

String ldapServer = "ldap://kcldap.northamerica.cerner.net:3268"
String ldapRootDn = "DC=northamerica,DC=cerner,DC=net"
String ldapUserSearchBase = ""
String ldapUserSearch = "sAMAccountName={0}"
String ldapGroupSearchBase = ""
String ldapGroupSearchFilter = ""
String ldapGroupMembershipFilter = ""
FromGroupSearchLDAPGroupMembershipStrategy ldapGroupMembershipStrategy = new FromGroupSearchLDAPGroupMembershipStrategy(ldapGroupMembershipFilter)
String ldapManagerDn = "CN=Dudley\\,Phillip,OU=Users,OU=World Headquarters,OU=Office Locations,DC=northamerica,DC=cerner,DC=net"
String ldapManagerPassword = Secret.fromString("somepasswordgoeshere")
boolean inhibitInferRootDn = false
boolean disableMailAddressResolver = false
String displayNameAttributeName = "displayname"
String mailAddressAttribute = "mail"

LDAPSecurityRealm ldapSecurityRealm = new LDAPSecurityRealm(
  ldapServer,
  ldapRootDn,
  ldapUserSearchBase,
  ldapUserSearch,
  ldapGroupSearchBase,
  ldapGroupSearchFilter,
  ldapGroupMembershipStrategy,
  ldapManagerDn,
  Secret.fromString("somepasswordgoeshere"),
  inhibitInferRootDn,
  disableMailAddressResolver,
  null,
  null,
  displayNameAttributeName,
  mailAddressAttribute,
  IdStrategy.CASE_INSENSITIVE, // userIdStrategy
  IdStrategy.CASE_INSENSITIVE  // groupIdStrategy
)

ldapSecurityRealm.setDisableRolePrefixing(true)

// Update the Jenkins Security Realm
instance.setSecurityRealm(ldapSecurityRealm)
// Save the instance state.
instance.save()
