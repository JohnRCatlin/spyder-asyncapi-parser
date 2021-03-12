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
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.ServerVariable;

public class ServerVariableParserTest {

	private static final String Default = "443";
	private static final String Enum = "['80','443']";
	private static final String rawModel;
	private static final String xBar = "x..bar..value";
	private static final String xFoo = "x..foo..value";

	// given
	static {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("  'enum':" + Enum + ",\n");
		sb.append("  'default':'" + Default + "',\n");
		sb.append("  'extensions': {\n");
		sb.append("     'x-foo':'" + xFoo + "',\n");
		sb.append("     'x-bar':'" + xBar + "'\n");
		sb.append("  }\n");
		sb.append("}");
		rawModel = sb.toString();
	}

	private ObjectMapper mapper = null;
	private JsonNode rootNode = null;

	@Before
	public void setUp() throws Exception {
		//System.out.println(rawModel);
		mapper = ObjectMapperFactory.forYaml();
		rootNode = mapper.readTree(rawModel);
	}

	@Test
	public void shouldParse() {
		// when
		ServerVariable parsed = ServerVariableParser.parse((ObjectNode) rootNode);

		// then
		assertNotNull(parsed);
		assertTrue(parsed.getEnum().contains("80"));
		assertTrue(parsed.getEnum().contains("443"));
		assertEquals(Default, parsed.getDefault());
		assertNotNull(parsed.getExtensions());
		assertTrue(parsed.getExtensions().containsKey("x-foo"));
		assertTrue(parsed.getExtensions().containsKey("x-bar"));
	}

	@After
	public void tearDown() throws Exception {
	}
}
