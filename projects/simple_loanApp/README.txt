###########################################
#
# Installation Guide
#
###########################################


1] Installing JDK
=================
This sample requireds JDK1.5.0 (not just JRE) and above. If you haven't got this installed, 
go to http://java.sun.com/javase/downloads/index.jsp download a Java SE greater or equals to 
5.0, installed it following the instructions given.

After installing it, doing a 

    java -version 

on the console should gives you something like 

    java version "1.5.0_06"
    Java(TM) 2 Runtime Environment, Standard Edition (build 1.5.0_06-b05)
    Java HotSpot(TM) Client VM (build 1.5.0_06-b05, mixed mode, sharing)

depending on the version you have installed.



########################################
#
# Running the sample application
#
#######################################

To run the application we need to :-

1] Start up the HSQLDB Database
===============================
Open up a command Console Window. 

Navigate to <installation_directory> where <installation_directory> is where you unzip this sample, and 
execute the following command

./startHsqlDb.sh  (in Linux)
startHsqlDb.bat   (in Windows)

If this starts up ok, we should see something like :-

   [Server@1a758cb]: [Thread[main,5,main]]: checkRunning(false) entered
   [Server@1a758cb]: [Thread[main,5,main]]: checkRunning(false) exited
   [Server@1a758cb]: Startup sequence initiated from main() method
   [Server@1a758cb]: Loaded properties from [/home/tmjee/development/xxx/tmp/server.properties]
   [Server@1a758cb]: Initiating startup sequence...
   [Server@1a758cb]: Server socket opened successfully in 36 ms.
   [Server@1a758cb]: Database [index=0, id=0, db=file:./hsqldb/data/site, alias=site] opened sucessfully in 1125 ms.
   [Server@1a758cb]: Startup sequence completed in 1163 ms.
   [Server@1a758cb]: 2008-10-04 10:52:47.726 HSQLDB server 1.8.0 is online
   [Server@1a758cb]: To close normally, connect and execute SHUTDOWN SQL
   [Server@1a758cb]: From command line, use [Ctrl]+[C] to abort abruptly

With this HSQLDB has been started up properly. Keep this console window as it is.

To stop HSQLDB, just press [Ctrl]+[C] on the console. 


2] Start up Tomcat Servlet Container
====================================
Open up another command Console window.

Navigate to <installation_directory>/tomcat/bin where <installation_directory> is where you unzip this sample, 
and execute the following command 

./catalina.sh run   (in Linux)
catalina.bat run    (in Windows)

to start up Tomcat. If everthing goes well, we should see something like the following :-

   Using CATALINA_BASE:   /home/tmjee/development/xxx/tmp/tomcat
   Using CATALINA_HOME:   /home/tmjee/development/xxx/tmp/tomcat
   Using CATALINA_TMPDIR: /home/tmjee/development/xxx/tmp/tomcat/temp
   Using JRE_HOME:       /usr/lib/jvm/java-1.5.0-sun/
   Oct 4, 2008 10:56:10 AM org.apache.catalina.core.AprLifecycleListener init
   INFO: The Apache Tomcat Native library which allows optimal performance in production environments was not found on the java.library.path: /usr/lib/jvm/java-1.5.0-sun-1.5.0.06/jre/lib/i386/client:/usr/lib/jvm/java-1.5.0-sun-1.5.0.06/jre/lib/i386:/usr/lib/jvm/java-1.5.0-sun-1.5.0.06/jre/../lib/i386
   Oct 4, 2008 10:56:10 AM org.apache.coyote.http11.Http11Protocol init
   INFO: Initializing Coyote HTTP/1.1 on http-8080
   Oct 4, 2008 10:56:10 AM org.apache.catalina.startup.Catalina load
   INFO: Initialization processed in 2713 ms
   Oct 4, 2008 10:56:10 AM org.apache.catalina.core.StandardService start
   INFO: Starting service Catalina
   Oct 4, 2008 10:56:10 AM org.apache.catalina.core.StandardEngine start
   INFO: Starting Servlet Engine: Apache Tomcat/6.0.14
   ....
   04/10/2008 10:56:24 INFO (FrameworkServlet.java:296) - FrameworkServlet 'site': initialization completed in 3211 ms
   Oct 4, 2008 10:56:25 AM org.apache.coyote.http11.Http11Protocol start
   INFO: Starting Coyote HTTP/1.1 on http-8080
   Oct 4, 2008 10:56:25 AM org.apache.jk.common.ChannelSocket init
   INFO: JK: ajp13 listening on /0.0.0.0:8009
   Oct 4, 2008 10:56:25 AM org.apache.jk.server.JkMain start
   INFO: Jk running ID=0 time=0/137  config=null
   Oct 4, 2008 10:56:25 AM org.apache.catalina.startup.Catalina start
   INFO: Server startup in 15007 ms

With Tomcat started up, open up a browser (eg. Firefox) and go to this url 
   
   http://localhost:8080/site/

With this you shall see the web application in action.

To stop Tomcat, just press [Ctrl]+[C] on the console.



##################################################
#
# Configuring the application
#
##################################################

Some part of the application could be configured, navigate to <installation_directory>/web/WEB-INF/classes/
directory. There you will find :-

- staticMessages.properties
- contentMessages.properties

staticMessages.properties, is where the company's name, your email address and contact number, where 
contentMessages.properties, is where some of the content of the site is documented.

After making those changes you'll need to stop Tomcat and restart it again.



################################################
#
# Using application
#
###############################################

There's only 2 kind of users in this application

1] Anonymous user
=================
- Browse content of website
- Apply Loan by filling in form

2] Administrator
================
- Browse at the loan(s) applied
- Delete the loan(s) applied
- Add another administrator
- Delete another administrator(s)
- Change his own password

To login as administrator, simply key in the username and password at the top of everye web page.

By default, there's a default administartor with username as 'admin' and password as 'admin'

Once logged in as administrator, at the top of the page, there'll be an 'Administration' link which will
allow you to perform some administartion stuff, eg. browse loans applied.


