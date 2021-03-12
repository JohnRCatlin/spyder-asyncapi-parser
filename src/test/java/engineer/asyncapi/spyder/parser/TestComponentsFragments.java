/* ------------------------------------------------------------------
Copyright 2021 asyncapi.engineer

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
------------------------------------------------------------------ */
package engineer.asyncapi.spyder.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import engineer.asyncapi.spyder.model.AsyncAPI;
import engineer.asyncapi.spyder.model.CorrelationId;
import engineer.asyncapi.spyder.model.Message;
import engineer.asyncapi.spyder.model.MessageTrait;
import engineer.asyncapi.spyder.model.MessageTraits;
import engineer.asyncapi.spyder.model.OperationTrait;
import engineer.asyncapi.spyder.model.Schema;
import engineer.asyncapi.spyder.model.SecurityScheme;
import engineer.asyncapi.spyder.model.bindings.ChannelBindings;
import engineer.asyncapi.spyder.model.bindings.KafkaChannelBinding010;
import engineer.asyncapi.spyder.model.bindings.KafkaMessageBinding010;
import engineer.asyncapi.spyder.model.bindings.KafkaOperationBinding010;
import engineer.asyncapi.spyder.model.bindings.KafkaServerBinding010;
import engineer.asyncapi.spyder.model.bindings.MessageBinding;
import engineer.asyncapi.spyder.model.bindings.MessageBindings;
import engineer.asyncapi.spyder.model.bindings.OperationBindings;
import engineer.asyncapi.spyder.model.bindings.ServerBindings;
import engineer.asyncapi.spyder.model.fields.Fields;

public class TestComponentsFragments extends AsyncApiV2ParserTestBase {

	private static final String BROKEN = "/src/test/resources/fragments/components.broken.yml";
	private static final String EMPTY = "/src/test/resources/fragments/components.empty.yml";
	private static final String FULL = "/src/test/resources/fragments/components.full.yml";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBroken() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + BROKEN);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNotNull(api.getComponents());
		assertNull(api.getComponents().getSchemas());
		assertNull(api.getComponents().getMessages());
		assertNull(api.getComponents().getSecuritySchemes());
		assertNull(api.getComponents().getParameters());
		assertNull(api.getComponents().getCorrelationIds());
		assertNull(api.getComponents().getOperationTraits());
		assertNull(api.getComponents().getMessageTraits());
		assertNull(api.getComponents().getServerBindings());
		assertNull(api.getComponents().getChannelBindings());
		assertNull(api.getComponents().getOperationBindings());
		assertNull(api.getComponents().getMessageBindings());
		assertNull(api.getComponents().getExtensions());
	}

	@Test
	public void testEmpty() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + EMPTY);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNull(api.getComponents());
	}

	@Test
	public void testFullChannelBindings() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FULL);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNotNull(api.getComponents());
		assertNotNull(api.getComponents().getChannelBindings());

		ChannelBindings channelBindings = api.getComponents().getChannelBindings();

		assertEquals(1, channelBindings.size());
		assertNotNull(channelBindings.get("foo"));
		assertTrue(channelBindings.get("foo") instanceof KafkaChannelBinding010);
		KafkaChannelBinding010 kafkaChannelBinding = (KafkaChannelBinding010) channelBindings.get("foo");

		assertNotNull(kafkaChannelBinding.getBindingType());
		assertEquals("kafka", kafkaChannelBinding.getBindingType());

		assertNotNull(kafkaChannelBinding.getExtensions());
		assertEquals(2, kafkaChannelBinding.getExtensions().size());
		assertNotNull(kafkaChannelBinding.getExtensions().get("x-g"));
		assertNotNull(kafkaChannelBinding.getExtensions().get("x-h"));
		assertEquals("x-g-value", kafkaChannelBinding.getExtensions().get("x-g"));
		assertEquals("x-h-value", kafkaChannelBinding.getExtensions().get("x-h"));
	}

	@Test
	public void testFullCorrelationIds() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FULL);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNotNull(api.getComponents());
		assertNotNull(api.getComponents().getCorrelationIds());
		assertEquals(2, api.getComponents().getCorrelationIds().size());
		assertTrue(api.getComponents().getCorrelationIds().containsKey("foo"));
		assertTrue(api.getComponents().getCorrelationIds().containsKey("bar"));
		CorrelationId id1 = api.getComponents().getCorrelationIds().get("foo");
		assertEquals("foo Correlation ID", id1.getDescription());
		assertEquals("$message.header#/correlationId/foo", id1.getLocation());
		CorrelationId id2 = api.getComponents().getCorrelationIds().get("bar");
		assertEquals("$message.header#/correlationId/bar", id2.getLocation());
		assertEquals("bar Correlation ID", id2.getDescription());
	}

	@Test
	public void testFullExtensions() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FULL);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNotNull(api.getComponents());
		assertNotNull(api.getComponents().getExtensions());
		assertEquals(2, api.getComponents().getExtensions().size());
		assertNotNull(api.getComponents().getExtensions().get("x-c"));
		assertNotNull(api.getComponents().getExtensions().get("x-d"));
		assertEquals("x-c-value", api.getComponents().getExtensions().get("x-c"));
		assertEquals("x-d-value", api.getComponents().getExtensions().get("x-d"));
	}

	@Test
	public void testFullMessageBindings() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FULL);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNotNull(api.getComponents());
		assertNotNull(api.getComponents().getMessageBindings());

		MessageBindings messageBindings = api.getComponents().getMessageBindings();

		assertEquals(1, messageBindings.size());
		assertNotNull(messageBindings.get("foo"));
		assertTrue(messageBindings.get("foo") instanceof KafkaMessageBinding010);
		KafkaMessageBinding010 kafkaMessageBinding = (KafkaMessageBinding010) messageBindings.get("foo");

		assertNotNull(kafkaMessageBinding.getBindingType());
		assertEquals("kafka", kafkaMessageBinding.getBindingType());

		assertNotNull(kafkaMessageBinding.getKey());
		assertNotNull(kafkaMessageBinding.getKey().getType());
		assertEquals(Fields.STRING.value, kafkaMessageBinding.getKey().getType());
		assertNotNull(kafkaMessageBinding.getKey().getEnum());
		assertTrue(kafkaMessageBinding.getKey().getEnum().contains("myKey"));

		assertNotNull(kafkaMessageBinding.getBindingVersion());
		assertEquals("0.1.0", kafkaMessageBinding.getBindingVersion());

		assertNotNull(kafkaMessageBinding.getExtensions());
		assertEquals(2, kafkaMessageBinding.getExtensions().size());
		assertNotNull(kafkaMessageBinding.getExtensions().get("x-a"));
		assertNotNull(kafkaMessageBinding.getExtensions().get("x-b"));
		assertEquals("x-a-value", kafkaMessageBinding.getExtensions().get("x-a"));
		assertEquals("x-b-value", kafkaMessageBinding.getExtensions().get("x-b"));
	}

	@Test
	public void testFullMessages() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FULL);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNotNull(api.getComponents());
		assertNotNull(api.getComponents().getMessages());

		assertEquals(1, api.getComponents().getMessages().size());
		assertTrue(api.getComponents().getMessages().containsKey("userSignUp"));
		Message message = api.getComponents().getMessages().get("userSignUp");

		assertNotNull(message.getBindings());
		assertNotNull(message.getBindings().get("kafka"));
		MessageBinding messageBinding = message.getBindings().get("kafka");
		assertTrue(messageBinding instanceof KafkaMessageBinding010);

		assertNotNull(message.getContentType());
		assertEquals("application/json", message.getContentType());

		assertNotNull(message.getCorrelationId());
		assertEquals("Default Correlation ID", message.getCorrelationId().getDescription());
		assertEquals("$message.header#/correlationId", message.getCorrelationId().getLocation());

		assertNotNull(message.getDescription());
		assertTrue(message.getDescription().contains("Multiline description of what this action does"));
		assertTrue(message.getDescription().contains("Here you have another line."));

		assertNotNull(message.getExtensions());
		assertEquals(2, message.getExtensions().size());
		assertNotNull(message.getExtensions().get("x-q"));
		assertNotNull(message.getExtensions().get("x-r"));
		assertEquals("x-q-value", message.getExtensions().get("x-q"));
		assertEquals("x-r-value", message.getExtensions().get("x-r"));

		assertNotNull(message.getExternalDocs());
		assertEquals("Find more info here", message.getExternalDocs().getDescription());
		assertEquals("https://example.com", message.getExternalDocs().getUrl());

		assertNotNull(message.getHeaders());
		assertNotNull(message.getHeaders().getProperties());
		assertTrue(message.getHeaders().getProperties().containsKey("applicationInstanceId"));
		assertEquals("Unique identifier for a given instance of the publishing application",
				message.getHeaders().getProperties().get("applicationInstanceId").getDescription());
		assertEquals("string", message.getHeaders().getProperties().get("applicationInstanceId").getType());

		assertNotNull(message.getName());
		assertEquals("name value", message.getName());

		assertNotNull(message.getTitle());
		assertEquals("title value", message.getTitle());

		assertNotNull(message.getPayload());
		assertEquals("object", message.getPayload().getType());
		assertNotNull(message.getPayload().getProperties());
		assertEquals(2, message.getPayload().getProperties().size());
		assertNotNull(message.getPayload().getProperties().get("user"));
		assertNotNull(message.getPayload().getProperties().get("signup"));
		Schema user = message.getPayload().getProperties().get("user");
		assertEquals("#/components/schemas/userCreate", user.getRef());
		Schema signup = message.getPayload().getProperties().get("signup");
		assertEquals("#/components/schemas/signup", signup.getRef());

		assertNotNull(message.getSchemaFormat());
		assertEquals("application/schema+json;version=draft-07", message.getSchemaFormat());
	}

	@Test
	public void testFullOperationBindings() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FULL);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNotNull(api.getComponents());
		assertNotNull(api.getComponents().getOperationBindings());

		OperationBindings operationBindings = api.getComponents().getOperationBindings();

		assertEquals(1, operationBindings.size());
		assertNotNull(operationBindings.get("foo"));
		assertTrue(operationBindings.get("foo") instanceof KafkaOperationBinding010);
		KafkaOperationBinding010 kafkaOperationBinding = (KafkaOperationBinding010) operationBindings.get("foo");

		assertNotNull(kafkaOperationBinding.getBindingType());
		assertEquals("kafka", kafkaOperationBinding.getBindingType());

		assertNotNull(kafkaOperationBinding.getClientId());
		assertNotNull(kafkaOperationBinding.getClientId().getType());
		assertEquals(Fields.STRING.value, kafkaOperationBinding.getClientId().getType());
		assertNotNull(kafkaOperationBinding.getClientId().getEnum());
		assertTrue(kafkaOperationBinding.getClientId().getEnum().contains("myClientId"));

		assertNotNull(kafkaOperationBinding.getGroupId());
		assertNotNull(kafkaOperationBinding.getGroupId().getType());
		assertEquals(Fields.STRING.value, kafkaOperationBinding.getGroupId().getType());
		assertNotNull(kafkaOperationBinding.getGroupId().getEnum());
		assertTrue(kafkaOperationBinding.getGroupId().getEnum().contains("myGroupId"));

		assertNotNull(kafkaOperationBinding.getBindingVersion());
		assertEquals("0.1.0", kafkaOperationBinding.getBindingVersion());

		assertNotNull(kafkaOperationBinding.getExtensions());
		assertEquals(2, kafkaOperationBinding.getExtensions().size());
		assertNotNull(kafkaOperationBinding.getExtensions().get("x-e"));
		assertNotNull(kafkaOperationBinding.getExtensions().get("x-f"));
		assertEquals("x-e-value", kafkaOperationBinding.getExtensions().get("x-e"));
		assertEquals("x-f-value", kafkaOperationBinding.getExtensions().get("x-f"));
	}

	@Test
	public void testFullOperationTraits() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FULL);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNotNull(api.getComponents());
		assertNotNull(api.getComponents().getOperationTraits());
		assertEquals(1, api.getComponents().getOperationTraits().size());
		assertTrue(api.getComponents().getOperationTraits().containsKey("foo"));
		OperationTrait trait1 = api.getComponents().getOperationTraits().get("foo");
		assertNotNull(trait1.getBindings());
		assertEquals("foo op trait description", trait1.getDescription());
		assertNull(trait1.getExtensions());
		assertNotNull(trait1.getExternalDocs());
		assertEquals("Find more info here", trait1.getExternalDocs().getDescription());
		assertEquals("https://example.com", trait1.getExternalDocs().getUrl());
		assertNotNull(trait1.getExternalDocs().getExtensions());
		assertNotNull(trait1.getExternalDocs().getExtensions().containsKey("x-o"));
		assertEquals("x-o-value", trait1.getExternalDocs().getExtensions().get("x-o"));
		assertNull(trait1.getOperationId());
		assertNull(trait1.getRef());
		assertEquals("foo op trait summary", trait1.getSummary());
		assertNull(trait1.getTags());

	}

	@Test
	public void testFullParameters() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FULL);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNotNull(api.getComponents());
		assertNotNull(api.getComponents().getParameters());

		assertEquals(2, api.getComponents().getParameters().size());
		assertNotNull(api.getComponents().getParameters().get("userId"));
		assertEquals("Id of the user.", api.getComponents().getParameters().get("userId").getDescription());
		assertNotNull(api.getComponents().getParameters().get("userId").getSchema());
		assertEquals("$message.payload#/user/id", api.getComponents().getParameters().get("userId").getLocation());
		assertNotNull(api.getComponents().getParameters().get("subscribe"));
		assertEquals("#/components/messages/userSignedUp", api.getComponents().getParameters().get("subscribe").getRef());
	}

	@Test
	public void testFullSchemas() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FULL);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNotNull(api.getComponents());
		assertNotNull(api.getComponents().getSchemas());
		assertEquals(1, api.getComponents().getSchemas().size());
		assertTrue(api.getComponents().getSchemas().containsKey("foo"));

		Schema foo = api.getComponents().getSchemas().get("foo");
		assertEquals("object", foo.getType());
		assertEquals(1, foo.getRequiredProperties().size());
		assertEquals("name", foo.getRequiredProperties().get(0));
		assertNotNull(foo.getProperties());
		assertEquals(3, foo.getProperties().size());
		assertTrue(foo.getProperties().containsKey("name"));
		assertTrue(foo.getProperties().containsKey("address"));
		assertTrue(foo.getProperties().containsKey("age"));
		assertNotNull(foo.getExtensions());

		Schema name = foo.getProperties().get("name");
		assertEquals("string", name.getType());

		Schema address = foo.getProperties().get("address");
		assertEquals("#/components/schemas/Address", address.getRef());

		Schema age = foo.getProperties().get("age");
		assertEquals("integer", age.getType());
		assertEquals("int32", age.getFormat());
		assertEquals(0, age.getMinimum().intValue());
	}

	@Test
	public void testFullSecuritySchemes() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FULL);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNotNull(api.getComponents());
		assertNotNull(api.getComponents().getSecuritySchemes());
		assertEquals(1, api.getComponents().getSecuritySchemes().size());
		assertNotNull(api.getComponents().getSecuritySchemes().get("supportedOauthFlows"));
		SecurityScheme scheme = api.getComponents().getSecuritySchemes().get("supportedOauthFlows");

//		assertNotNull(scheme.getType());
//		assertEquals(Type.OAUTH2, scheme.getType());

		assertNotNull(scheme.getFlows());
		assertNotNull(scheme.getFlows().getImplicit());
		assertNotNull(scheme.getFlows().getImplicit().getAuthorizationUrl());
		assertEquals("https://example.com/api/oauth/dialog", scheme.getFlows().getImplicit().getAuthorizationUrl());
		assertNotNull(scheme.getFlows().getImplicit().getScopes());
		assertEquals(2, scheme.getFlows().getImplicit().getScopes().size());
		assertTrue(scheme.getFlows().getImplicit().getScopes().containsKey("write:pets"));
		assertTrue(scheme.getFlows().getImplicit().getScopes().containsKey("read:pets"));

		assertNull(scheme.getBearerFormat());
		assertNull(scheme.getDescription());
		assertNull(scheme.getExtensions());
		assertNull(scheme.getName());
		assertNull(scheme.getOpenIdConnectUrl());
		assertNull(scheme.getRef());
		assertNull(scheme.getScheme());
	}

	@Test
	public void testFullServerBindings() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FULL);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNotNull(api.getComponents());
		assertNotNull(api.getComponents().getServerBindings());

		ServerBindings serverBindings = api.getComponents().getServerBindings();

		assertEquals(1, serverBindings.size());
		assertNotNull(serverBindings.get("foo"));
		assertTrue(serverBindings.get("foo") instanceof KafkaServerBinding010);
		KafkaServerBinding010 kafkaServerBinding = (KafkaServerBinding010) serverBindings.get("foo");

		assertNotNull(kafkaServerBinding.getBindingType());
		assertEquals("kafka", kafkaServerBinding.getBindingType());

		assertNotNull(kafkaServerBinding.getExtensions());
		assertEquals(2, kafkaServerBinding.getExtensions().size());
		assertNotNull(kafkaServerBinding.getExtensions().get("x-i"));
		assertNotNull(kafkaServerBinding.getExtensions().get("x-j"));
		assertEquals("x-i-value", kafkaServerBinding.getExtensions().get("x-i"));
		assertEquals("x-j-value", kafkaServerBinding.getExtensions().get("x-j"));
	}

	@Test
	public void testFulltMessageTraits() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FULL);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNotNull(api.getComponents());
		assertNotNull(api.getComponents().getMessageTraits());

		MessageTraits messageTraits = api.getComponents().getMessageTraits();
		assertEquals(1, messageTraits.size());
		assertNotNull(messageTraits.get("fooMessageTrait"));

		MessageTrait messageTrait = messageTraits.get("fooMessageTrait");

		assertNotNull(messageTrait.getCorrelationId());
		assertEquals("Default Correlation ID", messageTrait.getCorrelationId().getDescription());
		assertEquals("$message.header#/correlationId", messageTrait.getCorrelationId().getLocation());
		assertNotNull(messageTrait.getCorrelationId().getExtensions());
		assertEquals(2, messageTrait.getCorrelationId().getExtensions().size());
		assertNotNull(messageTrait.getCorrelationId().getExtensions().get("x-m"));
		assertNotNull(messageTrait.getCorrelationId().getExtensions().get("x-n"));
		assertEquals("x-m-value", messageTrait.getCorrelationId().getExtensions().get("x-m"));
		assertEquals("x-n-value", messageTrait.getCorrelationId().getExtensions().get("x-n"));

		assertNotNull(messageTrait.getName());
		assertEquals("name value", messageTrait.getName());

		assertNotNull(messageTrait.getTitle());
		assertEquals("title value", messageTrait.getTitle());

		assertNotNull(messageTrait.getSummary());
		assertEquals("summary value", messageTrait.getSummary());

		assertNotNull(messageTrait.getDescription());
		assertEquals("description value", messageTrait.getDescription());

		assertNotNull(messageTrait.getTags());
		assertEquals(2, messageTrait.getTags().size());
		assertEquals("foo tag name", messageTrait.getTags().get(0).getName());
		assertEquals("foo tag description", messageTrait.getTags().get(0).getDescription());
		assertEquals("bar tag name", messageTrait.getTags().get(1).getName());
		assertEquals("bar tag description", messageTrait.getTags().get(1).getDescription());

		assertNotNull(messageTrait.getBindings());
		assertEquals(1, messageTrait.getBindings().size());
		assertNotNull(messageTrait.getBindings().get("kafka"));
		assertTrue(messageTrait.getBindings().get("kafka") instanceof KafkaMessageBinding010);
		KafkaMessageBinding010 kafkaMessageBinding = (KafkaMessageBinding010) messageTrait.getBindings().get("kafka");
		assertNotNull(kafkaMessageBinding.getBindingType());
		assertEquals("kafka", kafkaMessageBinding.getBindingType());
		assertNotNull(kafkaMessageBinding.getKey());
		assertNotNull(kafkaMessageBinding.getKey().getType());
		assertEquals(Fields.STRING.value, kafkaMessageBinding.getKey().getType());
		assertNotNull(kafkaMessageBinding.getKey().getEnum());
		assertTrue(kafkaMessageBinding.getKey().getEnum().contains("myKey"));
		assertNotNull(kafkaMessageBinding.getBindingVersion());
		assertEquals("0.1.0", kafkaMessageBinding.getBindingVersion());

		assertNull(messageTrait.getExamples());

		assertNotNull(messageTrait.getSchemaFormat());
		assertEquals("application/vnd.aai.asyncapi;version=2.0.0", messageTrait.getSchemaFormat());

		assertNotNull(messageTrait.getContentType());
		assertEquals("application/json", messageTrait.getContentType());

		assertNotNull(messageTrait.getExtensions());
		assertEquals(2, messageTrait.getExtensions().size());
		assertNotNull(messageTrait.getExtensions().get("x-k"));
		assertNotNull(messageTrait.getExtensions().get("x-l"));
		assertEquals("x-k-value", messageTrait.getExtensions().get("x-k"));
		assertEquals("x-l-value", messageTrait.getExtensions().get("x-l"));

		assertNotNull(messageTrait.getHeaders());
		assertNotNull(messageTrait.getHeaders().getType());
		assertEquals("object", messageTrait.getHeaders().getType());
		assertNotNull(messageTrait.getHeaders().getProperties());
		assertEquals(1, messageTrait.getHeaders().getProperties().size());
		assertTrue(messageTrait.getHeaders().getProperties().containsKey("applicationInstanceId"));
		Schema propery = messageTrait.getHeaders().getProperties().get("applicationInstanceId");
		assertNotNull(propery.getDescription());
		assertEquals("applicationInstanceId description", propery.getDescription());
		assertNotNull(propery.getType());
		assertEquals("string", propery.getType());
	}
}
