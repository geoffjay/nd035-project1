FROM openjdk:8
COPY target/project1-0.0.1-SNAPSHOT.jar /usr/bin/chat-room
CMD ["java", "-jar", "/usr/bin/chat-room"]
