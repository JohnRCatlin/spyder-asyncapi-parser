package engineer.asyncapi.spyder.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engineer.asyncapi.spyder.model.SecurityScheme;
import engineer.asyncapi.spyder.model.security.OAuthFlow;
import engineer.asyncapi.spyder.model.security.OAuthFlows;
import engineer.asyncapi.spyder.model.security.SecurityType;
import org.junit.Before;
import org.junit.Test;

public class SecuirtySchemeParserTest {

  private static final String rawModelUserPassword;
  private static final String rawModelApiKey;
  private static final String rawModelX509;
  private static final String rawModelSymetricEncryption;
  private static final String rawModelHttp;
  private static final String rawModelHttpApiKey;
  private static final String rawModelJwtBearder;
  private static final String rawModelOpenIdConnect;
  private static final String rawModelOauth2;
  private static final String rawModelRef;

  // given
  static {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  type: userPassword\n");
    sb.append("}");
    rawModelUserPassword = sb.toString();
  }

  static {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  'type': 'apiKey',\n");
    sb.append("  'in': 'user'\n");
    sb.append("}");
    rawModelApiKey = sb.toString();
  }

  static {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  'type': 'X509'\n");
    sb.append("}");
    rawModelX509 = sb.toString();
  }

  static {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  'type': 'symmetricEncryption'\n");
    sb.append("}");
    rawModelSymetricEncryption = sb.toString();
  }

  static {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  'type': 'http',\n");
    sb.append("  'scheme': 'basic'\n");
    sb.append("}");
    rawModelHttp = sb.toString();
  }

  static {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  'type': 'httpApiKey',\n");
    sb.append("  'name': 'api_key',\n");
    sb.append("  'in': 'header'\n");
    sb.append("}");
    rawModelHttpApiKey = sb.toString();
  }

  static {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  'type': 'http',\n");
    sb.append("  'scheme': 'bearer',\n");
    sb.append("  'bearerFormat': 'JWT'\n");
    sb.append("}");
    rawModelJwtBearder = sb.toString();
  }

  static {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  'type': 'oauth2',\n");
    sb.append("  'openIdConnectUrl': 'https://example.com'\n");
    sb.append("}");
    rawModelOpenIdConnect = sb.toString();
  }

  static {
    StringBuilder sb = new StringBuilder();
    sb.append("type: oauth2\n");
    sb.append("flows:\n");
    sb.append("  implicit:\n");
    sb.append("    authorizationUrl: https://example.com/authorization/a\n");
    sb.append("    tokenUrl: https://example.com/token/b\n");
    sb.append("    refreshUrl: https://example.com/refresh/c\n");
    sb.append("    scopes:\n");
    sb.append("      write:pets: write\n");
    sb.append("      read:pets: read\n");
    sb.append("  authorizationCode:\n");
    sb.append("    authorizationUrl: https://example.com/authorization/d\n");
    sb.append("    tokenUrl: https://example.com/token/e\n");
    sb.append("    refreshUrl: https://example.com/refresh/f\n");
    sb.append("    scopes:\n");
    sb.append("      write:pets: write\n");
    sb.append("      read:pets: read\n");
    sb.append("    extensions:\n");
    sb.append("      x-1: implicit\n");
    sb.append("  clientCredentials:\n");
    sb.append("    authorizationUrl: https://example.com/authorization/g\n");
    sb.append("    tokenUrl: https://example.com/token/h\n");
    sb.append("    refreshUrl: https://example.com/refresh/i\n");
    sb.append("    scopes:\n");
    sb.append("      write:pets: write\n");
    sb.append("      read:pets: read\n");
    sb.append("  password:\n");
    sb.append("    authorizationUrl: https://example.com/authorization/j\n");
    sb.append("    tokenUrl: https://example.com/token/k\n");
    sb.append("    refreshUrl: https://example.com/refresh/l\n");
    sb.append("    scopes:\n");
    sb.append("      write:pets: write\n");
    sb.append("      read:pets: read\n");
    sb.append("  extensions:\n");
    sb.append("    x-1: foo\n");
    sb.append("    x-2: bar\n");
    sb.append("openIdConnectUrl: https://example.com\n");
    rawModelOauth2 = sb.toString();
  }

  static {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  '$ref': 'https://example.com'\n");
    sb.append("}");
    rawModelRef = sb.toString();
  }

  private ObjectMapper mapper = null;

  @Before
  public void setUp() throws Exception {
    // System.out.println(rawModel);
    mapper = ObjectMapperFactory.forYaml();
  }

  @Test
  public void testApiKey() throws Exception {
    // when
    final JsonNode rootNode = mapper.readTree(rawModelApiKey);

    // then
    SecurityScheme parsed = SecuritySchemeParser.parse((ObjectNode) rootNode);
    assertNotNull(parsed);
    assertEquals(SecurityType.APIKEY.value, parsed.getType());
    assertNull(parsed.getBearerFormat());
    assertNull(parsed.getDescription());
    assertNull(parsed.getExtensions());
    assertNull(parsed.getFlows());
    assertEquals("user", parsed.getIn());
    assertNull(parsed.getName());
    assertNull(parsed.getOpenIdConnectUrl());
    assertNull(parsed.getRef());
    assertNull(parsed.getScheme());
  }

  @Test
  public void testUserPassword() throws Exception {
    // when
    final JsonNode rootNode = mapper.readTree(rawModelUserPassword);

    // then
    SecurityScheme parsed = SecuritySchemeParser.parse((ObjectNode) rootNode);
    assertNotNull(parsed);
    assertEquals(SecurityType.USERPASSWORD.value, parsed.getType());
    assertNull(parsed.getBearerFormat());
    assertNull(parsed.getDescription());
    assertNull(parsed.getExtensions());
    assertNull(parsed.getFlows());
    assertNull(parsed.getIn());
    assertNull(parsed.getName());
    assertNull(parsed.getOpenIdConnectUrl());
    assertNull(parsed.getRef());
    assertNull(parsed.getScheme());
  }

  @Test
  public void testX509() throws Exception {
    // when
    final JsonNode rootNode = mapper.readTree(rawModelX509);

    // then
    SecurityScheme parsed = SecuritySchemeParser.parse((ObjectNode) rootNode);
    assertNotNull(parsed);
    assertEquals(SecurityType.X509.value, parsed.getType());
    assertNull(parsed.getBearerFormat());
    assertNull(parsed.getDescription());
    assertNull(parsed.getExtensions());
    assertNull(parsed.getFlows());
    assertNull(parsed.getIn());
    assertNull(parsed.getName());
    assertNull(parsed.getOpenIdConnectUrl());
    assertNull(parsed.getRef());
    assertNull(parsed.getScheme());
  }

  @Test
  public void testSymetricEncryption() throws Exception {
    // when
    final JsonNode rootNode = mapper.readTree(rawModelSymetricEncryption);

    // then
    SecurityScheme parsed = SecuritySchemeParser.parse((ObjectNode) rootNode);
    assertNotNull(parsed);
    assertEquals(SecurityType.SYMMETRICENCRYPTION.value, parsed.getType());
    assertNull(parsed.getBearerFormat());
    assertNull(parsed.getDescription());
    assertNull(parsed.getExtensions());
    assertNull(parsed.getFlows());
    assertNull(parsed.getIn());
    assertNull(parsed.getName());
    assertNull(parsed.getOpenIdConnectUrl());
    assertNull(parsed.getRef());
    assertNull(parsed.getScheme());
  }

  @Test
  public void testHttp() throws Exception {
    // when
    final JsonNode rootNode = mapper.readTree(rawModelHttp);

    // then
    SecurityScheme parsed = SecuritySchemeParser.parse((ObjectNode) rootNode);
    assertNotNull(parsed);
    assertEquals(SecurityType.HTTP.value, parsed.getType());
    assertNull(parsed.getBearerFormat());
    assertNull(parsed.getDescription());
    assertNull(parsed.getExtensions());
    assertNull(parsed.getFlows());
    assertNull(parsed.getIn());
    assertNull(parsed.getName());
    assertNull(parsed.getOpenIdConnectUrl());
    assertNull(parsed.getRef());
    assertEquals("basic", parsed.getScheme());
  }

  @Test
  public void testHttpApiKey() throws Exception {
    // when
    final JsonNode rootNode = mapper.readTree(rawModelHttpApiKey);

    // then
    SecurityScheme parsed = SecuritySchemeParser.parse((ObjectNode) rootNode);
    assertNotNull(parsed);
    assertEquals(SecurityType.HTTPAPIKEY.value, parsed.getType());
    assertNull(parsed.getBearerFormat());
    assertNull(parsed.getDescription());
    assertNull(parsed.getExtensions());
    assertNull(parsed.getFlows());
    assertEquals("header", parsed.getIn());
    assertEquals("api_key", parsed.getName());
    assertNull(parsed.getOpenIdConnectUrl());
    assertNull(parsed.getRef());
    assertNull(parsed.getScheme());
  }

  @Test
  public void testJwtBearer() throws Exception {
    // when
    final JsonNode rootNode = mapper.readTree(rawModelJwtBearder);

    // then
    SecurityScheme parsed = SecuritySchemeParser.parse((ObjectNode) rootNode);
    assertNotNull(parsed);
    assertEquals(SecurityType.HTTP.value, parsed.getType());
    assertEquals("JWT", parsed.getBearerFormat());
    assertNull(parsed.getDescription());
    assertNull(parsed.getExtensions());
    assertNull(parsed.getFlows());
    assertNull(parsed.getIn());
    assertNull(parsed.getName());
    assertNull(parsed.getOpenIdConnectUrl());
    assertNull(parsed.getRef());
    assertEquals("bearer", parsed.getScheme());
  }

  @Test
  public void testOpenIdConnect() throws Exception {
    // when
    final JsonNode rootNode = mapper.readTree(rawModelOpenIdConnect);

    // then
    SecurityScheme parsed = SecuritySchemeParser.parse((ObjectNode) rootNode);
    assertNotNull(parsed);
    assertEquals(SecurityType.OAUTH2.value, parsed.getType());
    assertNull(parsed.getBearerFormat());
    assertNull(parsed.getDescription());
    assertNull(parsed.getExtensions());
    assertNull(parsed.getFlows());
    assertNull(parsed.getIn());
    assertNull(parsed.getName());
    assertEquals("https://example.com", parsed.getOpenIdConnectUrl());
    assertNull(parsed.getRef());
    assertNull(parsed.getScheme());
  }

  @Test
  public void testOauth2() throws Exception {
    // when
    final JsonNode rootNode = mapper.readTree(rawModelOauth2);

    // then
    SecurityScheme parsed = SecuritySchemeParser.parse((ObjectNode) rootNode);
    assertNotNull(parsed);
    assertEquals(SecurityType.OAUTH2.value, parsed.getType());
    assertNull(parsed.getBearerFormat());
    assertNull(parsed.getDescription());
    assertNull(parsed.getExtensions());
    assertNotNull(parsed.getFlows());
    assertNull(parsed.getIn());
    assertNull(parsed.getName());
    assertEquals("https://example.com", parsed.getOpenIdConnectUrl());
    assertNull(parsed.getRef());
    assertNull(parsed.getScheme());

    OAuthFlows flows = parsed.getFlows();
    assertNotNull(flows.getAuthorizationCode());
    assertNotNull(flows.getClientCredentials());
    assertNotNull(flows.getExtensions());
    assertNotNull(flows.getImplicit());
    assertNotNull(flows.getPassword());

    OAuthFlow flow = flows.getImplicit();
    assertEquals("https://example.com/authorization/a", flow.getAuthorizationUrl());
    assertEquals("https://example.com/token/b", flow.getTokenUrl());
    assertEquals("https://example.com/refresh/c", flow.getRefreshUrl());
    assertEquals(2, flow.getScopes().size());
    assertNull(flow.getExtensions());

    flow = flows.getAuthorizationCode();
    assertEquals("https://example.com/authorization/d", flow.getAuthorizationUrl());
    assertEquals("https://example.com/token/e", flow.getTokenUrl());
    assertEquals("https://example.com/refresh/f", flow.getRefreshUrl());
    assertEquals(2, flow.getScopes().size());
    assertEquals(1, flow.getExtensions().size());

    flow = flows.getClientCredentials();
    assertEquals("https://example.com/authorization/g", flow.getAuthorizationUrl());
    assertEquals("https://example.com/token/h", flow.getTokenUrl());
    assertEquals("https://example.com/refresh/i", flow.getRefreshUrl());
    assertEquals(2, flow.getScopes().size());
    assertNull(flow.getExtensions());

    flow = flows.getPassword();
    assertEquals("https://example.com/authorization/j", flow.getAuthorizationUrl());
    assertEquals("https://example.com/token/k", flow.getTokenUrl());
    assertEquals("https://example.com/refresh/l", flow.getRefreshUrl());
    assertEquals(2, flow.getScopes().size());
    assertNull(flow.getExtensions());
  }

  @Test
  public void testRef() throws Exception {
    // when
    final JsonNode rootNode = mapper.readTree(rawModelRef);

    // then
    SecurityScheme parsed = SecuritySchemeParser.parse((ObjectNode) rootNode);
    assertNotNull(parsed);
    assertNull(parsed.getType());
    assertNull(parsed.getBearerFormat());
    assertNull(parsed.getDescription());
    assertNull(parsed.getExtensions());
    assertNull(parsed.getFlows());
    assertNull(parsed.getIn());
    assertNull(parsed.getName());
    assertNull(parsed.getOpenIdConnectUrl());
    assertEquals("https://example.com", parsed.getRef());
    assertNull(parsed.getScheme());
  }
}
