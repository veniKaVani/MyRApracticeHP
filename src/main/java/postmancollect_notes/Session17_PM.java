package postmancollect_notes;

public class Session17_PM {
	
	/*
	 * 
	 * SESSION 17: NOtes

What is Docker?? which gives the facility of not to have to procure the 
software--is absolutely free as well
---is the higher version of Virtual Machine
Docker Engine/ --helps to run the images, 
Docker Hub: is like Git hub, where all the vendors can upload their images
===>Push the image on to DockerHub
TO PUSH: the users should have dockerEngine/desktop on their individual
machines
docker push 
--so any one in the world can download this image==> pull the image 
docker pull
Whenever we are running the images-we create a container on top of the image
CONTAINER: has 2 things--all the containers will use the users hardware
===> the users machines GB, Ram,
the tc's will be run inside the container, and the cli report will be
generated inside the container itself
the moment the image gets pulled--the tc's will be run
VERSIONING: could be given to the images
no need to have any node, npm,...
1--has its own Operating system --which is always LINUx
2--Has its own application
---is just like Mvn repo engine
PM integration with Docker is possible, what is the used case??
dockerize the APP, ===> create a docker image --so any one can run the
testcases without any server--tomcat..

DOCKER: IS A CONTAINERIZATION SOFTWARE
=>is used to pkge our 'appn code' + 'appn dependencies' as single unit of
execution
=>we will combine app code + app dependencies in docker image
=>Docker images can be stored in docker registry (docker hub)
=>using docker image we can create Docker Container
=>Our appn will execute in Docker container

Docker HuB: is a collection/registry of all the docker images---is like
github

there will be official images stored by mysql as well

there will be for jenkins as well
to install jenkins: this is the docker image
----
docker run -p 8080:8080 -p 50000:50000 -v /your/home:/var/jenkins_home jenkins




docker cmds
--------------
docker --version
docker images
docker ps -a  

---------newman with docker cmds
docker pull postman/newman
----pulls from dockerhub to newman
first the image should be available--then the container gets created on top of the image
------------------------------docker cmd to run collection using an IMAGE
$ docker run -t postman/newman run "https://api.getpostman.com/collections/<collection-id>?apikey=<your-api-key>"

right click on collection on PM--open with sublime--there u get to see the
collection id--copy it --to be used to create a Docker image

also needs the apikey --one needed when we run the pm cli
----------the container is exited once all the exec is done

	 * 
	 * 
	 */

}
