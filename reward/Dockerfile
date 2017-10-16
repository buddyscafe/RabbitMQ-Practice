#We start with a “standard” image that already includes Java 8 installed (called “Java” and tagged “8”)
FROM java:8

#We then define that a volume named /tmp should exist
VOLUME /tmp

#We then add a file from the local filesystem, naming it “app.jar.” The re-naming isn’t necessary, just an option available
ADD build/libs/reward-1.0-SNAPSHOT.jar app.jar

#We state that we want to open port 8080 on the container
EXPOSE 8080

#We run a command on the system to “touch” the file. This ensures a file modification date on the app.jar file
RUN bash -c 'touch /app.jar'

#The ENTRYPOINT command is the “what to run to ‘start'” command — we run Java, setting our Spring Mongo property and a quick additional property to speed up the Tomcat startup time, and then point it at our jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb/micros", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]







