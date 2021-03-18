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
import engineer.asyncapi.spyder.model.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ServerParserTest {

  private static final String Default = "443";
  private static final String Description = "description..value";
  private static final String Enum = "['80','443']";
  private static final String Protocol = "rotocol..value";
  private static final String ProtocolVersion = "protocolVersion..value";
  private static final String rawModel;
  private static final String Url = "url..value";
  private static final String xBar = "x..bar..value";
  private static final String xFoo = "x..foo..value";

  // given
  static {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  'url':'" + Url + "',\n");
    sb.append("  'protocol':'" + Protocol + "',\n");
    sb.append("  'protocolVersion':'" + ProtocolVersion + "',\n");
    sb.append("  'description':'" + Description + "',\n");
    sb.append("  'variables': {\n");
    sb.append("    'port': {\n");
    sb.append("      'enum':" + Enum + ",\n");
    sb.append("      'default':'" + Default + "',\n");
    sb.append("      'extensions': {\n");
    sb.append("         'x-foo':'" + xFoo + "',\n");
    sb.append("         'x-bar':'" + xBar + "'\n");
    sb.append("      }\n");
    sb.append("    }\n");
    sb.append("  },\n");
    sb.append("  'extensions': {\n");
    sb.append("     'x-foo':'" + xFoo + "',\n");
    sb.append("     'x-bar':'" + xBar + "'\n");
    sb.append("  }\n");
    sb.append("}");
    rawModel = sb.toString();
  }

  // TODO: variables, security, bindings,

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
    Server parsed = ServerParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);

    assertEquals(Url, parsed.getUrl());
    assertEquals(Protocol, parsed.getProtocol());
    assertEquals(ProtocolVersion, parsed.getProtocolVersion());
    assertEquals(Description, parsed.getDescription());

    assertNotNull(parsed.getExtensions());
    assertTrue(parsed.getExtensions().containsKey("x-foo"));
    assertTrue(parsed.getExtensions().containsKey("x-bar"));

    assertNotNull(parsed.getVariables());
    assertTrue(parsed.getVariables().containsKey("port"));

  }

  @After
  public void tearDown() throws Exception {
  }
}
