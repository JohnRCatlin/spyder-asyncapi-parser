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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import engineer.asyncapi.spyder.model.AsyncAPI;
import engineer.asyncapi.spyder.model.Channel;
import engineer.asyncapi.spyder.model.Message;
import engineer.asyncapi.spyder.model.MessageTrait;
import engineer.asyncapi.spyder.model.OperationTrait;
import engineer.asyncapi.spyder.model.Parameter;
import engineer.asyncapi.spyder.model.Schema;
import engineer.asyncapi.spyder.model.Security;
import engineer.asyncapi.spyder.model.ServerVariable;
import engineer.asyncapi.spyder.model.bindings.KafkaOperationBinding010;
import engineer.asyncapi.spyder.model.security.OAuthFlow;
import engineer.asyncapi.spyder.model.security.SecurityType;
import java.math.BigDecimal;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAsyncAPIV2Parser_streetlights2 extends AsyncApiV2ParserTestBase {

  private static AsyncAPI api;
  private static String rawSubjectModel;
  private static final String SUBJECT_MODEL_SOURCE = "/src/test/resources/streetlights2.yml";

  @Test
  public void channelsExpectations() {
    // then
    assertNotNull(api.getChannels());

    assertEquals(4, api.getChannels().size());
    assertTrue(api.getChannels()
        .containsKey("smartylighting/streetlights/1/0/event/{streetlightId}/lighting/measured"));
    assertTrue(api.getChannels()
        .containsKey("smartylighting/streetlights/1/0/action/{streetlightId}/turn/on"));
    assertTrue(api.getChannels()
        .containsKey("smartylighting/streetlights/1/0/action/{streetlightId}/turn/off"));
    assertTrue(api.getChannels()
        .containsKey("smartylighting/streetlights/1/0/action/{streetlightId}/dim"));

    /*
     * first channel
     */
    Channel channel1 = api.getChannels()
        .get("smartylighting/streetlights/1/0/event/{streetlightId}/lighting/measured");
    assertNotNull(channel1);
    assertTrue(
        channel1.getDescription().contains("The topic on which measured values may be produce"));
    assertNotNull(channel1.getParameters());
    assertTrue(channel1.getParameters().containsKey("streetlightId"));
    assertEquals("#/components/parameters/streetlightId",
        channel1.getParameters().get("streetlightId").getRef());

    assertNotNull(channel1.getPublish());
    assertEquals("receiveLightMeasurement", channel1.getPublish().getOperationId());
    assertNotNull(channel1.getPublish().getTraits());
    assertTrue(operationTraitListContainsRef(channel1.getPublish().getTraits(),
        "#/components/operationTraits/kafka"));
    assertNotNull(channel1.getPublish().getMessage());
    assertEquals("#/components/messages/lightMeasured",
        channel1.getPublish().getMessage().getRef());

    assertNull(channel1.getSubscribe());

    channel1 = null;

    /*
     * second channel
     */
    Channel channel2 = api.getChannels()
        .get("smartylighting/streetlights/1/0/action/{streetlightId}/turn/on");
    assertNotNull(channel2);
    assertNull(channel2.getDescription());
    assertNotNull(channel2.getParameters());
    assertTrue(channel2.getParameters().containsKey("streetlightId"));
    assertEquals("#/components/parameters/streetlightId",
        channel2.getParameters().get("streetlightId").getRef());

    assertNull(channel2.getPublish());

    assertNotNull(channel2.getSubscribe());
    assertEquals("turnOn", channel2.getSubscribe().getOperationId());
    assertNotNull(channel2.getSubscribe().getTraits());
    assertTrue(operationTraitListContainsRef(channel2.getSubscribe().getTraits(),
        "#/components/operationTraits/kafka"));
    assertNotNull(channel2.getSubscribe().getMessage());
    assertEquals("#/components/messages/turnOnOff", channel2.getSubscribe().getMessage().getRef());

    channel2 = null;

    /*
     * third channel
     */
    Channel channel3 = api.getChannels()
        .get("smartylighting/streetlights/1/0/action/{streetlightId}/turn/off");
    assertNotNull(channel3);
    assertNull(channel3.getDescription());
    assertNotNull(channel3.getParameters());
    assertTrue(channel3.getParameters().containsKey("streetlightId"));
    assertEquals("#/components/parameters/streetlightId",
        channel3.getParameters().get("streetlightId").getRef());

    assertNull(channel3.getPublish());

    assertNotNull(channel3.getSubscribe());
    assertEquals("turnOff", channel3.getSubscribe().getOperationId());
    assertNotNull(channel3.getSubscribe().getTraits());
    assertTrue(operationTraitListContainsRef(channel3.getSubscribe().getTraits(),
        "#/components/operationTraits/kafka"));
    assertNotNull(channel3.getSubscribe().getMessage());
    assertEquals("#/components/messages/turnOnOff", channel3.getSubscribe().getMessage().getRef());

    channel3 = null;

    /*
     * fourth channel
     */
    Channel channel4 = api.getChannels()
        .get("smartylighting/streetlights/1/0/action/{streetlightId}/dim");
    assertNotNull(channel4);
    assertNull(channel4.getDescription());
    assertNotNull(channel4.getParameters());
    assertTrue(channel4.getParameters().containsKey("streetlightId"));
    assertEquals("#/components/parameters/streetlightId",
        channel4.getParameters().get("streetlightId").getRef());

    assertNull(channel4.getPublish());

    assertNotNull(channel4.getSubscribe());
    assertEquals("dimLight", channel4.getSubscribe().getOperationId());
    assertNotNull(channel4.getSubscribe().getTraits());
    assertTrue(operationTraitListContainsRef(channel4.getSubscribe().getTraits(),
        "#/components/operationTraits/kafka"));
    assertNotNull(channel4.getSubscribe().getMessage());
    assertEquals("#/components/messages/dimLight", channel4.getSubscribe().getMessage().getRef());

    channel4 = null;
  }

  /**
   * Test expectations for the subject Components.
   */
  @Test
  public void componentsExpectations() {
    // then
    assertNotNull(api.getComponents());
    assertNotNull(api.getComponents().getMessages());
    assertNotNull(api.getComponents().getSchemas());
    assertNotNull(api.getComponents().getSecuritySchemes());
    assertNotNull(api.getComponents().getParameters());
    assertNotNull(api.getComponents().getMessageTraits());
    assertNotNull(api.getComponents().getOperationTraits());
  }

  @Test
  public void componentsMessagesExpectations() {
    // then
    assertFalse(api.getComponents().getMessages().isEmpty());

    Map<String, Message> messages = api.getComponents().getMessages();
    assertEquals(3, messages.size());
    assertTrue(messages.containsKey("lightMeasured"));
    assertTrue(messages.containsKey("turnOnOff"));
    assertTrue(messages.containsKey("dimLight"));

    Message message1 = messages.get("lightMeasured");
    assertEquals("lightMeasured", message1.getName());
    assertEquals("Light measured", message1.getTitle());
    assertTrue(message1.getSummary()
        .contains("Inform about environmental lighting conditions of a particular streetlight."));
    assertEquals("application/json", message1.getContentType());
    assertNotNull(message1.getTraits());

    assertTrue(messageTraitListContainsRef(message1.getTraits(),
        "#/components/messageTraits/commonHeaders"));

    assertNotNull(message1.getPayload());
    Schema payload1 = message1.getPayload();
    assertEquals("#/components/schemas/lightMeasuredPayload", payload1.getRef());

    message1 = null;

    Message message2 = messages.get("turnOnOff");
    assertEquals("turnOnOff", message2.getName());
    assertEquals("Turn on/off", message2.getTitle());
    assertTrue(message2.getSummary()
        .contains("Command a particular streetlight to turn the lights on or off."));
    assertNotNull(message2.getTraits());

    assertTrue(messageTraitListContainsRef(message2.getTraits(),
        "#/components/messageTraits/commonHeaders"));

    assertNotNull(message2.getPayload());
    Schema payload2 = message2.getPayload();
    assertEquals("#/components/schemas/turnOnOffPayload", payload2.getRef());

    message2 = null;

    Message message3 = messages.get("dimLight");
    assertEquals("dimLight", message3.getName());
    assertEquals("Dim light", message3.getTitle());
    assertTrue(
        message3.getSummary().contains("Command a particular streetlight to dim the lights."));
    assertNotNull(message3.getTraits());

    assertTrue(messageTraitListContainsRef(message3.getTraits(),
        "#/components/messageTraits/commonHeaders"));

    assertNotNull(message3.getPayload());

    assertNotNull(message3.getPayload());
    Schema payload3 = message3.getPayload();
    assertEquals("#/components/schemas/dimLightPayload", payload3.getRef());
  }

  @Test
  public void componentsMessageTriatsExpectations() {
    // then
    assertFalse(api.getComponents().getMessageTraits().isEmpty());
    assertTrue(api.getComponents().getMessageTraits().containsKey("commonHeaders"));

    MessageTrait trait = api.getComponents().getMessageTraits().get("commonHeaders");
    assertNotNull(trait.getHeaders());
    assertEquals("object", trait.getHeaders().getType());
    assertNotNull(trait.getHeaders().getProperties());

    assertTrue(trait.getHeaders().getProperties().containsKey("my-app-header"));
    assertNotNull(trait.getHeaders().getProperties().get("my-app-header"));
    assertEquals("integer", trait.getHeaders().getProperties().get("my-app-header").getType());
    assertEquals(new BigDecimal(0),
        trait.getHeaders().getProperties().get("my-app-header").getMinimum());
    assertEquals(new BigDecimal(100),
        trait.getHeaders().getProperties().get("my-app-header").getMaximum());
  }

  @Test
  public void componentsOperationTraitsExpectations() {
    // then
    assertFalse(api.getComponents().getOperationTraits().isEmpty());
    assertTrue(api.getComponents().getOperationTraits().containsKey("kafka"));

    OperationTrait triat = api.getComponents().getOperationTraits().get("kafka");
    assertNotNull(triat.getBindings());

    KafkaOperationBinding010 binding = (KafkaOperationBinding010) triat.getBindings();
    assertNotNull(binding.getClientId());
    assertTrue(binding.getClientId().getEnum().contains("my-app-id"));
  }

  @Test
  public void componentsParametersExpectations() {
    // then
    assertFalse(api.getComponents().getParameters().isEmpty());
    assertTrue(api.getComponents().getParameters().containsKey("streetlightId"));

    Parameter parameter = api.getComponents().getParameters().get("streetlightId");
    assertTrue(parameter.getDescription().contains("The ID of the streetlight."));
    assertNotNull(parameter.getSchema());
    assertEquals("string", parameter.getSchema().getType());
  }

  @Test
  public void componentsSchemasExpectations() {
    // then
    assertFalse(api.getComponents().getSchemas().isEmpty());
    Map<String, Schema> schemas = api.getComponents().getSchemas();
    assertEquals(4, schemas.size());

    assertTrue(schemas.containsKey("lightMeasuredPayload"));
    assertTrue(schemas.containsKey("turnOnOffPayload"));
    assertTrue(schemas.containsKey("dimLightPayload"));
    assertTrue(schemas.containsKey("sentAt"));

    // schema1
    Schema schema1 = schemas.get("lightMeasuredPayload");
    assertEquals("object", schema1.getType());
    assertNotNull(schema1.getProperties().get("sentAt"));
    assertEquals("#/components/schemas/sentAt", schema1.getProperties().get("sentAt").getRef());
    assertNotNull(schema1.getProperties().get("lumens"));
    assertEquals("integer", schema1.getProperties().get("lumens").getType());
    assertEquals(new BigDecimal(0), schema1.getProperties().get("lumens").getMinimum());
    assertTrue(schema1.getProperties().get("lumens").getDescription()
        .contains("Light intensity measured in lumens."));
    schema1 = null;

    // schema2
    Schema schema2 = schemas.get("turnOnOffPayload");
    assertEquals("object", schema2.getType());
    assertNotNull(schema2.getProperties().get("sentAt"));
    assertEquals("#/components/schemas/sentAt", schema2.getProperties().get("sentAt").getRef());
    assertNotNull(schema2.getProperties().get("command"));
    assertEquals("string", schema2.getProperties().get("command").getType());
    assertNotNull(schema2.getProperties().get("command").getEnum());
    assertEquals(2, schema2.getProperties().get("command").getEnum().size());
    assertTrue(schema2.getProperties().get("command").getEnum().contains("on"));
    assertTrue(schema2.getProperties().get("command").getEnum().contains("off"));
    assertTrue(schema2.getProperties().get("command").getDescription()
        .contains("Whether to turn on or off the light."));
    schema2 = null;

    // schema3
    Schema schema3 = schemas.get("dimLightPayload");
    assertEquals("object", schema3.getType());
    assertNotNull(schema3.getProperties());
    assertNotNull(schema3.getProperties().get("percentage"));
    assertEquals("integer", schema3.getProperties().get("percentage").getType());
    assertEquals(new BigDecimal(0), schema3.getProperties().get("percentage").getMinimum());
    assertEquals(new BigDecimal(100), schema3.getProperties().get("percentage").getMaximum());
    assertNotNull(schema3.getProperties().get("percentage").getDescription());
    assertTrue(schema3.getProperties().get("percentage").getDescription()
        .contains("Percentage to which the light should be dimmed to."));
    assertNotNull(schema3.getProperties().get("sentAt"));
    assertEquals("#/components/schemas/sentAt", schema3.getProperties().get("sentAt").getRef());
    schema3 = null;

    // schema4
    Schema schema4 = schemas.get("sentAt");
    assertEquals("string", schema4.getType());
    assertEquals("date-time", schema4.getFormat());
    assertTrue(schema4.getDescription().contains("Date and time when the message was sent."));
    schema4 = null;
  }

  @Test
  public void componentsSecuritySchemasExpectations() {
    // then
    assertFalse(api.getComponents().getSecuritySchemes().isEmpty());
    assertEquals(3, api.getComponents().getSecuritySchemes().size());
    assertTrue(api.getComponents().getSecuritySchemes().containsKey("apiKey"));
    assertTrue(api.getComponents().getSecuritySchemes().containsKey("supportedOauthFlows"));
    assertTrue(api.getComponents().getSecuritySchemes().containsKey("openIdConnectWellKnown"));

    assertEquals(SecurityType.APIKEY.value,
        api.getComponents().getSecuritySchemes().get("apiKey").getType());
    assertEquals("user", api.getComponents().getSecuritySchemes().get("apiKey").getIn());
    assertTrue(api.getComponents().getSecuritySchemes().get("apiKey").getDescription()
        .contains("Provide your API key as the user and leave the password empty."));

    assertEquals(SecurityType.OAUTH2.value,
        api.getComponents().getSecuritySchemes().get("supportedOauthFlows").getType());
    assertNotNull(api.getComponents().getSecuritySchemes().get("supportedOauthFlows").getFlows());

    OAuthFlow implicitFlow = api.getComponents().getSecuritySchemes().get("supportedOauthFlows")
        .getFlows().getImplicit();
    assertNotNull(implicitFlow);
    assertNotNull(implicitFlow.getAuthorizationUrl());
    assertNotNull(implicitFlow.getScopes());
    assertEquals(3, implicitFlow.getScopes().size());
    implicitFlow = null;

    OAuthFlow passwordFlow = api.getComponents().getSecuritySchemes().get("supportedOauthFlows")
        .getFlows().getPassword();
    assertNotNull(passwordFlow);
    assertNotNull(passwordFlow.getTokenUrl());
    assertNotNull(passwordFlow.getScopes());
    assertEquals(3, passwordFlow.getScopes().size());
    passwordFlow = null;

    OAuthFlow clientCredentialsFlow = api.getComponents().getSecuritySchemes()
        .get("supportedOauthFlows").getFlows().getClientCredentials();
    assertNotNull(clientCredentialsFlow);
    assertNotNull(clientCredentialsFlow.getTokenUrl());
    assertNotNull(clientCredentialsFlow.getScopes());
    assertEquals(3, clientCredentialsFlow.getScopes().size());
    clientCredentialsFlow = null;

    OAuthFlow autorizationCodeFlow = api.getComponents().getSecuritySchemes()
        .get("supportedOauthFlows").getFlows().getAuthorizationCode();
    assertNotNull(autorizationCodeFlow);
    assertNotNull(autorizationCodeFlow.getAuthorizationUrl());
    assertNotNull(autorizationCodeFlow.getScopes());
    assertEquals(3, autorizationCodeFlow.getScopes().size());
    autorizationCodeFlow = null;

    assertTrue(api.getComponents().getSecuritySchemes().get("supportedOauthFlows").getDescription()
        .contains("Flows to support OAuth 2.0"));
  }

  /**
   * Test expectations for the subject Default Content Type.
   */
  @Test
  public void defaultContentTypeExpectations() {
    // then
    assertEquals("application/json", api.getDefaultContentType());
  }

  /**
   * Test the fundamental ability to parse the subject.
   */
  @Test
  public void fundamentalParseExpectation() {
    // then
    assertNotNull(api);
  }

  /**
   * Test expectations for the subject Info.
   */
  @Test
  public void infoExpectations() {
    // then
    assertNotNull(api.getInfo());
    assertEquals("Streetlights API", api.getInfo().getTitle());
    assertEquals("1.0.0", api.getInfo().getVersion());
    assertTrue(api.getInfo().getDescription()
        .contains("The Smartylighting Streetlights API allows you to remotely"));
    assertEquals("Apache 2.0", api.getInfo().getLicense().getName());
    assertEquals("https://www.apache.org/licenses/LICENSE-2.0",
        api.getInfo().getLicense().getUrl());
  }

  /**
   * Test expectations for the subject Servers.
   */
  @Test
  public void serversExpectations() {
    // then
    assertNotNull(api.getServers());
    assertEquals(1, api.getServers().size());

    assertNotNull(api.getServers().get("production"));
    assertEquals("test.mosquitto.org:{port}", api.getServers().get("production").getUrl());
    assertEquals("mqtt", api.getServers().get("production").getProtocol());
    assertEquals("Test broker", api.getServers().get("production").getDescription());

    assertNotNull(api.getServers().get("production").getVariables());
    assertNotNull(api.getServers().get("production").getVariables().get("port"));

    ServerVariable port = api.getServers().get("production").getVariables().get("port");
    assertEquals("Secure connection (TLS) is available through port 8883.", port.getDescription());
    assertEquals("1883", port.getDefault());
    assertNotNull(port.getEnum());
    assertTrue(port.getEnum().contains("1883"));
    assertTrue(port.getEnum().contains("8883"));

    assertNotNull(api.getServers().get("production").getSecurity());
    Security security = api.getServers().get("production").getSecurity();

    assertTrue(security.containsRequirement("supportedOauthFlows"));
    assertTrue(security.containsRequirement("apiKey"));
    assertTrue(security.containsRequirement("openIdConnectWellKnown"));
  }

  @Before
  public void setUp() throws Exception {
    // given
    rawSubjectModel = rawModelFromFile(currentWorkingDirectory() + SUBJECT_MODEL_SOURCE);
    AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
    // when
    api = parser.parseFromString(rawSubjectModel);
  }

  @After
  public void tearDown() throws Exception {
  }

  /**
   * Test expectations for the subject Version.
   */
  @Test
  public void versionExpectations() {
    // then
    assertEquals("2.0.0", api.getAsyncapi());
  }
}
