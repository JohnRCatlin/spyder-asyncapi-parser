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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engineer.asyncapi.spyder.model.SecurityRequirement;
import org.junit.Before;
import org.junit.Test;

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