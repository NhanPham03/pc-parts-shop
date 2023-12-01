FROM tomcat:9.0.80-jdk8-corretto-al2
RUN rm -rf /usr/local/tomcat/webapps/ROOT
RUN rm -rf /usr/local/tomcat/webapps/examples
ADD com.pcpartsshop.war /usr/local/tomcat/webapps/

EXPOSE 8000
CMD ["catalina.sh", "run"]
