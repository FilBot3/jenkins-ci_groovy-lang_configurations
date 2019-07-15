#!groovy

import groovy.text.SimpleTemplateEngine
import jenkins.model.Jenkins

Jenkins instance = new Jenkins.get()

def job_template_data = [
  "scan_interval": "300",
  "org": "ETSForge",
  "repo_specifier": "",
  "branch_specifier": "",
  "ignore_prs": "",
]

def github_org_scan_job_template = """
<jenkins.branch.OrganizationFolder plugin="branch-api@2.0.10">
  <description></description>
  <properties>
    <org.jenkinsci.plugins.pipeline.modeldefinition.config.FolderConfig plugin="pipeline-model-definition@1.1.8">
      <dockerLabel></dockerLabel>
      <registry plugin="docker-commons@1.8"/>
    </org.jenkinsci.plugins.pipeline.modeldefinition.config.FolderConfig>
    <jenkins.branch.NoTriggerOrganizationFolderProperty>
      <branches>.*</branches>
    </jenkins.branch.NoTriggerOrganizationFolderProperty>
  </properties>
  <folderViews class="jenkins.branch.OrganizationFolderViewHolder">
    <owner reference="../.."/>
  </folderViews>
  <healthMetrics>
    <com.cloudbees.hudson.plugins.folder.health.WorstChildHealthMetric plugin="cloudbees-folder@6.0.4">
      <nonRecursive>false</nonRecursive>
    </com.cloudbees.hudson.plugins.folder.health.WorstChildHealthMetric>
  </healthMetrics>
  <icon class="jenkins.branch.MetadataActionFolderIcon">
      <owner class="jenkins.branch.OrganizationFolder" reference="../.."/>
  </icon>
  <orphanedItemStrategy class="com.cloudbees.hudson.plugins.folder.computed.DefaultOrphanedItemStrategy" plugin="cloudbees-folder@6.0.4">
    <pruneDeadBranches>true</pruneDeadBranches>
    <daysToKeep>7</daysToKeep>
    <numToKeep>20</numToKeep>
  </orphanedItemStrategy>
  <triggers>
    <com.cloudbees.hudson.plugins.folder.computed.PeriodicFolderTrigger plugin="cloudbees-folder@6.0.4">
      <spec>H H * * *</spec>
      <interval>${scan_interval}</interval>
    </com.cloudbees.hudson.plugins.folder.computed.PeriodicFolderTrigger>
  </triggers>
  <navigators>
    <org.jenkinsci.plugins.github__branch__source.GitHubSCMNavigator plugin="github-branch-source@2.3.2">
      <repoOwner>${org}</repoOwner>
      <scanCredentialsId>cerner_github_credentials</scanCredentialsId>
      <checkoutCredentialsId>SAME</checkoutCredentialsId>
      <apiUri>https://github.cerner.com/api/v3/</apiUri>
      <pattern>${repo_specifier}</pattern>
      <includes>${branch_specifier}</includes>
      <buildOriginBranch>true</buildOriginBranch>
      <buildOriginBranchWithPR>${ignore_prs}</buildOriginBranchWithPR>
      <buildOriginPRMerge>false</buildOriginPRMerge>
      <buildOriginPRHead>false</buildOriginPRHead>
      <buildForkPRMerge>${ignore_prs}</buildForkPRMerge>
      <buildForkPRHead>false</buildForkPRHead>
    </org.jenkinsci.plugins.github__branch__source.GitHubSCMNavigator>
  </navigators>
  <projectFactories>
    <org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProjectFactory plugin="workflow-multibranch@2.16">
      <scriptPath>Jenkinsfile</scriptPath>
    </org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProjectFactory>
  </projectFactories>
</jenkins.branch.OrganizationFolder>
"""

def templateEngine = new groovy.text.SimpleTemplateEngine()
def templateRendered = templateEngine.createTemplate(github_org_scan_job_template).make(job_template_data)
