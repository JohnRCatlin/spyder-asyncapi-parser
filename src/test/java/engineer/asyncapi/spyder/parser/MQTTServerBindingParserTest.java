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
import engineer.asyncapi.spyder.model.bindings.MQTTServerBinding010;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MQTTServerBindingParserTest {

  private static final String rawModel;

  // given
  static {
    StringBuilder sb = new StringBuilder();
    sb.append("clientId: guest\n");
    sb.append("cleanSession: true\n");
    sb.append("lastWill:\n");
    sb.append("  topic: /last-wills\n");
    sb.append("  qos: 2\n");
    sb.append("  message: Guest gone offline.\n");
    sb.append("  retain: false\n");
    sb.append("keepAlive: 60\n");
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
    MQTTServerBinding010 parsed = (MQTTServerBinding010) MQTTServerBindingParser
        .parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);

    assertEquals("mqtt", parsed.getBindingType());

    assertEquals("guest", parsed.getClientId());
    assertEquals(true, parsed.getCleanSession());

    assertEquals("/last-wills", parsed.getLastWillTopic());
    assertEquals(Integer.valueOf(2), parsed.getLastWillQos());
    assertEquals("Guest gone offline.", parsed.getLastWillMessage());
    assertEquals(false, parsed.getLastWillRetain());

    assertEquals(Integer.valueOf(60), parsed.getKeepAlive());

    assertEquals(1, parsed.getExtensions().size());
    assertEquals("foo", parsed.getExtensions().get("x-1"));

    assertEquals("0.1.0", parsed.getBindingVersion());
  }

  @After
  public void tearDown() throws Exception {
  }

}
