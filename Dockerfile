FROM tomcat:9.0.80-jdk8-corretto-al2
RUN rm -rf /usr/local/tomcat/webapps/ROOT
RUN rm -rf /usr/local/tomcat/webapps/examples

# Add WAR file
ADD com.pcpartsshop.war /usr/local/tomcat/webapps/

# Add context.xml file
COPY context.xml /usr/local/tomcat/conf/
RUN touch /usr/local/tomcat/conf/context.xml

EXPOSE 8000
CMD ["catalina.sh", "run"]
