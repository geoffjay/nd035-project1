# Chat Room

A multi-client chat room application implementation using WebSocket. All
messages that are sent are broadcast to all users.

## Running

### Development

During development the server can be built and run locally using the command:

```sh
mvn spring-boot:run
```

### Docker

The application can also be run in a container use Docker.

```sh
docker build -t chat-room .
docker run -it --rm --name chatter chat-room
```

### Packaging

To create a single `.jar` file that can be run elsewhere use the command:

```sh
mvn package
# run with
java -jar target/project1-0.0.1-SNAPSHOT.jar
```
