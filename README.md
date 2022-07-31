# quarkus-kafka-multi-topic
This project uses Quarkus, the Supersonic Subatomic Java Framework.
If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

Start the kafka instance 
```shell script
zookeeper-server-start.bat %kafka_home%\config\zookeeper.properties
kafka-server-start.bat %kafka_home%\config\server.properties
```

Start the quarkus application in dev mode using:
open three cmd windows and run the following commands in separate windows
and navigate to the base folder of the project ie. web-message-system
```shell script
mvn clean install -DskipTests -U -f message-producer quarkus:dev
mvn clean install -DskipTests -U -f message-processor quarkus:dev
mvn clean install -DskipTests -U -f second-processor quarkus:dev
```
open http://localhost:8080/quotes.html and request quotes, you will see that the message-processor 
and second-processor will process the quotes alternatively and returns a price for each quote.

## Related Guides

- SmallRye Reactive Messaging - Kafka Connector ([guide](https://quarkus.io/guides/kafka-reactive-getting-started)): Connect to Kafka with Reactive Messaging
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI

## Provided Code

### Reactive Messaging codestart

Use SmallRye Reactive Messaging

[Related Apache Kafka guide section...](https://quarkus.io/guides/kafka-reactive-getting-started)


### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
