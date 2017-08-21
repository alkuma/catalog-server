# Catalog Server

## Overview
This is a server that serves apis exposed by catalog module.

The api documentation is available at

[Catalog API
Documentation](http://htmlpreview.github.io/?https://github.com/alkuma/newarch/blob/master/products-api.html)

## Build
To build this server from source, perform the following steps:

### Tools needed for build and test
1. Any POSIX or Windows environment (this was developed on a POSIX environment)
2. JDK 1.8.0_144-b01
1. git 2.9.2
2. gradle 4.1
4. postgres sql v 9.6.4
postgres should have a database created for use with catalog service

### How to perform the build
git clone https://github.com/alkuma/catalog-server.git
gradle clean build #this will also run the unit tests.
The war is generated in build/libs folder and is called
swagger-jaxrs-resteasy-server-1.0.0.war

Note : gradle clean build will also run the tests

## Deploy
### Tools needed to deploy
1. JRE 1.8
3. tomcat 9.0.0.M26
4. postgres sql v 9.6.4
postgres should have a database created for use with catalog service

### How to perform the deployment
To deploy, you must have a tomcat installation. 
In your tomcat installation create a folder catalog under webapps
Unzip the generated war into this folder
1. cd /path/to/tomcat/webapps
1. mkdir -p catalog
2. cp /path/to/gradle/project/build/libs/\*.war .
3. jar xf swagger-jaxrs-resteasy-server-1.0.0.war
4. rm swagger-jaxrs-resteasy-server-1.0.0.war
5. in /path/to/tomcat/webapps/catalog/WEB-INF/classes, there is a file called
jdbc.properties. Populate the correct values of postgres hostname, postgres
port, postgres database name, postgres user id, postgres password.
6. now you can start / stop tomcat using /path/to/tomcat/bin/shutdown.sh and
/path/to/tomcat/bin/startup.sh By default tomcat will start on localhost:8080
but that can be changed by tweaking config scripts.


## Test
### Tools needed to deploy
1. a browser  or any http client for making GET, POST and DELETE calls
1. a running deployment (see above).
localhost:8080 the get product api will be

### How to perform the tests
2. The endpoint will be prefixed with api, eg if tomcat is running at
localhost:8080, the api url will be
http://localhost:8080/catalog/api/products/<id>
