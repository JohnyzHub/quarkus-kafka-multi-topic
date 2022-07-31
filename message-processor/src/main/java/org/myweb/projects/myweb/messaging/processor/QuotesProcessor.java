/**
 * 
 */
package org.myweb.projects.myweb.messaging.processor;

/**
 * @author Johny Shaik
 *
 */
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment.Strategy;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;
import org.myweb.projects.myweb.messaging.processor.model.Quote;

import io.smallrye.reactive.messaging.annotations.Blocking;

/**
 * A bean consuming data from the "quote-requests" Kafka topic (mapped to
 * "requests" channel) and giving out a random quote. The result is pushed to
 * the "quotes" Kafka topic.
 */
@ApplicationScoped
public class QuotesProcessor {

	private static final Logger LOGGER = Logger.getLogger(QuotesProcessor.class.getName());

	private Random random = new Random();

	@Incoming("requests")
	@Acknowledgment(Strategy.PRE_PROCESSING)
	@Outgoing("quotes-one.top")
	@Blocking
	public Quote process(Message<String> quoteRequestMessage) throws InterruptedException {
		String quoteRequest = quoteRequestMessage.getPayload();
		// simulate some hard working task
		Thread.sleep(2000);
		int price = random.nextInt(100);
		LOGGER.info("Even quoteRequest: " + quoteRequest + ", price: " + price);
		return new Quote(quoteRequest, price);
	}
}