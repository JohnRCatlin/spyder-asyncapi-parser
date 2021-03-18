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
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExtensionsParserTest {

  private static final String xBar = "x..bar..value";
  private static final String xFoo = "x..foo..value";

  private ObjectMapper mapper = null;

  // given
  private final String rawModel = "{" + "\n" + "'x-foo':'" + xFoo + "'" + ",\n" + "'x-bar':'" + xBar
      + "'" + "\n" + "}";
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
    Map<?, ?> parsed = ExtensionsParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertTrue(parsed.containsKey("x-foo"));
    assertTrue(parsed.containsKey("x-bar"));
    assertEquals(xFoo, parsed.get("x-foo"));
    assertEquals(xBar, parsed.get("x-bar"));
  }

  @After
  public void tearDown() throws Exception {
  }

}
