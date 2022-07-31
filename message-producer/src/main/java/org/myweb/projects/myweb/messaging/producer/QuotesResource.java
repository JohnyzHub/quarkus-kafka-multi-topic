package org.myweb.projects.myweb.messaging.producer;

import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.jboss.logging.Logger;
import org.myweb.projects.myweb.messaging.producer.model.Quote;

import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;

@Path("/quotes")
public class QuotesResource {

	private static final Logger LOGGER = Logger.getLogger(QuotesResource.class.getName());
	// private AtomicInteger quoteCounter = new AtomicInteger(0);
	private static int quoteCounter = 0;

	@Inject
	@Channel("quote-request")
	Emitter<String> quoteRequestEmitter;

	/**
	 * Endpoint to generate a new quote request id and send it to "quote-requests"
	 * Kafka topic using the emitter.
	 */
	@POST
	@Path("/request")
	@Produces(MediaType.TEXT_PLAIN)
	public String createRequest() {
		UUID uuid = UUID.randomUUID();
		LOGGER.info("Counter: " + quoteCounter);
		if (quoteCounter % 2 == 0) {

			OutgoingKafkaRecordMetadata<String> outgoingMetaDeata = OutgoingKafkaRecordMetadata.<String>builder()
					.withTopic("even-request").withKey("aKey").build();

			Message<String> outgoingMessage = Message.of(uuid.toString()).addMetadata(outgoingMetaDeata);

			quoteRequestEmitter.send(outgoingMessage);
		} else {
			OutgoingKafkaRecordMetadata<String> outgoingMetaDeata = OutgoingKafkaRecordMetadata.<String>builder()
					.withTopic("odd-request").withKey("anotherKey").build();

			Message<String> outgoingMessage = Message.of(uuid.toString()).addMetadata(outgoingMetaDeata);

			quoteRequestEmitter.send(outgoingMessage);
		}
		quoteCounter++;
		return uuid.toString();
	}

	@Channel("quotes")
	Multi<Quote> quotes;

	/**
	 * Endpoint retrieving the "quotes" Kafka topic and sending the items to a
	 * server sent event.
	 */
	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS) // denotes that server side events (SSE) will be produced
	public Multi<Quote> stream() {
		Multi<Quote> quote = quotes.log();
		LOGGER.info("Quote: " + quote);
		return quote;
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Hello from Quote Producer!";
	}
}