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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.bindings.KafkaServerBinding010;

public class KafkaServerBindingParserTest {

	// given
	private static final String rawModel = "{" + "\n" + "}";

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
		KafkaServerBinding010 parsed = (KafkaServerBinding010)KafkaServerBindingParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
	}

	@After
	public void tearDown() throws Exception {
	}

}
