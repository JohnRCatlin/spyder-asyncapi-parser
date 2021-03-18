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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.Info;

public class InfoParserTest {

  private static final String Description = "description...value";
  private static final String Name = "name...value";
  private static final String rawModel;
  private static final String TermsOfService = "terms of service...value";
  private static final String Title = "title...value";
  private static final String Url = "url...value";
  private static final String Version = "version...value";
  private static final String xBar = "x..bar..value";
  private static final String xFoo = "x..foo..value";

  static {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  'title':'" + Title + "',\n");
    sb.append("  'version':'" + Version + "',\n");
    sb.append("  'description':'" + Description + "',\n");
    sb.append("  'termsOfService':'" + TermsOfService + "',\n");
    sb.append("  'contact': {\n");
    sb.append("    'name':'" + Name + "',\n");
    sb.append("    'url':'" + Url + "',\n");
    sb.append("    'extensions': {\n");
    sb.append("       'x-foo':'" + xFoo + "',\n");
    sb.append("       'x-bar':'" + xBar + "'\n");
    sb.append("    }\n");
    sb.append("  },\n");
    sb.append("  'license': {\n");
    sb.append("    'name':'" + Name + "',\n");
    sb.append("    'url':'" + Url + "',\n");
    sb.append("    'extensions': {\n");
    sb.append("       'x-foo':'" + xFoo + "',\n");
    sb.append("       'x-bar':'" + xBar + "'\n");
    sb.append("    }\n");
    sb.append("  }\n");
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
    Info parsed = InfoParser.parse((ObjectNode) rootNode);

    // then
    assertNotNull(parsed);

    assertNotNull(parsed.getTitle());
    assertEquals(Title, parsed.getTitle());

    assertNotNull(parsed.getVersion());
    assertEquals(Version, parsed.getVersion());

    assertNotNull(parsed.getDescription());
    assertEquals(Description, parsed.getDescription());

    assertNotNull(parsed.getTermsOfService());
    assertEquals(TermsOfService, parsed.getTermsOfService());

    assertNotNull(parsed.getContact());
    assertEquals(Name, parsed.getContact().getName());
    assertEquals(Url, parsed.getContact().getUrl());

    assertNotNull(parsed.getContact().getExtensions());
    assertTrue(parsed.getContact().getExtensions().containsKey("x-foo"));
    assertTrue(parsed.getContact().getExtensions().containsKey("x-bar"));

    assertNotNull(parsed.getLicense());
    assertEquals(Name, parsed.getLicense().getName());
    assertEquals(Url, parsed.getLicense().getUrl());

    assertNotNull(parsed.getLicense().getExtensions());
    assertTrue(parsed.getLicense().getExtensions().containsKey("x-foo"));
    assertTrue(parsed.getLicense().getExtensions().containsKey("x-bar"));
  }

  @After
  public void tearDown() throws Exception {
  }

}
