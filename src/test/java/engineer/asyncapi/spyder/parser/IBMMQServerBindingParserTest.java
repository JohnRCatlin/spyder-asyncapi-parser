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

import engineer.asyncapi.spyder.model.bindings.IBMMQServerBinding010;

public class IBMMQServerBindingParserTest {

  private static final String rawModel;

  // given
  static {
    StringBuilder sb = new StringBuilder();
    sb.append("groupId: PRODCLSTR1\n");
    sb.append("cipherSpec: ANY_TLS12_OR_HIGHER\n");
    sb.append("ccdtQueueManagerName: qm1\n");
    sb.append("multiEndpointServer: false\n");
    sb.append("heartBeatInterval: 300\n");
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
    IBMMQServerBinding010 parsed = (IBMMQServerBinding010) IBMMQServerBindingParser
        .parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertEquals("ibmmq", parsed.getBindingType());
    assertEquals("0.1.0", parsed.getBindingVersion());

    assertEquals("PRODCLSTR1", parsed.getGroupId());
    assertEquals("ANY_TLS12_OR_HIGHER", parsed.getCipherSpec());
    assertEquals("qm1", parsed.getCcdtQueueManagerName());
    assertEquals(false, parsed.getMultiEndpointServer());
    assertEquals(Integer.valueOf(300), parsed.getHeartBeatInterval());

    assertEquals(1, parsed.getExtensions().size());
    assertEquals("foo", parsed.getExtensions().get("x-1"));
  }

  @After
  public void tearDown() throws Exception {
  }

}
