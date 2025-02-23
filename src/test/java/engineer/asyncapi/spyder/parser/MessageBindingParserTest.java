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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engineer.asyncapi.spyder.model.bindings.AMQP091MessageBinding020;
import engineer.asyncapi.spyder.model.bindings.HTTPMessageBinding010;
import engineer.asyncapi.spyder.model.bindings.IBMMQMessageBinding010;
import engineer.asyncapi.spyder.model.bindings.KafkaMessageBinding010;
import engineer.asyncapi.spyder.model.bindings.MQTTMessageBinding010;
import engineer.asyncapi.spyder.model.bindings.MessageBindings;
import org.junit.After;
import org.junit.Test;

public class MessageBindingParserTest {

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
    MessageBindings parsed = MessageBindingsParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertTrue(parsed.containsKey("amqp"));
    assertTrue(parsed.get("amqp") instanceof AMQP091MessageBinding020);
  }

  @Test
  public void amqpBindingNoVersion() throws JsonMappingException, JsonProcessingException {
    // given
    final StringBuilder sb = new StringBuilder();
    sb.append("amqp:\n");
    sb.append("  random: foo\n");
    final String rawModel = sb.toString();

    final ObjectMapper mapper = ObjectMapperFactory.forYaml();
    final JsonNode rootNode = mapper.readTree(rawModel);

    // when
    MessageBindings parsed = MessageBindingsParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertTrue(parsed.containsKey("amqp"));
    assertTrue(parsed.get("amqp") instanceof AMQP091MessageBinding020);
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
    MessageBindings parsed = MessageBindingsParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertTrue(parsed.containsKey("amqp"));
    assertTrue(parsed.get("amqp") instanceof AMQP091MessageBinding020);
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
    MessageBindings parsed = MessageBindingsParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertTrue(parsed.containsKey("http"));
    assertTrue(parsed.get("http") instanceof HTTPMessageBinding010);
  }

  @Test
  public void HTMLBindingNoVersion() throws JsonMappingException, JsonProcessingException {
    // given
    final StringBuilder sb = new StringBuilder();
    sb.append("http:\n");
    sb.append("  random: foo\n");
    final String rawModel = sb.toString();

    final ObjectMapper mapper = ObjectMapperFactory.forYaml();
    final JsonNode rootNode = mapper.readTree(rawModel);

    // when
    MessageBindings parsed = MessageBindingsParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertTrue(parsed.containsKey("http"));
    assertTrue(parsed.get("http") instanceof HTTPMessageBinding010);
  }

  @Test
  public void HTMLBindingUnsupportedVersion() throws JsonMappingException, JsonProcessingException {
    // given
    final StringBuilder sb = new StringBuilder();
    sb.append("http:\n");
    sb.append("  bindingVersion: '9.9.9'\n");
    final String rawModel = sb.toString();

    final ObjectMapper mapper = ObjectMapperFactory.forYaml();
    final JsonNode rootNode = mapper.readTree(rawModel);

    // when
    MessageBindings parsed = MessageBindingsParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertTrue(parsed.containsKey("http"));
    assertTrue(parsed.get("http") instanceof HTTPMessageBinding010);
  }

  @Test
  public void IBMMQBinding() throws JsonMappingException, JsonProcessingException {
    // given
    final StringBuilder sb = new StringBuilder();
    sb.append("ibmmq:\n");
    sb.append("  bindingVersion: '0.1.0'\n");
    final String rawModel = sb.toString();

    final ObjectMapper mapper = ObjectMapperFactory.forYaml();
    final JsonNode rootNode = mapper.readTree(rawModel);

    // when
    MessageBindings parsed = MessageBindingsParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertTrue(parsed.containsKey("ibmmq"));
    assertTrue(parsed.get("ibmmq") instanceof IBMMQMessageBinding010);
  }

  @Test
  public void IBMMQBindingNoVersion() throws JsonMappingException, JsonProcessingException {
    // given
    final StringBuilder sb = new StringBuilder();
    sb.append("ibmmq:\n");
    sb.append("  random: foo\n");
    final String rawModel = sb.toString();

    final ObjectMapper mapper = ObjectMapperFactory.forYaml();
    final JsonNode rootNode = mapper.readTree(rawModel);

    // when
    MessageBindings parsed = MessageBindingsParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertTrue(parsed.containsKey("ibmmq"));
    assertTrue(parsed.get("ibmmq") instanceof IBMMQMessageBinding010);
  }

  @Test
  public void IBMMQBindingUnsupportedVersion()
      throws JsonMappingException, JsonProcessingException {
    // given
    final StringBuilder sb = new StringBuilder();
    sb.append("ibmmq:\n");
    sb.append("  bindingVersion: '9.9.9'\n");
    final String rawModel = sb.toString();

    final ObjectMapper mapper = ObjectMapperFactory.forYaml();
    final JsonNode rootNode = mapper.readTree(rawModel);

    // when
    MessageBindings parsed = MessageBindingsParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertTrue(parsed.containsKey("ibmmq"));
    assertTrue(parsed.get("ibmmq") instanceof IBMMQMessageBinding010);
  }

  @Test
  public void kafkaBinding() throws JsonMappingException, JsonProcessingException {
    // given
    final StringBuilder sb = new StringBuilder();
    sb.append("kafka:\n");
    sb.append("  bindingVersion: '0.1.0'\n");
    final String rawModel = sb.toString();

    final ObjectMapper mapper = ObjectMapperFactory.forYaml();
    final JsonNode rootNode = mapper.readTree(rawModel);

    // when
    MessageBindings parsed = MessageBindingsParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertTrue(parsed.containsKey("kafka"));
    assertTrue(parsed.get("kafka") instanceof KafkaMessageBinding010);
  }

  @Test
  public void kafkaBindingNoVersion() throws JsonMappingException, JsonProcessingException {
    // given
    final StringBuilder sb = new StringBuilder();
    sb.append("kafka:\n");
    sb.append("  random: foo\n");
    final String rawModel = sb.toString();

    final ObjectMapper mapper = ObjectMapperFactory.forYaml();
    final JsonNode rootNode = mapper.readTree(rawModel);

    // when
    MessageBindings parsed = MessageBindingsParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertTrue(parsed.containsKey("kafka"));
    assertTrue(parsed.get("kafka") instanceof KafkaMessageBinding010);
  }

  @Test
  public void kafkaBindingUnsupportedVersion()
      throws JsonMappingException, JsonProcessingException {
    // given
    final StringBuilder sb = new StringBuilder();
    sb.append("kafka:\n");
    sb.append("  bindingVersion: '9.9.9'\n");
    final String rawModel = sb.toString();

    final ObjectMapper mapper = ObjectMapperFactory.forYaml();
    final JsonNode rootNode = mapper.readTree(rawModel);

    // when
    MessageBindings parsed = MessageBindingsParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertTrue(parsed.containsKey("kafka"));
    assertTrue(parsed.get("kafka") instanceof KafkaMessageBinding010);
  }

  @Test
  public void MQTTBinding() throws JsonMappingException, JsonProcessingException {
    // given
    final StringBuilder sb = new StringBuilder();
    sb.append("mqtt:\n");
    sb.append("  bindingVersion: '0.1.0'\n");
    final String rawModel = sb.toString();

    final ObjectMapper mapper = ObjectMapperFactory.forYaml();
    final JsonNode rootNode = mapper.readTree(rawModel);

    // when
    MessageBindings parsed = MessageBindingsParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertTrue(parsed.containsKey("mqtt"));
    assertTrue(parsed.get("mqtt") instanceof MQTTMessageBinding010);
  }

  @Test
  public void MQTTBindingNoVersion() throws JsonMappingException, JsonProcessingException {
    // given
    final StringBuilder sb = new StringBuilder();
    sb.append("mqtt:\n");
    sb.append("  random: foo\n");
    final String rawModel = sb.toString();

    final ObjectMapper mapper = ObjectMapperFactory.forYaml();
    final JsonNode rootNode = mapper.readTree(rawModel);

    // when
    MessageBindings parsed = MessageBindingsParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertTrue(parsed.containsKey("mqtt"));
    assertTrue(parsed.get("mqtt") instanceof MQTTMessageBinding010);
  }

  @Test
  public void MQTTBindingUnsupportedVersion() throws JsonMappingException, JsonProcessingException {
    // given
    final StringBuilder sb = new StringBuilder();
    sb.append("mqtt:\n");
    sb.append("  bindingVersion: '9.9.9'\n");
    final String rawModel = sb.toString();

    final ObjectMapper mapper = ObjectMapperFactory.forYaml();
    final JsonNode rootNode = mapper.readTree(rawModel);

    // when
    MessageBindings parsed = MessageBindingsParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertTrue(parsed.containsKey("mqtt"));
    assertTrue(parsed.get("mqtt") instanceof MQTTMessageBinding010);
  }

  @After
  public void tearDown() throws Exception {
  }
}
