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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.bindings.AMQP091ChannelBinding020;
import engineer.asyncapi.spyder.model.bindings.ChannelBindings;
import engineer.asyncapi.spyder.model.bindings.KafkaChannelBinding010;
import engineer.asyncapi.spyder.model.bindings.WebSocketsChannelBinding010;

public class ChannelBindingParserTest {

	@Test
	public void amqpBinding() throws JsonMappingException, JsonProcessingException {
		// given
		final StringBuilder sb = new StringBuilder();
		sb.append("amqp:\n");
		sb.append("  bindingVersion: '0.2.0'\n");
		final String rawModel = sb.toString();

		final ObjectMapper mapper = ObjectMapperFactory.forYaml();
		final JsonNode rootNode = mapper.readTree(rawModel);

		// when
		ChannelBindings parsed = ChannelBindingsParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.containsKey("amqp"));
		assertTrue(parsed.get("amqp") instanceof AMQP091ChannelBinding020);
	}

	@Test
	public void amqpBindingNoBindingVersion() throws JsonMappingException, JsonProcessingException {
		// given
		final StringBuilder sb = new StringBuilder();
		sb.append("amqp:\n");
		sb.append("  random: foo\n");
		final String rawModel = sb.toString();

		final ObjectMapper mapper = ObjectMapperFactory.forYaml();
		final JsonNode rootNode = mapper.readTree(rawModel);

		// when
		ChannelBindings parsed = ChannelBindingsParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.containsKey("amqp"));
		assertTrue(parsed.get("amqp") instanceof AMQP091ChannelBinding020);
	}

	@Test
	public void amqpBindingUnsupportedBindingVersion() throws JsonMappingException, JsonProcessingException {
		// given
		final StringBuilder sb = new StringBuilder();
		sb.append("amqp:\n");
		sb.append("  bindingVersion: '9.9.9'\n");
		final String rawModel = sb.toString();

		final ObjectMapper mapper = ObjectMapperFactory.forYaml();
		final JsonNode rootNode = mapper.readTree(rawModel);

		// when
		ChannelBindings parsed = ChannelBindingsParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.containsKey("amqp"));
		assertTrue(parsed.get("amqp") instanceof AMQP091ChannelBinding020);
	}

	@Test
	public void kafakBinding() throws JsonMappingException, JsonProcessingException {
		// given
		final StringBuilder sb = new StringBuilder();
		sb.append("kafka:\n");
		sb.append("  bindingVersion: '0.1.0'\n");
		final String rawModel = sb.toString();

		final ObjectMapper mapper = ObjectMapperFactory.forYaml();
		final JsonNode rootNode = mapper.readTree(rawModel);

		// when
		ChannelBindings parsed = ChannelBindingsParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.containsKey("kafka"));
		assertTrue(parsed.get("kafka") instanceof KafkaChannelBinding010);
	}

	@Test
	public void kafakBindingNoBindingVersion() throws JsonMappingException, JsonProcessingException {
		// given
		final StringBuilder sb = new StringBuilder();
		sb.append("kafka:\n");
		sb.append("  random: foo\n");
		final String rawModel = sb.toString();

		final ObjectMapper mapper = ObjectMapperFactory.forYaml();
		final JsonNode rootNode = mapper.readTree(rawModel);

		// when
		ChannelBindings parsed = ChannelBindingsParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.containsKey("kafka"));
		assertTrue(parsed.get("kafka") instanceof KafkaChannelBinding010);
	}

	@Test
	public void kafakBindingUnsupportedBindingVersion() throws JsonMappingException, JsonProcessingException {
		// given
		final StringBuilder sb = new StringBuilder();
		sb.append("kafka:\n");
		sb.append("  bindingVersion: '9.9.9'\n");
		final String rawModel = sb.toString();

		final ObjectMapper mapper = ObjectMapperFactory.forYaml();
		final JsonNode rootNode = mapper.readTree(rawModel);

		// when
		ChannelBindings parsed = ChannelBindingsParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.containsKey("kafka"));
		assertTrue(parsed.get("kafka") instanceof KafkaChannelBinding010);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void webSocketsBinding() throws JsonMappingException, JsonProcessingException {
		// given
		final StringBuilder sb = new StringBuilder();
		sb.append("websockets:\n");
		sb.append("  bindingVersion: '0.1.0'\n");
		final String rawModel = sb.toString();

		final ObjectMapper mapper = ObjectMapperFactory.forYaml();
		final JsonNode rootNode = mapper.readTree(rawModel);

		// when
		ChannelBindings parsed = ChannelBindingsParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.containsKey("websockets"));
		assertTrue(parsed.get("websockets") instanceof WebSocketsChannelBinding010);
	}

	@Test
	public void webSocketsBindingNoBindingVersion() throws JsonMappingException, JsonProcessingException {
		// given
		final StringBuilder sb = new StringBuilder();
		sb.append("websockets:\n");
		sb.append("  random: foo\n");
		final String rawModel = sb.toString();

		final ObjectMapper mapper = ObjectMapperFactory.forYaml();
		final JsonNode rootNode = mapper.readTree(rawModel);

		// when
		ChannelBindings parsed = ChannelBindingsParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.containsKey("websockets"));
		assertTrue(parsed.get("websockets") instanceof WebSocketsChannelBinding010);
	}

	@Test
	public void webSocketsBindingUnsupportedBindingVersion() throws JsonMappingException, JsonProcessingException {
		// given
		final StringBuilder sb = new StringBuilder();
		sb.append("websockets:\n");
		sb.append("  bindingVersion: '9.9.9'\n");
		final String rawModel = sb.toString();

		final ObjectMapper mapper = ObjectMapperFactory.forYaml();
		final JsonNode rootNode = mapper.readTree(rawModel);

		// when
		ChannelBindings parsed = ChannelBindingsParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.containsKey("websockets"));
		assertTrue(parsed.get("websockets") instanceof WebSocketsChannelBinding010);
	}

}
