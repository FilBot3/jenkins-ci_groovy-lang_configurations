start-jenkins:
	docker \
	  run \
	  --detach \
	  --publish 8080:8080 \
	  --volume "${PWD}/init.groovy.d:/var/jenkins_home/init.groovy.d" \
	  --volume "${PWD}/jenkins-temp:/var/jenkins_home" \
	  --env "JAVA_OPTS=-Djenkins.install.runSetupWizard=false" \
	  --name jenkins-server \
	  jenkins/jenkins:lts-alpine

stop-jenkins:
	docker stop jenkins-server

jenkins-server-interactive:
	docker \
	  run \
	  --rm \
	  --interactive \
	  --tty \
	  --publish 8080:8080 \
	  --volume "${PWD}/init.groovy.d:/var/jenkins_home/init.groovy.d" \
	  --volume "${PWD}/jenkins-temp:/var/jenkins_home" \
	  --env "JAVA_OPTS=-Djenkins.install.runSetupWizard=false" \
	  --name jenkins-server \
	  jenkins/jenkins:lts-alpine

up:
	docker-compose up --build

down: 
	docker-compose down
	docker rmi phil/jenkins-server

clean: down
	docker volume rm jenkins-data
