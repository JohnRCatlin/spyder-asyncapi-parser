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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import engineer.asyncapi.spyder.model.Security;
import org.junit.Before;
import org.junit.Test;

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