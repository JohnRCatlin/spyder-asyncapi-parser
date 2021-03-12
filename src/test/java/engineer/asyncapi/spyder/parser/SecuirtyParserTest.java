package engineer.asyncapi.spyder.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import engineer.asyncapi.spyder.model.Security;

public class SecuirtyParserTest {

	private static final String rawModel;

	// given
	static {
		StringBuilder sb = new StringBuilder();
		sb.append("security:\n");
		sb.append("  - apiKey: []\n");
		sb.append("  - supportedOauthFlows:\n");
		sb.append("    - streetlights:on\n");
		sb.append("    - streetlights:off\n");
		sb.append("    - streetlights:dim\n");
		sb.append("  - openIdConnectWellKnown: []\n");
		rawModel = sb.toString();
	}

	private ObjectMapper mapper = null;

	@Before
	public void setUp() throws Exception {
		// System.out.println(rawModel);
		mapper = ObjectMapperFactory.forYaml();
	}

	@Test
	public void test1() throws Exception {
		// when
		final JsonNode rootNode = mapper.readTree(rawModel);
		final ArrayNode securityNode = (ArrayNode) rootNode.get("security");

		// then
		Security parsed = SecurityParser.parse(securityNode);
		assertNotNull(parsed);
		assertEquals(3, parsed.size());
		assertTrue(parsed.containsRequirement("apiKey"));
		assertTrue(parsed.containsRequirement("supportedOauthFlows"));
		assertTrue(parsed.containsRequirement("openIdConnectWellKnown"));
		assertFalse(parsed.containsRequirement("randomDonkey"));
	}

}