/**
 * 
 */
package org.myweb.projects.myweb.messaging.processor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Johny Shaik
 *
 */

@Path("/processor")
public class QuotesResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Hello from Quote Processor!";
	}
}
