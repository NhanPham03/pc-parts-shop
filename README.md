# PC Parts Shop (Maven Project)

## About the web application
This application is a mock-up website for any computer hardware retailer that uses basic Java Servlets, Java Database Connectivity and Java Persistence API for the application.

The entire project is made using ***Apache Maven*** and tested on ***Eclipse IDE for Enterprise Java and Web Developers*** with ***MySQL database***.
To make sure this web application runs smoothly, it is recommended to run using the aforementioned tools.

You can access the website at: https://pcpartsshop.onrender.com/com.pcpartsshop/

### NOTES WHEN ACCESSING WEBSITE
The first time accessing this website might take a long time loading.
> PC Parts Shop is hosted on Render's Free plan, therefore many resources are limited and slowed down when inactive.

You might run into an error (#1040): Too many connections, when performing any actions on the website.
This is due to the web application connecting to a FREE hosting service, hence the number of concurrent connections are limited, which will cause the website to crash the first time.
> Please wait for 30 seconds to 1 minute and do the same thing you tried before the error, the website should run normally by then.

# How to run
There are 2 main ways to download and run the application.

1. Cloning the repository
    - Make sure you have **Apache Maven** (Project type, not needed for Eclipse users) and **Apache Tomcat** (Server) installed!
    *(Apache Tomcat preferred version: 8.0+)*
    - To clone the repository, simply press `Code` and copy the link that is provided.
    - Then open the Terminal (or Windows PowerShell) at the desired location to save this project.
    - Run the following command:
    ```
    git clone https://link/to/the/repository
    ``` 
    - Open the Maven project in Eclipse (Or any desired IDE).
    - Run the project on **Tomcat Server**!

2. Download the WAR file
    - Make sure you have **Apache Maven** (Project type, not needed for Eclipse users) and **Apache Tomcat** (Server) installed!
    *(Apache Tomcat preferred version: 8.0+)*
    - Download **com.pcpartsshop.war** file.
    - Open Eclipse IDE and select File > Import > Web > WAR file.
    - Select the path to the **com.pcpartsshop.war** file and press Finish.
    - Run the project on **Tomcat Server**!

# Things to do after importing project (FOR BOTH METHODS)
### Configure the Build Path for this project (All the necessary .jar files are located in src/main/webapp/WEB-INF/libs)
1. Right click on com.pcpartsshop (root) > Build Path > Configure Build Path...
2. Make sure you are seeing Java Build Path and on the Libraries tab.
3. Select External JARs > Select all the JARs in the src/.../libs file > Open.
4. Apply and Close.
### Make sure you have downloaded MySQL database and created a database named "pc_parts_shop", if you haven't.
1. Open the MySQL Command Line Client.
2. Input your MySQL password (If you have an account).
3. Type the following command:
```
create database pc_parts_shop;
```
### Locate the **persistence.xml** file within the project (Found in src/main/resources/META-INF) and modify the "user", "password" values.
+ Method 1: Right click > Open With > Generic Text Editor
+ Look for the "properties" tag and change the values of
    * *jakarta.persistence.jdbc.user* to your MySQL account name (Default: root)
    * *jakarta.persistence.jdbc.password* to your MySQL account password
+ Method 2: Double click on persistence.xml
+ Select the Source tab on the bottom of the persistence.xml window but above the Console window.
    * *jakarta.persistence.jdbc.user* to your MySQL account name (Default: root)
    * *jakarta.persistence.jdbc.password* to your MySQL account password
### This step requires Visual Studio Code or any IDE that can read .bat files!
1. Locate the **run-commands.bat** (Found in src/main/webapp/scripts) and open the file.
2. Change the value of **mysql_path** to the path of your mysql.exe (Found in MySQL\MySQL Server (Version)\bin\mysql.exe).
3. mysql_path should look like this:
```
set mysql_path=path\to\your\mysql.exe
```
4. Change the values of **username** and **password** values to your respective credentials.
```
set username=your_username
set password=your_password
```
5. Save the .bat file and run! (**ONLY AFTER YOU MADE SURE THE "pc_parts_shop" DATABASE HAS ALL THE TABLES**).
    + Run the project.
    + Wait until the website loads up on your browser.
    + Press Account.
    + Try to log in (It might not log in and return an error for the first time).
    + Close the web application and open MySQL Command Line Client.
    + Type the following commands:
    ```
    use pc_parts_shop;
    ```
    ```
    show tables;
    ```
    + If there are 7 tables in the database, you can run the .bat file to load the data into Products table!

# Known problems
### I ran the project but there seems to be a problem with the database!
> Please check if you have followed every step above!

### Why can't my project access the database even though I have set up the "persistence.xml" correctly?
> Go to Project Explorer > Servers > Tomcat vX.X Server at localhost-config > context.xml > Open With > Generic Text Editor.
```
<Resource name="jdbc/pc_parts_shop" auth="Container" driverClassName="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/pc_parts_shop?autoReconnect=true" 
username="root" password="12345" 
maxActive="100" maxIdle="30" maxWait="10000" logAbandoned="true" removeAbandoned="true" removeAbandonedTimeout="60" type="javax.sql.DataSource" />
```
> Put the line of code above in between the "Context" tags, remember the change the username and password values, then save.