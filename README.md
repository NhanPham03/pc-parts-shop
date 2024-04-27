# PC Parts Shop (Java)

[![Apache Maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)  

PC Parts Shop is a mock-up website for any computer hardware retailer.  
This web application uses basic Java Servlets, Java Database Connectivity and Java Persistence API.  

The entire **Apache Maven** project is made and tested on **Eclipse IDE for Enterprise Java and Web Developers** with **MySQL database**.  
To make sure this web application runs smoothly, it is recommended to run using the aforementioned programs.  

You can access the website on:  
[![Render](https://img.shields.io/badge/Render-46E3B7?style=for-the-badge&logo=render&logoColor=white)](https://pcpartsshop.onrender.com/com.pcpartsshop/)

## Table of Contents
- [Notes](#notes-for-accessing-render)
- [Prerequisites](#prerequisites)
- [How to run (Eclipse IDE)](#how-to-run-eclipse-ide-for-enterprise-java-and-web-developers)
    - [Install dependencies](#install-dependencies)
    - [Setting up environment](#set-up-environment)
- [How to run (IntelliJ IDEA)](#how-to-run-jetbrains-intellij-idea)
    - [Install dependencies](#install-dependencies-1)
    - [Setting up environment](#set-up-environment-1)
- [Known problems](#known-problems)

## Notes for accessing Render
### The first time accessing this website will take a long time to load.
> PC Parts Shop is hosted on Render's Free plan, therefore resources are limited and slowed down when inactive.

### When accessing the website 1-2 months AFTER it's been updated, you might encounter lots of 404 errors when browsing items.
> The service that is hosting the MySQL database has been _powered down_ as this project only serves as a college project and has no commercial use.

## Prerequisites
Install MySQL database at: https://dev.mysql.com/downloads/installer/  
Download and Extract Apache Maven *(Preferred version: 3.9.5+)* from: https://maven.apache.org/download.cgi  
Download and Extract Apache Tomcat *(Preferred version: 8.0+)* from: https://tomcat.apache.org/  
Project source code:  
- To clone the repository, simply press the `Code` button and copy the link.
- Then open the Terminal (or Windows PowerShell) at the desired location to clone this project.
- Run `git clone https://github.com/NhanPham03/pc-parts-shop-java.git`.
- Open the project in your preferred IDE.

Recommended plugins (For *IntelliJ IDEA* users):
- Maven Helper (Vojtěch Krása): https://plugins.jetbrains.com/plugin/7179-maven-helper  
- Smart Tomcat (zeng kid): https://plugins.jetbrains.com/plugin/9492-smart-tomcat  

# How to run (Eclipse IDE for Enterprise Java and Web Developers)
Don't forget to check [this section](#known-problems) if you happen to run into any unexpected errors!  

## Install dependencies
### Java libraries
**NOTE**: All .jar files are located in `src/main/webapp/WEB-INF/libs`
1. Right click on com.pcpartsshop (root) > Build Path > Configure Build Path...
2. Make sure you are seeing Java Build Path and on the Libraries tab.
3. Select External JARs > Select all .jar files > Open.
4. Apply and Close.

### Create the Database
**NOTE**: If you want to change the database name, don't forget to change it in the `persistence.xml` and `context.xml` too!
1. Open the MySQL Command Line Client and log in to your account.
2. Run `create database pc_parts_shop;` (Don't forget the semicolon!)

## Set up environment
### Modify persistence.xml
**NOTE**: persistence.xml is located in `src/main/resources/META-INF`.  
- Locate the `persistence.xml`.
- Look for the `<properties>` tag and change these values:
    + *jakarta.persistence.jdbc.url*: jdbc:mysql://localhost:3306/pc_parts_shop
    + *jakarta.persistence.jdbc.user*: MySQL_username (Default: root)
    + *jakarta.persistence.jdbc.password*: MySQL_password

### Set up Apache Tomcat server
> Follow the steps [here](https://www.javatpoint.com/how-to-configure-tomcat-server-in-eclipse-ide#:~:text=For%20configuring%20the%20tomcat%20server,%2D%3E%20addAll%20%2D%3E%20Finish.)!

### Modify batch script
**NOTE**: You need to *EDIT* the script, not *RUN* it!
1. Locate `run-commands.bat` (src/main/webapp/scripts) and open the script.
2. Change the value of `mysql_path` to the setup path of your mysql.exe .
```
set mysql_path=path\to\your\mysql.exe
# mysql.exe can be found at (...\Program Files\MySQL\MySQL Server (Version)\bin\).
```
3. Change the values of `username` and `password` values to your respective credentials.
```
set username=your_username
set password=your_password
```
4. Save the `.bat` script and run it.
5. If step 4 fails, troubleshoot [here](#troubleshoot-batch-script-not-running).

# How to run (JetBrains IntelliJ IDEA)
Don't forget to check [this section](#known-problems) if you happen to run into any unexpected errors!  

## Install dependencies
### Java libraries
**NOTE**: All .jar files are located in `src/main/webapp/WEB-INF/libs`.  
1. Right click on pcpartsshop (root) > Open Module Settings.
2. Navigate to Global Libraries tab > Add Java library > Select all the .jar files.
3. Navigate to Module tab > Select Dependencies > Add the Library (*Step 2*) and tick the checkbox > Apply.

### Create the Database
**NOTE**: If you want to change the database name, don't forget to change it in the `persistence.xml` and `context.xml` too!
1. Open the MySQL Command Line Client and log in to your account.
2. Run `create database pc_parts_shop;` (Don't forget the semicolon!)

## Set up environment
### Modify persistence.xml
**NOTE**: persistence.xml is located in `src/main/resources/META-INF`.  
- Locate the `persistence.xml`.
- Look for the `<properties>` tag and change these values:
    + *jakarta.persistence.jdbc.url*: jdbc:mysql://localhost:3306/pc_parts_shop
    + *jakarta.persistence.jdbc.user*: MySQL_username (Default: root)
    + *jakarta.persistence.jdbc.password*: MySQL_password

### Set up Smart Tomcat server
**NOTE**: If Smart Tomcat configurations are all blank, proceed past step 1.
1. Go to `Run/Debug Configurations > Add Smart Tomcat` (At this step, the Tomcat server should automatically configure itself).
2. Tomcat Server: Extract and Select the `Apache Tomcat`'s root directory from the .zip.
3. Catalina base: Select `C:\Users\<Your user>\.SmartTomcat\com.pcpartsshop\com.pcpartsshop`.
4. Deployment Directory: Select `com.pcpartsshop/src/main/webapp`.
5. Use classpath of module: Select `com.pcpartsshop`.
6. Context path: `/pcpartsshop`.
If you can't select classpath of module, `Right click on the project's root directory > Run Maven > Reimport > Retry select module`.

### Modify batch script
**NOTE**: You need to *EDIT* the script, not *RUN* it!
1. Locate `run-commands.bat` (src/main/webapp/scripts) and open the script.
2. Change the value of `mysql_path` to the setup path of your mysql.exe.
```
set mysql_path=path\to\your\mysql.exe
# mysql.exe can be found at (...\Program Files\MySQL\MySQL Server (Version)\bin\).
```
3. Change the values of `username` and `password` values to your respective credentials.
```
set username=your_username
set password=your_password
```
4. Save the `.bat` script and run it.
5. If step 4 fails, troubleshoot [here](#troubleshoot-batch-script-not-running).

# KNOWN PROBLEMS
### Why can't the website access the database despite having correctly modified the "persistence.xml"?
This problem tends to be unique for **Eclipse IDE** users.  
> Go to `Project Explorer > Servers > Tomcat vX.X Server at localhost-config > context.xml > Open With > Generic Text Editor`.  
> Change the "url" `HOST` (localhost or 127.0.0.1), `PORT` (Default: 3306).  
> Change the `username` and `password` values.  
> Then insert the tags below in between `<Context>`.  
```
<Resource name="jdbc/pc_parts_shop" auth="Container" driverClassName="com.mysql.cj.jdbc.Driver" 
url="jdbc:mysql://HOST:PORT/pc_parts_shop?autoReconnect=true" 
username="USERNAME" password="PASSWORD" 
maxActive="100" maxIdle="30" maxWait="10000" logAbandoned="true" removeAbandoned="true" removeAbandonedTimeout="60" type="javax.sql.DataSource" />
```

### Troubleshoot batch script not running
> Make sure you have followed all the steps provided to modify this script!  
> Run `com.pcpartsshop` on Tomcat Server, then access any Catalog page or Account information page.  
> The reason this happens is because the ORM hasn't fired up and hasn't created any tables in the database.  
> After this, open MySQL Command Line Client and run these commands to see if tables have been created or not, then run the batch script again. 
```
use pc_parts_shop;

show tables;
```

### If you encounter either of these errors:
org.apache.jasper.JasperException: org.apache.jasper.JasperException: Failed to load or instantiate TagLibraryValidator class: [org.apache.taglibs.standard.tlv.JstlCoreTLV]  
org.apache.jasper.JasperException: java.lang.ClassNotFoundException: org.apache.jsp.home_jsp  
> Make sure you have already added the necessary libraries located in src/main/webapp/WEB-INF/libs.  
> Locate the `pom.xml` file (src/main/webapp/WEB-INF).  
> Add `<scope>` tags with `provided` value to **ALL** dependencies.  
```
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>3.8.1</version>
        <scope>provided</scope>
    </dependency>
    ...
</dependencies>
```
