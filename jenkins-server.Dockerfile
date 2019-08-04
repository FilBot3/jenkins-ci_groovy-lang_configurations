FROM jenkins/jenkins:lts-alpine

COPY init.groovy.d /usr/share/jenkins/ref/init.groovy.d
