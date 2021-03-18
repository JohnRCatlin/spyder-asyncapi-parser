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
import engineer.asyncapi.spyder.model.bindings.AMQP091OperationBinding020;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AMQP091OperationBindingParserTest {

  private static final String ack = "false";
  private static final String bcc = "external.audit";
  private static final String bindingVersion = "0.2.0";
  private static final String cc = "user.logs";
  private static final String deliveryMode = "2";
  private static final String expiration = "100000";
  private static final String mandatory = "false";
  private static final String priority = "10";
  private static final String rawModel;
  private static final String replyTo = "user.signedup";
  private static final String timestamp = "true";
  private static final String userId = "guest";

  // given
  static {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  expiration: " + expiration + ",\n");
    sb.append("  userId: " + userId + ",\n");
    sb.append("  cc: [" + cc + "],\n");
    sb.append("  priority: " + priority + ",\n");
    sb.append("  deliveryMode: " + deliveryMode + ",\n");
    sb.append("  mandatory: " + mandatory + ",\n");
    sb.append("  bcc: [" + bcc + "],\n");
    sb.append("  replyTo: " + replyTo + ",\n");
    sb.append("  timestamp: " + timestamp + ",\n");
    sb.append("  ack: " + ack + ",\n");
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
    AMQP091OperationBinding020 parsed = (AMQP091OperationBinding020) AMQP091OperationBindingParser
        .parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);
    assertEquals(Long.parseLong(expiration), parsed.getExpiration());
    assertEquals(userId, parsed.getUserId());
    assertTrue(parsed.getCc().contains(cc));
    assertTrue(parsed.getBcc().contains(bcc));
    assertEquals(Integer.parseInt(deliveryMode), parsed.getDeliveryMode());
    assertEquals(Integer.parseInt(priority), parsed.getPriority());
    assertEquals(Boolean.parseBoolean(mandatory), parsed.isMandatory());
    assertEquals(replyTo, parsed.getReplyTo());
    assertEquals(Boolean.parseBoolean(timestamp), parsed.isTimestamp());
    assertEquals(Boolean.parseBoolean(ack), parsed.isAck());
    assertEquals(bindingVersion, parsed.getBindingVersion());
  }

  @After
  public void tearDown() throws Exception {
  }

}
