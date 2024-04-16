# PC Parts Shop (Maven Project)

PC Parts Shop is a mock-up website for any computer hardware retailer.
This web application uses basic Java Servlets, Java Database Connectivity and Java Persistence API.

The entire project is made using **Apache Maven** and tested on **Eclipse IDE for Enterprise Java and Web Developers** with **MySQL database**.
To make sure this web application runs smoothly, it is recommended to run using the aforementioned tools.

You can access the website on: [Render](https://pcpartsshop.onrender.com/com.pcpartsshop/)

## Table of Contents
- [NOTES](#notes-for-accessing-website)
- [HOW TO RUN](#how-to-run)
    - [Download prerequisites](#download-prerequisites)
    - [Install dependencies](#install-dependencies)
    - [Setting up environment](#setting-up-environment)
- [Known problems](#known-problems)

## NOTES FOR ACCESSING WEBSITE
### The first time accessing this website will take a long time to load.
> PC Parts Shop is hosted on Render's Free plan, therefore resources are limited and slowed down when inactive.

### When accessing the website 1-2 months AFTER it's been updated, you might encounter lots of 404 errors when browsing items.
> The service that is hosting the MySQL database has been _powered down_ as this project only serves as a college project and has no commercial use.

# HOW TO RUN
## Download prerequisites
Install MySQL at: https://dev.mysql.com/downloads/installer/  
Install Apache Tomcat at: https://tomcat.apache.org/

### Cloning the repository
- Make sure you have **Apache Maven** (Project type, not needed for Eclipse users) and **Apache Tomcat** (Server) installed!
*(Apache Tomcat preferred version: 8.0+)*
- To clone the repository, simply press the `Code` button and copy the link.
- Then open the Terminal (or Windows PowerShell) at the desired location to clone this repo.
- Run: `git clone https://github.com/NhanPham03/pc-parts-shop-java.git`
- Open the Maven project in Eclipse (Or any desired IDE).

### Download the WAR file
_If you choose not to clone the repository_
- Make sure you have **Apache Maven** (Project type, not needed for Eclipse users) and **Apache Tomcat** (Server) installed!
*(Apache Tomcat preferred version: 8.0+)*
- Download **com.pcpartsshop.war** file.
- Open Eclipse IDE and select File > Import > Web > WAR file.
- Select the path to the **com.pcpartsshop.war** file and press Finish.

## Install dependencies
### All the necessary .jar files are located in src/main/webapp/WEB-INF/libs
1. Right click on com.pcpartsshop (root) > Build Path > Configure Build Path...
2. Make sure you are seeing Java Build Path and on the Libraries tab.
3. Select External JARs > Select all the JARs in the src/.../libs file > Open.
4. Apply and Close.

### Create the Database
1. Open the MySQL Command Line Client and log in to your account.
2. Run: `create database pc_parts_shop;` (Don't forget the semicolon!)

## Setting up environment
### Modify persistence.xml
- Locate the `persistence.xml` (src/main/resources/META-INF)
- Look for the "properties" tag and change the values of:
    + *jakarta.persistence.jdbc.user* to your MySQL username (Default: root)
    + *jakarta.persistence.jdbc.password* to your MySQL password

### Set up Apache Tomcat server
> Follow the steps [here](https://www.javatpoint.com/how-to-configure-tomcat-server-in-eclipse-ide#:~:text=For%20configuring%20the%20tomcat%20server,%2D%3E%20addAll%20%2D%3E%20Finish.)!

### Modify batch script
This step requires _Visual Studio Code_ or any code editor that can _edit .bat scripts_!
1. Locate `run-commands.bat` (src/main/webapp/scripts) and open the script.
2. Change the value of `mysql_path` to the setup path of your mysql.exe (...\MySQL\MySQL Server (Version)\bin\mysql.exe).
3. mysql_path should look like this:
```
set mysql_path=path\to\your\mysql.exe
```
4. Change the values of `username` and `password` values to your respective credentials.
```
set username=your_username
set password=your_password
```
5. Save the .bat script and run it.
6. (_If step 5 fails_)
    + Run `com.pcpartsshop` on Tomcat Server.
    + Access the website on your browser.
    + Navigate to Account.
    + Try to log in (It might throw an error).
    + Open MySQL Command Line Client.
    + Run:
    ```
    use pc_parts_shop;

    show tables;
    ```
    + If there are 7 tables in the database, run the batch script (`run-commands.bat`) to seed the data into Products table.
    + Refresh the page and start browsing!

# KNOWN PROBLEMS
### I ran the project but there seems to be a problem with the database!
> Please check if you have followed every step above!

### Why can't my project access the database even though I have set up the "persistence.xml" correctly?
> Go to Project Explorer > Servers > Tomcat vX.X Server at localhost-config > context.xml > Open With > Generic Text Editor
> Change the "url" `HOST` (localhost or 127.0.0.1), `PORT` (Default: 3306)
> Change the `username` and `password` values
> Then insert the tags below in between `<Context>`
```
<Resource name="jdbc/pc_parts_shop" auth="Container" driverClassName="com.mysql.cj.jdbc.Driver" 
url="jdbc:mysql://HOST:PORT/pc_parts_shop?autoReconnect=true" 
username="USERNAME" password="PASSWORD" 
maxActive="100" maxIdle="30" maxWait="10000" logAbandoned="true" removeAbandoned="true" removeAbandonedTimeout="60" type="javax.sql.DataSource" />
```
