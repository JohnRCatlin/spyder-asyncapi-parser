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

import engineer.asyncapi.spyder.model.bindings.IBMMQChannelBinding010;

public class IBMMQChannelBindingParserTest {

	private static final String rawModel;

	// given
	static {
		StringBuilder sb = new StringBuilder();
		sb.append("destinationType: topic\n");
		sb.append("maxMsgLength: 104857600\n");
		sb.append("queue:\n");
		sb.append("  exclusive: false\n");
		sb.append("  isPartitioned: false\n");
		sb.append("  objectName: myQueueName\n");
		sb.append("topic:\n");
		sb.append("  durablePermitted: true\n");
		sb.append("  lastMsgRetained: false\n");
		sb.append("  objectName: myTopicName\n");
		sb.append("  string: bar\n");
		sb.append("bindingVersion: '0.1.0'\n");
		sb.append("extensions:\n");
		sb.append("  x-1: foo\n");
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
		IBMMQChannelBinding010 parsed = (IBMMQChannelBinding010) IBMMQChannelBindingParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertEquals("ibmmq", parsed.getBindingType());
		assertEquals("0.1.0", parsed.getBindingVersion());

		assertEquals("topic", parsed.getDestinationType());
		assertEquals(false, parsed.getQueueExclusive());
		assertEquals(false, parsed.getQueueIsPartitioned());
		assertEquals("myQueueName", parsed.getQueueObjectName());
		assertEquals(true, parsed.getTopicDurablePermitted());
		assertEquals(false, parsed.getTopicLastMsgRetained());
		assertEquals("myTopicName", parsed.getTopicObjectName());
		assertEquals("bar", parsed.getTopicString());

		assertEquals(1, parsed.getExtensions().size());
		assertEquals("foo", parsed.getExtensions().get("x-1"));
	}

	@After
	public void tearDown() throws Exception {
	}

}
