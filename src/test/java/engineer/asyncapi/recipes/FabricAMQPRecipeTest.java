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
package engineer.asyncapi.recipes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import engineer.asyncapi.spyder.model.AsyncAPI;
import engineer.asyncapi.spyder.model.Channel;
import engineer.asyncapi.spyder.model.Channels;
import engineer.asyncapi.spyder.model.Components;
import engineer.asyncapi.spyder.model.Info;
import engineer.asyncapi.spyder.model.License;
import engineer.asyncapi.spyder.model.Message;
import engineer.asyncapi.spyder.model.MessageTrait;
import engineer.asyncapi.spyder.model.MessageTraits;
import engineer.asyncapi.spyder.model.Messages;
import engineer.asyncapi.spyder.model.OperationTraits;
import engineer.asyncapi.spyder.model.Schema;
import engineer.asyncapi.spyder.model.Schemas;
import engineer.asyncapi.spyder.model.SecuritySchemes;
import engineer.asyncapi.spyder.model.Server;
import engineer.asyncapi.spyder.model.Servers;
import engineer.asyncapi.spyder.model.Tags;
import engineer.asyncapi.spyder.model.bindings.AMQP091ChannelBinding020;
import engineer.asyncapi.spyder.model.bindings.AMQP091MessageBinding020;
import engineer.asyncapi.spyder.model.bindings.AMQP091OperationBinding020;
import engineer.asyncapi.spyder.model.bindings.AMQP091ServerBinding020;
import engineer.asyncapi.spyder.model.bindings.ChannelBindings;
import engineer.asyncapi.spyder.model.bindings.MessageBindings;
import engineer.asyncapi.spyder.model.bindings.OperationBindings;
import engineer.asyncapi.spyder.model.bindings.ServerBindings;
import engineer.asyncapi.spyder.parser.AsyncAPIParser;
import engineer.asyncapi.spyder.parser.AsyncAPIParserFactory;
import engineer.asyncapi.spyder.parser.SupportedAsyncAPIVersions;

public class FabricAMQPRecipeTest {

	static final AsyncAPI parsed;
	// given
	static final String SUBJECT_URL = "https://raw.githubusercontent.com/JohnRCatlin/asyncapi-recipes/main/src/fabrics/fabrics.amqp.yml";

	// when
	static {
		final AsyncAPIParser parser = AsyncAPIParserFactory.create(SupportedAsyncAPIVersions.V2_0_0);
		parsed = parser.parseFromUrl(SUBJECT_URL);
	}

	@Test
	public void testAsyncapi() {
		// then
		assertEquals(SupportedAsyncAPIVersions.V2_0_0.value, parsed.getAsyncapi());
	}

	@Test
	public void testChannelBindings() {
		// then
		ChannelBindings bindings = parsed.getComponents().getChannelBindings();
		assertEquals(1, bindings.size());

		assertTrue(bindings.get("amqpExampleChannelBinding") instanceof AMQP091ChannelBinding020);
		AMQP091ChannelBinding020 binding = (AMQP091ChannelBinding020) bindings.get("amqpExampleChannelBinding");

		assertEquals("routingKey", binding.getIs());
		assertEquals("your-queue-name", binding.getQueueName());
		assertTrue(binding.isQueueDurable());
		assertTrue(binding.isQueueExclusive());
		assertFalse(binding.isQueueAutoDelete());
		assertEquals("/", binding.getQueueVHost());

		assertEquals("your-exchange-name", binding.getExchangeName());
		assertEquals("amqp", binding.getBindingType());
		assertEquals("topic", binding.getExchangeType());
		assertTrue(binding.isExchangeDurable());
		assertFalse(binding.isExchangeAutoDelete());
		assertEquals("/", binding.getExchangeVHost());

		assertEquals("0.2.0", binding.getBindingVersion());

		assertNull(binding.getExtensions());
	}

	@Test
	public void testChannels() {
		// then
		Channels channels = parsed.getChannels();
		assertNotNull(channels);
		assertEquals(6, channels.size());
	}

	@Test
	public void testCommandFabricServer() {
		// then
		final Server server = parsed.getServers().get("commandFabric");
		assertNotNull(server);
		assertNotNull(server.getUrl());
		assertNotNull(server.getProtocol());
		assertNotNull(server.getDescription());
		assertNotNull(server.getVariables());
		assertNotNull(server.getSecurity());
	}

	@Test
	public void testCommandPubChannel() {
		// then
		final Channel channel = parsed.getChannels().get("commands/publish/streetlights/1/0");
		assertNotNull(channel);
		assertNotNull(channel);
		assertNotNull(channel.getDescription());
		assertNotNull(channel.getPublish().getDescription());
		assertNotNull(channel.getPublish().getSummary());
		assertNotNull(channel.getPublish().getOperationId());
		assertNotNull(channel.getPublish().getTraits());
		assertNotNull(channel.getPublish().getTraits().get(0).getRef());
		assertNotNull(channel.getPublish().getMessage());
		assertNotNull(channel.getPublish().getMessage().getRef());
	}

	@Test
	public void testCommandSubChannel() {
		// then
		final Channel channel = parsed.getChannels().get("commands/subscribe/streetlights/1/0");
		assertNotNull(channel);
		assertNotNull(channel);
		assertNotNull(channel.getDescription());
		assertNotNull(channel.getSubscribe().getDescription());
		assertNotNull(channel.getSubscribe().getSummary());
		assertNotNull(channel.getSubscribe().getOperationId());
		assertNotNull(channel.getSubscribe().getTraits());
		assertNotNull(channel.getSubscribe().getTraits().get(0).getRef());
		assertNotNull(channel.getSubscribe().getMessage());
		assertNotNull(channel.getSubscribe().getMessage().getRef());
	}

	@Test
	public void testCommandyMessages() {
		// then
		final Message message = parsed.getComponents().getMessages().get("commandWrapper");
		assertNotNull(message);
		assertNotNull(message.getName());
		assertNotNull(message.getTitle());
		assertNotNull(message.getSummary());
		assertNotNull(message.getTraits());
		assertNotNull(message.getTraits().get(0).getRef());
		assertNotNull(message.getPayload().getRef());
	}

	@Test
	public void testComponents() {
		// then
		final Components components = parsed.getComponents();
		assertNotNull(components);
		assertNotNull(components.getMessages());
		assertNotNull(components.getSchemas());
		assertNotNull(components.getSecuritySchemes());
		assertNotNull(components.getMessageTraits());
		assertNotNull(components.getOperationTraits());
		assertNotNull(components.getMessageBindings());
		assertNotNull(components.getChannelBindings());
		assertNotNull(components.getOperationBindings());
		assertNotNull(components.getServerBindings());
		assertNull(components.getCorrelationIds());
		assertNull(components.getExtensions());
		assertNull(components.getParameters());
	}

	@Test
	public void testDefaultContetType() {
		// then
		final String defaultContentType = parsed.getDefaultContentType();
		assertNotNull(defaultContentType);
		assertEquals("application/json", defaultContentType);
	}

	@Test
	public void testEntityFabricServer() {
		// then
		final Server server = parsed.getServers().get("entityFabric");
		assertNotNull(server);
		assertNotNull(server.getUrl());
		assertNotNull(server.getProtocol());
		assertNotNull(server.getDescription());
		assertNotNull(server.getVariables());
		assertNotNull(server.getSecurity());
	}

	@Test
	public void testEntityMessages() {
		// then
		final Message message = parsed.getComponents().getMessages().get("entityWrapper");
		assertNotNull(message);
		assertNotNull(message.getName());
		assertNotNull(message.getTitle());
		assertNotNull(message.getSummary());
		assertNotNull(message.getTraits());
		assertNotNull(message.getTraits().get(0).getRef());
		assertNotNull(message.getPayload().getRef());
	}

	@Test
	public void testEntityPubChannel() {
		// then
		final Channel channel = parsed.getChannels().get("entities/publish/streetlights/1/0");
		assertNotNull(channel);
		assertNotNull(channel);
		assertNotNull(channel.getDescription());
		assertNotNull(channel.getPublish().getDescription());
		assertNotNull(channel.getPublish().getSummary());
		assertNotNull(channel.getPublish().getOperationId());
		assertNotNull(channel.getPublish().getTraits());
		assertNotNull(channel.getPublish().getTraits().get(0).getRef());
		assertNotNull(channel.getPublish().getMessage());
		assertNotNull(channel.getPublish().getMessage().getRef());
	}

	@Test
	public void testEntitySubChannel() {
		// then
		final Channel channel = parsed.getChannels().get("entities/subscribe/streetlights/1/0");
		assertNotNull(channel);
		assertNotNull(channel);
		assertNotNull(channel.getDescription());
		assertNotNull(channel.getSubscribe().getDescription());
		assertNotNull(channel.getSubscribe().getSummary());
		assertNotNull(channel.getSubscribe().getOperationId());
		assertNotNull(channel.getSubscribe().getTraits());
		assertNotNull(channel.getSubscribe().getTraits().get(0).getRef());
		assertNotNull(channel.getSubscribe().getMessage());
		assertNotNull(channel.getSubscribe().getMessage().getRef());
	}

	@Test
	public void testEventFabricServer() {
		// then
		final Server server = parsed.getServers().get("eventFabric");
		assertNotNull(server);
		assertNotNull(server.getUrl());
		assertNotNull(server.getProtocol());
		assertNotNull(server.getDescription());
		assertNotNull(server.getVariables());
		assertNotNull(server.getSecurity());
	}

	@Test
	public void testEventPubChannel() {
		// then
		final Channel channel = parsed.getChannels().get("events/publish/streetlights/1/0");
		assertNotNull(channel);
		assertNotNull(channel);
		assertNotNull(channel.getDescription());
		assertNotNull(channel.getPublish().getDescription());
		assertNotNull(channel.getPublish().getSummary());
		assertNotNull(channel.getPublish().getOperationId());
		assertNotNull(channel.getPublish().getTraits());
		assertNotNull(channel.getPublish().getTraits().get(0).getRef());
		assertNotNull(channel.getPublish().getMessage());
		assertNotNull(channel.getPublish().getMessage().getRef());
	}

	@Test
	public void testEventSubChannel() {
		// then
		final Channel channel = parsed.getChannels().get("events/subscribe/streetlights/1/0");
		assertNotNull(channel);
		assertNotNull(channel.getDescription());
		assertNotNull(channel.getSubscribe().getDescription());
		assertNotNull(channel.getSubscribe().getSummary());
		assertNotNull(channel.getSubscribe().getOperationId());
		assertNotNull(channel.getSubscribe().getTraits());
		assertNotNull(channel.getSubscribe().getTraits().get(0).getRef());
		assertNotNull(channel.getSubscribe().getMessage());
		assertNotNull(channel.getSubscribe().getMessage().getRef());
	}

	@Test
	public void testEventyMessages() {
		// then
		final Message message = parsed.getComponents().getMessages().get("eventWrapper");
		assertNotNull(message);
		assertNotNull(message.getName());
		assertNotNull(message.getTitle());
		assertNotNull(message.getSummary());
		assertNotNull(message.getTraits());
		assertNotNull(message.getTraits().get(0).getRef());
		assertNotNull(message.getPayload().getRef());
	}

	@Test
	public void testInfo() {
		// then
		final Info info = parsed.getInfo();
		assertNotNull(info);
		assertNotNull(info.getTitle());
		assertNotNull(info.getTitle());
		assertEquals("0.0.0", info.getVersion());
		assertNotNull(info.getDescription());
		assertNotNull(info.getLicense());
	}

	@Test
	public void testLicense() {
		// then
		final License license = parsed.getInfo().getLicense();
		assertNotNull(license);
		assertEquals("application/json", parsed.getDefaultContentType());
	}

	@Test
	public void testMessageBindings() {
		// then
		MessageBindings bindings = parsed.getComponents().getMessageBindings();
		assertNotNull(bindings);
		assertEquals(3, bindings.size());

		assertTrue(bindings.get("amqpCommandMessageBinding") instanceof AMQP091MessageBinding020);
		assertTrue(bindings.get("amqpEventMessageBinding") instanceof AMQP091MessageBinding020);
		assertTrue(bindings.get("amqpEntityMessageBinding") instanceof AMQP091MessageBinding020);

		AMQP091MessageBinding020 binding = (AMQP091MessageBinding020) bindings.get("amqpCommandMessageBinding");
		assertEquals("0.2.0", binding.getBindingVersion());
		assertEquals("application/json", binding.getContentEncoding());
		assertEquals("commandWrapper", binding.getMessageType());
		assertNull(binding.getExtensions());
	}

	@Test
	public void testMessages() {
		// then
		final Messages messages = parsed.getComponents().getMessages();
		assertNotNull(messages);
		assertEquals(3, messages.size());
	}

	@Test
	public void testMessageTraits() {
		// then
		MessageTraits triats = parsed.getComponents().getMessageTraits();
		assertNotNull(triats);
		assertEquals(1, triats.size());
		assertNotNull(triats.get("yourTraitName"));
		MessageTrait triat = triats.get("yourTraitName");
		assertEquals("object", triat.getHeaders().getType());
		assertNotNull(triat.getHeaders().getProperties().get("your-app-header"));
		assertEquals("integer", triat.getHeaders().getProperties().get("your-app-header").getType());
		assertEquals(0, triat.getHeaders().getProperties().get("your-app-header").getMinimum().intValue());
		assertEquals(100, triat.getHeaders().getProperties().get("your-app-header").getMaximum().intValue());
	}

	@Test
	public void testOperationBindings() {
		// then
		OperationBindings bindings = parsed.getComponents().getOperationBindings();
		assertNotNull(bindings);
		assertEquals(1, bindings.size());

		assertTrue(bindings.get("amqpExampleOperationBinding") instanceof AMQP091OperationBinding020);
		AMQP091OperationBinding020 binding = (AMQP091OperationBinding020) bindings.get("amqpExampleOperationBinding");

		assertEquals("0.2.0", binding.getBindingVersion());
		assertEquals(Long.parseLong("100000"), binding.getExpiration());
		assertEquals("guest", binding.getUserId());
		assertTrue(binding.getCc().contains("user.logs"));
		assertTrue(binding.getBcc().contains("external.audit"));
		assertEquals(Long.parseLong("10"), binding.getPriority());
		assertEquals(Long.parseLong("2"), binding.getDeliveryMode());
		assertEquals(false, binding.isMandatory());
		assertEquals(true, binding.isTimestamp());
		assertEquals(false, binding.isAck());
		assertNull(binding.getExtensions());
	}

	@Test
	public void testOperationTraits() {
		// then
		OperationTraits triats = parsed.getComponents().getOperationTraits();
		assertNotNull(triats);
		assertEquals(1, triats.size());
		assertNotNull(triats.get("yourTriatName"));
		assertTrue(triats.get("yourTriatName").getBindings() instanceof AMQP091OperationBinding020);
		AMQP091OperationBinding020 binding = (AMQP091OperationBinding020) triats.get("yourTriatName").getBindings();
		assertEquals("guest", binding.getUserId());
		assertEquals("0.2.0", binding.getBindingVersion());
	}

	@Test
	public void testParse() {
		// then
		assertNotNull(parsed);
	}

	@Test
	public void testSchemas() {
		// then
		final Schemas schemas = parsed.getComponents().getSchemas();
		assertNotNull(schemas);
		assertEquals(5, schemas.size());

		assertTrue(schemas.containsKey("lightMeasurementEntity"));
		Schema s1 = schemas.get("lightMeasurementEntity");
		assertNotNull(s1);
		assertEquals("object", s1.getType());
		assertEquals(2, s1.getProperties().size());
		assertNotNull(s1.getProperties().get("lumens"));
		assertEquals("integer", s1.getProperties().get("lumens").getType());
		assertNotNull(s1.getProperties().get("sentAt"));
		assertEquals(null, s1.getProperties().get("sentAt").getType());
		assertNotNull(s1.getProperties().get("sentAt").getRef());
		s1 = null;

		assertTrue(schemas.containsKey("lightMeasuredEvent"));
		Schema s2 = schemas.get("lightMeasuredEvent");
		assertNotNull(s2);
		assertEquals("object", s2.getType());
		assertEquals(2, s2.getProperties().size());
		assertNotNull(s2.getProperties().get("lumens"));
		assertEquals("integer", s2.getProperties().get("lumens").getType());
		assertNotNull(s2.getProperties().get("sentAt"));
		assertEquals(null, s2.getProperties().get("sentAt").getType());
		assertNotNull(s2.getProperties().get("sentAt").getRef());
		s2 = null;

		assertTrue(schemas.containsKey("turnOnOffCommand"));
		Schema s3 = schemas.get("turnOnOffCommand");
		assertNotNull(s3);
		assertEquals("object", s3.getType());
		assertEquals(2, s3.getProperties().size());
		assertNotNull(s3.getProperties().get("command"));
		assertEquals("string", s3.getProperties().get("command").getType());
		assertNotNull(s3.getProperties().get("sentAt"));
		assertEquals(null, s3.getProperties().get("sentAt").getType());
		assertNotNull(s3.getProperties().get("sentAt").getRef());
		s3 = null;

		assertTrue(schemas.containsKey("dimLightCommand"));
		Schema s4 = schemas.get("dimLightCommand");
		assertNotNull(s4);
		assertEquals("object", s4.getType());
		assertEquals(2, s4.getProperties().size());
		assertNotNull(s4.getProperties().get("percentage"));
		assertEquals("integer", s4.getProperties().get("percentage").getType());
		assertNotNull(s4.getProperties().get("sentAt"));
		assertEquals(null, s4.getProperties().get("sentAt").getType());
		assertNotNull(s4.getProperties().get("sentAt").getRef());
		s4 = null;

		assertTrue(schemas.containsKey("sentAt"));
		Schema s5 = schemas.get("sentAt");
		assertNotNull(s5);
		assertEquals("string", s5.getType());
		assertEquals("date-time", s5.getFormat());
		assertEquals(null, s5.getProperties());
		s5 = null;

	}

	@Test
	public void testSecuritySchemes() {
		// then
		final SecuritySchemes schemes = parsed.getComponents().getSecuritySchemes();
		assertNotNull(schemes);
		assertEquals(3, schemes.size());
	}

	@Test
	public void testServerBindings() {
		// then
		ServerBindings bindings = parsed.getComponents().getServerBindings();
		assertNotNull(bindings);
		assertEquals(1, bindings.size());

		assertTrue(bindings.get("amqpExampleServerBinding") instanceof AMQP091ServerBinding020);
		AMQP091ServerBinding020 binding = (AMQP091ServerBinding020) bindings.get("amqpExampleServerBinding");

		assertEquals("0.2.0", binding.getBindingVersion());
		assertNull(binding.getExtensions());
	}

	@Test
	public void testServers() {
		// then
		final Servers servers = parsed.getServers();
		assertNotNull(servers);
		assertEquals(3, servers.size());
	}

	@Test
	public void testTags() {
		// then
		Tags tags = parsed.getTags();
		assertNotNull(tags);
		assertEquals(4, tags.size());
		assertEquals("Catalog", tags.get(0).getName());
		assertEquals("Recipes", tags.get(0).getDescription());
		assertEquals("Type", tags.get(1).getName());
		assertEquals("Recipe", tags.get(1).getDescription());
		assertEquals("Access", tags.get(2).getName());
		assertEquals("Public", tags.get(2).getDescription());
		assertEquals("License", tags.get(3).getName());
		assertEquals("Apache 2.0", tags.get(3).getDescription());
	}

	@Test
	public void testToString() {
		final String visual = parsed.toString();
		assertNotNull(visual);
		//System.out.println(visual);
	}
}
