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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.bindings.IBMMQMessageBinding010;

public class IBMMQMessageBindingParserTest {

  private static final String rawModel;

  // given
  static {
    StringBuilder sb = new StringBuilder();
    sb.append("type: jms\n");
    sb.append("description: JMS stream\n");
    sb.append("expiry: 0\n");
    sb.append("headers: MQFMT_DEAD_LETTER_HEADER, MQFMT_REF_MSG_HEADER\n");
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
    IBMMQMessageBinding010 parsed = (IBMMQMessageBinding010) IBMMQMessageBindingParser
        .parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertEquals("ibmmq", parsed.getBindingType());
    assertEquals("0.1.0", parsed.getBindingVersion());

    assertEquals("jms", parsed.getType());
    assertEquals("JMS stream", parsed.getDescription());
    assertEquals(Integer.valueOf(0), parsed.getExpiry());
    assertEquals("MQFMT_DEAD_LETTER_HEADER, MQFMT_REF_MSG_HEADER", parsed.getHeaders());

    assertEquals(1, parsed.getExtensions().size());
    assertEquals("foo", parsed.getExtensions().get("x-1"));
  }

  @After
  public void tearDown() throws Exception {
  }

}
