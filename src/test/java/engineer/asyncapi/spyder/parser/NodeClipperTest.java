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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NodeClipperTest extends AsyncApiV2ParserTestBase {

  private static final String MODEL_SOURCE = "/src/test/resources/fragments/info.full.yml";
  private static String rawSubjectModel;
  private final ObjectMapper mapper = ObjectMapperFactory.forYaml();
  private JsonNode rootNode = null;

  @Test
  public void objectValueClip() throws Exception {

    // when
    ObjectNode clippedValue = NodeClipper.objectNodeFrom("info", (ObjectNode) rootNode);

    // then
    assertNotNull(clippedValue);
    assertTrue(clippedValue.isContainerNode());
    assertTrue(clippedValue.isObject());
  }

  @Before
  public void setUp() throws Exception {
    // given
    rawSubjectModel = rawModelFromFile(currentWorkingDirectory() + MODEL_SOURCE);
    rootNode = mapper.readTree(rawSubjectModel);
  }

  @Test
  public void stringValueClip() throws Exception {

    // when
    String clippedValue = NodeClipper.stringFrom("asyncapi", (ObjectNode) rootNode);

    // then
    assertNotNull(clippedValue);
    assertEquals("2.0.0", clippedValue);
  }

  @Test
  public void stringValueFromObject() throws Exception {

    // when
    ObjectNode clippedObject = NodeClipper.objectNodeFrom("info", (ObjectNode) rootNode);
    String clippedValue = NodeClipper.stringFrom("title", clippedObject);

    // then
    assertNotNull(clippedValue);
    assertEquals("example api", clippedValue);
  }

  @After
  public void tearDown() throws Exception {
  }

}
