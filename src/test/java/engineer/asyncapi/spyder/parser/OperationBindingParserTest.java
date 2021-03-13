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

import engineer.asyncapi.spyder.model.bindings.AMQP091OperationBinding020;
import engineer.asyncapi.spyder.model.bindings.HTTPOperationBinding010;
import engineer.asyncapi.spyder.model.bindings.KafkaOperationBinding010;
import engineer.asyncapi.spyder.model.bindings.OperationBindings;

public class OperationBindingParserTest {

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
		OperationBindings parsed = OperationBindingsParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.containsKey("amqp"));
		assertTrue(parsed.get("amqp") instanceof AMQP091OperationBinding020);
	}
	
	@Test
	public void amqpBindingNoVersion() throws JsonMappingException, JsonProcessingException {
		// given
		final StringBuilder sb = new StringBuilder();
		sb.append("amqp:\n");
		sb.append("  ramdom: foo\n");
		final String rawModel = sb.toString();

		final ObjectMapper mapper = ObjectMapperFactory.forYaml();
		final JsonNode rootNode = mapper.readTree(rawModel);

		// when
		OperationBindings parsed = OperationBindingsParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.containsKey("amqp"));
		assertTrue(parsed.get("amqp") instanceof AMQP091OperationBinding020);
	}
	
	
	@Test
	public void amqpBindingUnsupportedVersion() throws JsonMappingException, JsonProcessingException {
		// given
		final StringBuilder sb = new StringBuilder();
		sb.append("amqp:\n");
		sb.append("  bindingVersion: '9.9.9'\n");
		final String rawModel = sb.toString();

		final ObjectMapper mapper = ObjectMapperFactory.forYaml();
		final JsonNode rootNode = mapper.readTree(rawModel);

		// when
		OperationBindings parsed = OperationBindingsParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.containsKey("amqp"));
		assertTrue(parsed.get("amqp") instanceof AMQP091OperationBinding020);
	}

	@Test
	public void HTMLBinding() throws JsonMappingException, JsonProcessingException {
		// given
		final StringBuilder sb = new StringBuilder();
		sb.append("http:\n");
		sb.append("  bindingVersion: '0.1.0'\n");
		final String rawModel = sb.toString();

		final ObjectMapper mapper = ObjectMapperFactory.forYaml();
		final JsonNode rootNode = mapper.readTree(rawModel);

		// when
		OperationBindings parsed = OperationBindingsParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.containsKey("http"));
		assertTrue(parsed.get("http") instanceof HTTPOperationBinding010);
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
		OperationBindings parsed = OperationBindingsParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.containsKey("kafka"));
		assertTrue(parsed.get("kafka") instanceof KafkaOperationBinding010);
	}
	
	@Test
	public void kafakBindingNoVersion() throws JsonMappingException, JsonProcessingException {
		// given
		final StringBuilder sb = new StringBuilder();
		sb.append("kafka:\n");
		sb.append("  ramdom: foo\n");
		final String rawModel = sb.toString();

		final ObjectMapper mapper = ObjectMapperFactory.forYaml();
		final JsonNode rootNode = mapper.readTree(rawModel);

		// when
		OperationBindings parsed = OperationBindingsParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.containsKey("kafka"));
		assertTrue(parsed.get("kafka") instanceof KafkaOperationBinding010);
	}
	
	@Test
	public void kafakBindingUnsupportedVersion() throws JsonMappingException, JsonProcessingException {
		// given
		final StringBuilder sb = new StringBuilder();
		sb.append("kafka:\n");
		sb.append("  bindingVersion: '9.9.9'\n");
		final String rawModel = sb.toString();

		final ObjectMapper mapper = ObjectMapperFactory.forYaml();
		final JsonNode rootNode = mapper.readTree(rawModel);

		// when
		OperationBindings parsed = OperationBindingsParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.containsKey("kafka"));
		assertTrue(parsed.get("kafka") instanceof KafkaOperationBinding010);
	}

	@After
	public void tearDown() throws Exception {
	}

}
