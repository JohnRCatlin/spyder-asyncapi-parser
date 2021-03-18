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

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.Tag;

public class TagsParserTest {

  private static final String Description = "description...value";
  private static final String Name = "name...value";
  private static final String rawModel;
  private static final String xBar = "x..bar..value";
  private static final String xFoo = "x..foo..value";

  // given
  static {
    StringBuilder sb = new StringBuilder();
    sb.append("{'tags': [{\n");
    sb.append("  'name':'" + Name + "',\n");
    sb.append("  'description':'" + Description + "',\n");
    sb.append("  'extensions': {\n");
    sb.append("     'x-foo':'" + xFoo + "',\n");
    sb.append("     'x-bar':'" + xBar + "'\n");
    sb.append("  }\n");
    sb.append("}]\n");
    sb.append("}");
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
    final ArrayNode array = NodeClipper.arrayNodeFrom("tags", (ObjectNode) rootNode);

    List<Tag> parsed = TagsParser.parse(array);

    // then
    assertNotNull(parsed);
    assertEquals(1, parsed.size());
    assertEquals(Name, parsed.get(0).getName());
    assertEquals(Description, parsed.get(0).getDescription());
  }

  @After
  public void tearDown() throws Exception {
  }

}
