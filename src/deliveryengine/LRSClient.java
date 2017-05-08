/**
 * 
 * @author Anupam Samanta
 */
package deliveryengine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import gov.adlnet.xapi.client.StatementClient;
import gov.adlnet.xapi.model.Activity;
import gov.adlnet.xapi.model.ActivityDefinition;
import gov.adlnet.xapi.model.Agent;
import gov.adlnet.xapi.model.InteractionComponent;
import gov.adlnet.xapi.model.Statement;
import gov.adlnet.xapi.model.Verb;
import gov.adlnet.xapi.model.Verbs;

public class LRSClient {

	private Client client;
	final ResourceBundle rb = ResourceBundle.getBundle("portal", Locale.getDefault());
	final String key1 = "LRSuri";
	final String key2 = "LRSuser";
	final String key3 = "LRSpassword";
	private final String BASE_URI = rb.getString(key1);
	private final String username = rb.getString(key2);
	private final String password = rb.getString(key3);
	private WebTarget target;

	public LRSClient() {
		client = ClientBuilder.newClient();
		target = client.target(BASE_URI);
	}

	public String SaveStatement(String UserId, String Mbox) throws IOException {
		StatementClient client = new StatementClient(BASE_URI, username, password);
		Statement statement = new Statement();
		Agent agent = new Agent();
		Verb verb = Verbs.experienced();
		agent.setMbox("mailto:" + Mbox);
		agent.setName(UserId);
		statement.setActor(agent);
		statement.setId(UUID.randomUUID().toString());
		statement.setVerb(verb);
		Activity activity = new Activity();
		activity.setId("http://example.com");
		statement.setObject(activity);
		ActivityDefinition ad = new ActivityDefinition();
		ad.setChoices(new ArrayList<InteractionComponent>());
		InteractionComponent ic = new InteractionComponent();
		ic.setId("http://example.com");
		ic.setDescription(new HashMap<String, String>());
		ic.getDescription().put("en-US", "test");
		ad.getChoices().add(ic);
		ad.setInteractionType("choice");
		ad.setMoreInfo("http://example.com");
		activity.setDefinition(ad);
		String publishedId = client.postStatement(statement);
		String responceMsg = "OK";
		return responceMsg;
	}

}
