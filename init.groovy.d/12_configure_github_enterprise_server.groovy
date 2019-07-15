#!groovy

import java.util.logging.Logger
import org.jenkinsci.plugins.github_branch_source.GitHubConfiguration
import org.jenkinsci.plugins.github_branch_source.Endpoint
import jenkins.model.GlobalConfiguration

Logger logger = Logger.getLogger("github-enterprise-api-endpoint")

logger.info("Adding GitHub Enterprise API Endpoint")
GitHubConfiguration gitHubConfig = GlobalConfiguration.all().get(GitHubConfiguration.class)

Endpoint gheApiEndpoint = new Endpoint("https://github.cerner.com/api/v3/", "Cerner GitHub Enterprise v3 API")
List<Endpoint> endpointlist = new ArrayList<Endpoint>()
endpointlist.add(gheApiEndpoint)
gitHubConfig.setEndpoints(endpointlist)
logger.info("Added GitHub Enterprise API Endpoint")
