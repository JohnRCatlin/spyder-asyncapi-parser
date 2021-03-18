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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engineer.asyncapi.spyder.model.bindings.HTTPMessageBinding010;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HTTPMessageBindingParserTest {

  private static final String rawModel;

  // given
  static {
    StringBuilder sb = new StringBuilder();
    sb.append("headers:\n");
    sb.append("  type: object\n");
    sb.append("  properties:\n");
    sb.append("    Content-Type:\n");
    sb.append("      type: string\n");
    sb.append("      enum: ['application/json']\n");
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
    HTTPMessageBinding010 parsed = (HTTPMessageBinding010) HTTPMessageBindingParser
        .parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertEquals("http", parsed.getBindingType());
    assertNotNull(parsed.getHeaders());
    assertEquals("object", parsed.getHeaders().getType());
    assertEquals(1, parsed.getHeaders().getProperties().size());
    assertNotNull(parsed.getHeaders().getProperties().get("Content-Type"));
    assertNotNull(parsed.getHeaders().getProperties().get("Content-Type"));
    assertEquals("string", parsed.getHeaders().getProperties().get("Content-Type").getType());
    assertTrue("string", parsed.getHeaders().getProperties().get("Content-Type").getEnum()
        .contains("application/json"));
    assertEquals(1, parsed.getExtensions().size());
    assertEquals("foo", parsed.getExtensions().get("x-1"));
    assertEquals("0.1.0", parsed.getBindingVersion());
  }

  @After
  public void tearDown() throws Exception {
  }

}
