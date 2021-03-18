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
import engineer.asyncapi.spyder.model.bindings.AMQP091MessageBinding020;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AMQP091MessageBindingParserTest {

  private static final String bindingVersion = "0.2.0";
  private static final String contentEncoding = "application/json";
  private static final String messageType = "user.signup";
  private static final String rawModel;

  // given
  static {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  contentEncoding: " + contentEncoding + ",\n");
    sb.append("  messageType: " + messageType + ",\n");
    sb.append("  bindingVersion: " + bindingVersion + ",\n");
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
    AMQP091MessageBinding020 parsed = (AMQP091MessageBinding020) AMQP091MessageBindingParser
        .parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertEquals(contentEncoding, parsed.getContentEncoding());
    assertEquals(messageType, parsed.getMessageType());
    assertEquals(bindingVersion, parsed.getBindingVersion());
  }

  @After
  public void tearDown() throws Exception {
  }

}
