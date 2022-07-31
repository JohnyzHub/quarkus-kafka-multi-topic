/**
 * 
 */
package org.myweb.projects.myweb.messaging.producer.model;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

/**
 * @author Johny Shaik
 *
 */
public class QuoteDeserializer extends ObjectMapperDeserializer<Quote> {
	public QuoteDeserializer() {
		super(Quote.class);
	}
}