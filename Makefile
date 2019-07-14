start-jenkins:
	docker \
	  run \
	  --rm \
	  --interactive \
	  --tty \
	  --detach \
	  --port 8080:8080 \
	  --volume "${PWD}/init.groovy.d:/var/jenkins_home/init.groovy.d" \
	  --volume "${PWD}/jenkins-temp:/var/jenkins_home" \
	  --env "JAVA_OPTS=-Djenkins.install.runSetupWizard=false" \
	  --name jenkins-server \
	  jenkins/jenkins:lts-alpine

stop-jenkins:
	docker stop jenkins-server
