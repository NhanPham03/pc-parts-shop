# PC Parts Shop (Maven Project)

## About the web application
This application is a mock-up website for any computer hardware retailer that uses basic Java Servlets, Java Database Connectivity and Java Persistence API for the application.

The entire project is made using ***Apache Maven*** and tested on ***Eclipse IDE for Enterprise Java and Web Developers*** with ***MySQL database***.
To make sure this web application runs smoothly, it is recommended to run using the aforementioned tools.

## How to run
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

### Things to do after importing project (FOR BOTH METHODS)
- Configure the Build Path for this project (All the necessary .jar files are located in src/main/webapp/WEB-INF/libs)
    + Right click on com.pcpartsshop (root) > Build Path > Configure Build Path...
    + Make sure you are seeing Java Build Path and on the Libraries tab.
    + Select External JARs > Select all the JARs in the src/.../libs file > Open.
    + Apply and Close.
- Make sure you have downloaded MySQL database and created a database named "pc_parts_shop", if you haven't.
    + Open the MySQL Command Line Client.
    + Input your MySQL password (If you have an account).
    + Type the following command:
    ```
    create database pc_parts_shop;
    ```
- Locate the **persistence.xml** file within the project.
    + Path to .xml file: src/main/resources/META-INF.
    + Change the "user" and "password" values within the file:
        1. Method 1: Right click > Open With > Generic Text Editor
        2. Look for the "properties" tag and change the values of
            * *jakarta.persistence.jdbc.user* to your MySQL account name (Default: root)
            * *jakarta.persistence.jdbc.password* to your MySQL account password
        1. Method 2: Double click on persistence.xml
        2. Select the Source tab on the bottom of the persistence.xml window but above the Console window.
            * *jakarta.persistence.jdbc.user* to your MySQL account name (Default: root)
            * *jakarta.persistence.jdbc.password* to your MySQL account password
- This step requires Visual Studio Code or any IDE that can read .bat files!
    + Locate the **run-commands.bat** (Found in src/main/webapp/scripts) and open the file.
    + Change the value of **mysql_path** to the path of your mysql.exe (Found in MySQL\MySQL Server (Version)\bin\mysql.exe).
    + mysql_path should look like this:
    ```
    set mysql_path=path\to\your\mysql.exe
    ```
    + Change the values of **username** and **password** values to your respective credentials.
    ```
    set username=your_username
    set password=your_password
    ```
    + Save the .bat file and run! (**ONLY AFTER YOU MADE SURE THE "pc_parts_shop" DATABASE HAS ALL THE TABLES**).
        1. Run the project.
        2. Wait until the website loads up on your browser.
        3. Press Account.
        4. Try to log in (It might not log in and return an error for the first time).
        5. Close the web application and open MySQL Command Line Client.
        6. Type the following commands:
        ```
        use pc_parts_shop;
        ```
        ```
        show tables;
        ```
        7. If there are 7 tables in the database, you can run the .bat file to load the data into Products table!

## Known problems
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