# Chat Room

A multi-client chat room application implementation using WebSocket. All
messages that are sent are broadcast to all users.

## Running

### Development

During development the server can be built and run using the command: 

```sh
mvn spring-boot:run
```

### Packaging

To create a single `.jar` file that can be run elsewhere use the command:

```sh
mvn package
# run with
java -jar target/project1-0.0.1-SNAPSHOT.jar
```
