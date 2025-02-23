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
import com.fasterxml.jackson.databind.node.ObjectNode;
import engineer.asyncapi.spyder.model.bindings.HTTPOperationBinding010;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HTTPOperationBindingParserTest {

  private static final String rawModel;

  // given
  static {
    StringBuilder sb = new StringBuilder();
    sb.append("bindingVersion: '0.1.0'\n");
    sb.append("method: GET\n");
    sb.append("query:\n");
    sb.append("  additionalProperties: false\n");
    sb.append("  properties:\n");
    sb.append("    companyId:\n");
    sb.append("      description: The Id of the company.\n");
    sb.append("      minimum: 1\n");
    sb.append("      type: number\n");
    sb.append("  required:\n");
    sb.append("    - companyId\n");
    sb.append("  type: object\n");
    sb.append("type: request\n");
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
    HTTPOperationBinding010 parsed = (HTTPOperationBinding010) HTTPOperationBindingParser
        .parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertEquals("http", parsed.getBindingType());
    assertEquals("GET", parsed.getMethod());
    assertEquals("request", parsed.getChannelType());
    assertNotNull(parsed.getQuery());
    assertEquals("object", parsed.getQuery().getType());
    assertFalse(parsed.getQuery().getAdditionalProperties());
    assertEquals(1, parsed.getQuery().getProperties().size());
    assertTrue(parsed.getQuery().getProperties().containsKey("companyId"));
    assertEquals(new BigDecimal(1),
        parsed.getQuery().getProperties().get("companyId").getMinimum());
    assertEquals("number", parsed.getQuery().getProperties().get("companyId").getType());
    assertTrue(parsed.getQuery().getRequiredProperties().contains("companyId"));
    assertEquals(1, parsed.getExtensions().size());
    assertEquals("foo", parsed.getExtensions().get("x-1"));
    assertEquals("0.1.0", parsed.getBindingVersion());
  }

  @After
  public void tearDown() throws Exception {
  }

}
