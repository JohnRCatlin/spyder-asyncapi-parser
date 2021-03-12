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

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.Schema;

public class TestSchema2Fragments extends AsyncApiV2ParserTestBase {

	private static final String BROKEN = "/src/test/resources/fragments/schema.broken.yml";
	private static final String EMPTY = "/src/test/resources/fragments/schema.empty.yml";
	private static final String FRAGMENT_1 = "/src/test/resources/fragments/schema.1.yml";
	private static final String FRAGMENT_2 = "/src/test/resources/fragments/schema.2.yml";
	private static final String FRAGMENT_3 = "/src/test/resources/fragments/schema.3.yml";
	private static final String FRAGMENT_4 = "/src/test/resources/fragments/schema.4.yml";
	private static final String FRAGMENT_5 = "/src/test/resources/fragments/schema.5.yml";
	private static final String FULL = "/src/test/resources/fragments/schema.full.yml";

	/*
	 * 
	 */
	private static ObjectMapper mapperInstance(final String apiAsString) {
		if (apiAsString.trim().startsWith("{")) {
			return ObjectMapperFactory.forJson();
		}
		return ObjectMapperFactory.forYaml();
	}

	/*
	 * 
	 */
	private ObjectNode getSchemaNode(final String schema) {
		JsonNode rootNode = null;
		JsonNode componentsNode = null;
		JsonNode schemasNode = null;
		JsonNode testSubjectNode = null;
		FileReader reader = new FileReader();
		final String rawModel = reader.fromLocation(currentWorkingDirectory() + schema);
		final ObjectMapper mapper = mapperInstance(rawModel);
		try {
			rootNode = mapper.readTree(rawModel);
		} catch (JsonProcessingException e) {
		}
		componentsNode = rootNode.get("components");
		schemasNode = componentsNode.get("schemas");
		testSubjectNode = schemasNode.get("test");
		return (ObjectNode) testSubjectNode;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParseBroken() {
		// given
		ObjectNode node = getSchemaNode(BROKEN);

		// when
		final Schema schema = SchemaParser.parse(node);

		// then
		assertNotNull(schema);
		assertNull(schema.getTitle());
		assertNull(schema.getType());
		assertNull(schema.getRequiredProperties());
		assertNull(schema.getMultipleOf());
		assertNull(schema.getMaximum());
		assertNull(schema.isExclusiveMaximum());
		assertNull(schema.getMinimum());
		assertNull(schema.isExclusiveMinimum());
		assertNull(schema.getMaxLength());
		assertNull(schema.getMinLength());
		assertNull(schema.getPattern());
		assertNull(schema.getMaxItems());
		assertNull(schema.getMinItems());
		assertNull(schema.getUniqueItems());
		assertNull(schema.getMaxProperties());
		assertNull(schema.getMinProperties());
		assertNull(schema.getEnum());
		assertNull(schema.getConst());
		assertNull(schema.getExamples());
		assertNull(schema.getReadOnly());
		assertNull(schema.getWriteOnly());
		assertNull(schema.getProperties());
		assertNull(schema.getAdditionalProperties());
		assertNull(schema.getAdditionalItems());
		assertNull(schema.getItems());
		assertNull(schema.getPropertyNames());
		assertNull(schema.getContains());
		assertNull(schema.getAllOf());
		assertNull(schema.getOneOf());
		assertNull(schema.getAnyOf());
		assertNull(schema.getNot());
	}

	@Test
	public void testParseEmpty() {
		// given
		ObjectNode node = getSchemaNode(EMPTY);

		// when
		final Schema schema = SchemaParser.parse(node);

		// then
		assertNull(schema);
	}

	@Test
	public void testParseFragment1() {
		// given
		ObjectNode node = getSchemaNode(FRAGMENT_1);

		// when
		final Schema schema = SchemaParser.parse(node);

		// then
		assertNotNull(schema);
		assertEquals("object", schema.getType());
		assertEquals(1, schema.getRequiredProperties().size());
		assertEquals("name", schema.getRequiredProperties().get(0));
		assertNotNull(schema.getProperties());
		assertEquals(3, schema.getProperties().size());
		assertTrue(schema.getProperties().containsKey("name"));
		assertTrue(schema.getProperties().containsKey("address"));
		assertTrue(schema.getProperties().containsKey("age"));
		assertNotNull(schema.getExtensions());

		Schema name = schema.getProperties().get("name");
		assertEquals("string", name.getType());

		Schema age = schema.getProperties().get("age");
		assertEquals("integer", age.getType());
		assertEquals("int32", age.getFormat());
		assertEquals(0, age.getMinimum().intValue());

		Schema address = schema.getProperties().get("address");
		assertEquals("#/components/schemas/Address", address.getRef());
	}

	@Test
	public void testParseFragment2() {

		// given
		ObjectNode node = getSchemaNode(FRAGMENT_2);

		// when
		final Schema schema = SchemaParser.parse(node);

		// then
		assertNotNull(schema);
		assertEquals("object", schema.getType());
		assertEquals(2, schema.getProperties().size());
		assertTrue(schema.getProperties().containsKey("lumens"));
		assertTrue(schema.getProperties().containsKey("sentAt"));

		final Schema lumens = schema.getProperties().get("lumens");
		assertEquals("integer", lumens.getType());
		assertEquals(0, lumens.getMinimum().intValue());
		assertEquals("Light intensity measured in lumens.", lumens.getDescription());

		final Schema sentAt = schema.getProperties().get("sentAt");
		assertEquals("#/components/schemas/sentAt", sentAt.getRef());
	}

	@Test
	public void testParseFragment3() {

		// given
		ObjectNode node = getSchemaNode(FRAGMENT_3);

		// when
		final Schema schema = SchemaParser.parse(node);

		// then
		assertNotNull(schema);
		assertEquals("object", schema.getType());
		assertEquals(2, schema.getProperties().size());
		assertTrue(schema.getProperties().containsKey("command"));
		assertTrue(schema.getProperties().containsKey("sentAt"));

		final Schema command = schema.getProperties().get("command");
		assertEquals("string", command.getType());
		assertEquals("Whether to turn on or off the light.", command.getDescription());

		assertNotNull(command.getEnum());
		final List<String> theEnum = command.getEnum();
		assertTrue(theEnum.contains("on"));
		assertTrue(theEnum.contains("off"));

		final Schema sentAt = schema.getProperties().get("sentAt");
		assertEquals("#/components/schemas/sentAt", sentAt.getRef());

	}

	public void testParseFragment4() {

		// given
		ObjectNode node = getSchemaNode(FRAGMENT_4);

		// when
		final Schema schema = SchemaParser.parse(node);

		// then
		assertNotNull(schema);
		assertEquals("object", schema.getType());
		assertEquals(2, schema.getProperties().size());
		assertTrue(schema.getProperties().containsKey("percentage"));
		assertTrue(schema.getProperties().containsKey("sentAt"));

		final Schema percentage = schema.getProperties().get("percentage");
		assertEquals("integer", percentage.getType());
		assertEquals("Percentage to which the light should be dimmed to.", percentage.getDescription());
		assertEquals(0, percentage.getMinimum().intValue());
		assertEquals(100, percentage.getMaximum().intValue());

		final Schema sentAt = schema.getProperties().get("sentAt");
		assertEquals("#/components/schemas/sentAt", sentAt.getRef());

	}

	@Test
	public void testParseFragment5() {

		// given
		ObjectNode node = getSchemaNode(FRAGMENT_5);

		// when
		final Schema schema = SchemaParser.parse(node);
		assertEquals("string", schema.getType());
		assertEquals("date-time", schema.getFormat());
		assertEquals("Date and time when the message was sent.", schema.getDescription());
		assertNull(schema.getProperties());

		// then
		assertNotNull(schema);

	}

	@Test
	public void testParseFullNotNulls() {
		// given
		ObjectNode node = getSchemaNode(FULL);

		// when
		final Schema schema = SchemaParser.parse(node);

		// then
		assertNotNull(schema);
		assertNotNull(schema.getTitle());
		assertNotNull(schema.getType());
		assertNotNull(schema.getRequiredProperties());
		assertNotNull(schema.getMultipleOf());
		assertNotNull(schema.getMaximum());
		assertNotNull(schema.isExclusiveMaximum());
		assertNotNull(schema.getMinimum());
		assertNotNull(schema.isExclusiveMinimum());
		assertNotNull(schema.getMaxLength());
		assertNotNull(schema.getMinLength());
		assertNotNull(schema.getPattern());
		assertNotNull(schema.getMaxItems());
		assertNotNull(schema.getMinItems());
		assertNotNull(schema.getUniqueItems());
		assertNotNull(schema.getMaxProperties());
		assertNotNull(schema.getMinProperties());
		assertNotNull(schema.getEnum());
		assertNotNull(schema.getConst());
		assertNotNull(schema.getReadOnly());
		assertNotNull(schema.getWriteOnly());
		assertNotNull(schema.getProperties());
		assertNotNull(schema.getAdditionalProperties());
		assertNotNull(schema.getAdditionalItems());
		assertNotNull(schema.getAllOf());
		assertNotNull(schema.getOneOf());
		assertNotNull(schema.getAnyOf());
		assertNotNull(schema.getNot());
		assertNotNull(schema.getExtensions());
		assertNotNull(schema.getExternalDocs());
		assertNotNull(schema.getDiscriminator());
		assertNotNull(schema.isDeprecated());

		// not yet implemented
//		assertNotNull(schema.getContains());
//		assertNotNull(schema.getItems());
//		assertNotNull(schema.getExamples());
//		assertNotNull(schema.getPatternProperties());
//		assertNotNull(schema.getPropertyNames());
	}
}
