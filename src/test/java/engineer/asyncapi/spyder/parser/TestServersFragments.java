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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import engineer.asyncapi.spyder.model.AsyncAPI;

public class TestServersFragments extends AsyncApiV2ParserTestBase {

  private static final String BROKEN = "/src/test/resources/fragments/servers.broken.yml";
  private static final String EMPTY = "/src/test/resources/fragments/servers.empty.yml";
  private static final String FULL = "/src/test/resources/fragments/servers.full.yml";

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testBroken() {
    // when
    final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
    final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + BROKEN);

    // then
    assertNotNull(api);
    assertEquals("2.0.0", api.getAsyncapi());
    assertNotNull(api.getServers());
    assertNotNull(api.getServers().get("dev"));
    assertNull(api.getServers().get("dev").getUrl());
    assertNull(api.getServers().get("dev").getDescription());
    assertNull(api.getServers().get("dev").getProtocol());
    assertNull(api.getServers().get("dev").getProtocolVersion());
    assertNull(api.getServers().get("dev").getExtensions());
    assertNull(api.getServers().get("dev").getSecurity());
    assertNull(api.getServers().get("dev").getBindings());

  }

  @Test
  public void testEmpty() {
    // when
    final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
    final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + EMPTY);

    // then
    assertNotNull(api);
    assertEquals("2.0.0", api.getAsyncapi());
    assertNull(api.getServers());
  }

  @Test
  public void testFull() {
    // when
    final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
    final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FULL);

    // then
    assertNotNull(api);
    assertEquals("2.0.0", api.getAsyncapi());

    assertNotNull(api.getServers());
    assertEquals(3, api.getServers().size());

    assertNotNull(api.getServers().get("dev"));
    assertEquals("dev.example.com", api.getServers().get("dev").getUrl());
    assertEquals("Dev server", api.getServers().get("dev").getDescription());
    assertEquals("kafka", api.getServers().get("dev").getProtocol());
    assertEquals("1.0.1", api.getServers().get("dev").getProtocolVersion());
    assertNotNull(api.getServers().get("dev").getExtensions());
    assertEquals(2, api.getServers().get("dev").getExtensions().size());
    assertEquals("x-a-value", api.getServers().get("dev").getExtensions().get("x-a"));
    assertEquals("x-b-value", api.getServers().get("dev").getExtensions().get("x-b"));
    assertNull(api.getServers().get("dev").getBindings());
    assertNotNull(api.getServers().get("dev").getSecurity());
    assertEquals(3, api.getServers().get("dev").getSecurity().size());

    assertTrue(
        api.getServers().get("dev").getSecurity().containsRequirement("supportedOauthFlows"));
    assertTrue(api.getServers().get("dev").getSecurity().containsRequirement("apiKey"));
    assertTrue(
        api.getServers().get("dev").getSecurity().containsRequirement("openIdConnectWellKnown"));

    assertNotNull(api.getServers().get("dev").getSecurity().get(0));
    assertNotNull(api.getServers().get("dev").getSecurity().get(1));

    assertNotNull(api.getServers().get("ci"));
    assertEquals("ci.example.com", api.getServers().get("ci").getUrl());
    assertEquals("CI server", api.getServers().get("ci").getDescription());
    assertEquals("kafka", api.getServers().get("ci").getProtocol());
    assertEquals("1.0.2", api.getServers().get("ci").getProtocolVersion());
    assertNotNull(api.getServers().get("ci").getExtensions());
    assertEquals(2, api.getServers().get("ci").getExtensions().size());
    assertEquals("x-c-value", api.getServers().get("ci").getExtensions().get("x-c"));
    assertEquals("x-d-value", api.getServers().get("ci").getExtensions().get("x-d"));
    assertNull(api.getServers().get("ci").getSecurity());
    assertNull(api.getServers().get("ci").getBindings());

    assertNotNull(api.getServers().get("prod"));
    assertEquals("prod.example.com", api.getServers().get("prod").getUrl());
    assertEquals("Prod server", api.getServers().get("prod").getDescription());
    assertEquals("kafka", api.getServers().get("prod").getProtocol());
    assertEquals("1.0.3", api.getServers().get("prod").getProtocolVersion());
    assertNotNull(api.getServers().get("prod").getExtensions());
    assertEquals(2, api.getServers().get("prod").getExtensions().size());
    assertEquals("x-e-value", api.getServers().get("prod").getExtensions().get("x-e"));
    assertEquals("x-f-value", api.getServers().get("prod").getExtensions().get("x-f"));
    assertNull(api.getServers().get("prod").getSecurity());
    assertNull(api.getServers().get("prod").getBindings());

  }
}
