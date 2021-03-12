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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.bindings.AMQP091ChannelBinding020;

public class AMQP091ChannelBindingParserTest {

	private static final String bindingVersion = "0.2.0";
	private static final String exchangeAutoDelete = "false";
	private static final String exchangeDurable = "true";
	private static final String exchangeName = "myExchange";
	private static final String exchangeType = "topic";
	private static final String exchangeVhost = "/";
	private static final String is = "routingKey";
	private static final String queueAutoDelete = "false";
	private static final String queueDurable = "true";
	private static final String queueExclusive = "true";
	private static final String queueName = "my-queue-name";
	private static final String queueVhost = "/";
	private static final String rawModel;

	// given
	static {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("  is: " + is + ",\n");
		sb.append("  queue: {\n");
		sb.append("    name: " + queueName + ",\n");
		sb.append("    durable: " + queueDurable + ",\n");
		sb.append("    exclusive: " + queueExclusive + ",\n");
		sb.append("    autoDelete: " + queueAutoDelete + ",\n");
		sb.append("    vhost: " + queueVhost + "},\n");
		sb.append("  exchange: {\n");
		sb.append("    name: " + exchangeName + ",\n");
		sb.append("    type: " + exchangeType + ",\n");
		sb.append("    durable: " + exchangeDurable + ",\n");
		sb.append("    autoDelete: " + exchangeAutoDelete + ",\n");
		sb.append("    vhost: " + exchangeVhost + "},\n");
		sb.append("  bindingVersion: " + bindingVersion + ",\n");
		sb.append("}");
		rawModel = sb.toString();
	}

	private ObjectMapper mapper = null;
	private JsonNode rootNode = null;

	@Before
	public void setUp() throws Exception {
		// System.out.println(rawModel);
		mapper = ObjectMapperFactory.forYaml();
		rootNode = mapper.readTree(rawModel);
	}

	@Test
	public void shouldParse() {
		// when
		AMQP091ChannelBinding020 parsed = (AMQP091ChannelBinding020) AMQP091ChannelBindingParser
				.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertEquals(is, parsed.getIs());
		assertEquals(queueName, parsed.getQueueName());
		assertEquals(Boolean.parseBoolean(queueDurable), parsed.isQueueDurable());
		assertEquals(Boolean.parseBoolean(queueExclusive), parsed.isQueueExclusive());
		assertEquals(Boolean.parseBoolean(queueAutoDelete), parsed.isQueueAutoDelete());
		assertEquals(queueVhost, parsed.getExchangeVHost());
		assertEquals(exchangeName, parsed.getExchangeName());
		assertEquals(Boolean.parseBoolean(exchangeDurable), parsed.isExchangeDurable());
		assertEquals(exchangeType, parsed.getExchangeType());
		assertEquals(Boolean.parseBoolean(exchangeAutoDelete), parsed.isExchangeAutoDelete());
		assertEquals(exchangeVhost, parsed.getExchangeVHost());
		assertEquals(bindingVersion, parsed.getBindingVersion());
	}

	@After
	public void tearDown() throws Exception {
	}

}
