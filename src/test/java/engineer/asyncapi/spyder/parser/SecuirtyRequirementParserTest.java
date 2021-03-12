package engineer.asyncapi.spyder.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.SecurityRequirement;

public class SecuirtyRequirementParserTest {

	private static final String rawModel1;
	private static final String rawModel2;
	private static final String rawModel3;

	// given
	static {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("  use_pass: []\n");
		sb.append("}");
		rawModel1 = sb.toString();
	}

	// given
	static {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("  api_key: []\n");
		sb.append("}");
		rawModel2 = sb.toString();
	}

	// given
	static {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("  petstore_auth: [\n");
		sb.append("     write:pets,\n");
		sb.append("     read:pets,\n");
		sb.append("  ]\n");
		sb.append("}");
		rawModel3 = sb.toString();
	}

//  security:
//    - apiKey: []
//    - supportedOauthFlows:
//      - streetlights:on
//      - streetlights:off
//      - streetlights:dim
//    - openIdConnectWellKnown: []

	private ObjectMapper mapper = null;

	@Before
	public void setUp() throws Exception {
		// System.out.println(rawModel);
		mapper = ObjectMapperFactory.forYaml();
	}

	@Test
	public void test1() throws Exception {
		// when
		final JsonNode rootNode = mapper.readTree(rawModel1);

		// then
		SecurityRequirement parsed = SecurityRequirementParser.parse((ObjectNode) rootNode);
		assertNotNull(parsed);
		assertEquals(0, parsed.size());
		assertEquals("use_pass", parsed.getName());
	}

	@Test
	public void test2() throws Exception {
		// when
		final JsonNode rootNode = mapper.readTree(rawModel2);

		// then
		SecurityRequirement parsed = SecurityRequirementParser.parse((ObjectNode) rootNode);
		assertNotNull(parsed);
		assertEquals(0, parsed.size());
		assertEquals("api_key", parsed.getName());
	}

	@Test
	public void test3() throws Exception {
		// when
		final JsonNode rootNode = mapper.readTree(rawModel3);

		// then
		SecurityRequirement parsed = SecurityRequirementParser.parse((ObjectNode) rootNode);
		assertNotNull(parsed);
		assertEquals(2, parsed.size());
		assertEquals("petstore_auth", parsed.getName());
	}
}