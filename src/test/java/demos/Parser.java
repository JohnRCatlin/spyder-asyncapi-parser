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
package demos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import engineer.asyncapi.spyder.model.AsyncAPI;
import engineer.asyncapi.spyder.parser.AsyncAPIParser;
import engineer.asyncapi.spyder.parser.AsyncAPIParserFactory;
import engineer.asyncapi.spyder.parser.SupportedAsyncAPIVersions;

public class Parser {

  static final String SUBJECT_FILE = "/src/test/resources/streetlights2.yml";
  static final String SUBJECT_STRING;
  static final String SUBJECT_URL = "https://raw.githubusercontent.com/JohnRCatlin/asyncapi-recipes/main/src/fabrics/fabrics.amqp.yml";

  static {
    StringBuilder sb = new StringBuilder();
    sb.append("asyncapi: '2.0.0'\n");
    sb.append("info:\n");
    sb.append("  title: Streetlights API\n");
    sb.append("  version: '1.0.0'\n");
    sb.append("  description: |\n");
    sb.append("    The Smartylighting Streetlights API...\n");
    sb.append("  license:\n");
    sb.append("    name: Apache 2.0\n");
    sb.append("    url: https://www.apache.org/licenses/LICENSE-2.0\n");
    SUBJECT_STRING = sb.toString();
  }

  @Test
  public void parseFromAny1() {
    // when
    final AsyncAPIParser parser = AsyncAPIParserFactory.create(SupportedAsyncAPIVersions.V2_0_0);
    final AsyncAPI parsed = parser.parseFrom(SUBJECT_URL);

    // then
    assertNotNull(parsed);
    assertEquals(SupportedAsyncAPIVersions.V2_0_0.value, parsed.getAsyncapi());
  }

  @Test
  public void parseFromAny2() {
    // when
    final AsyncAPIParser parser = AsyncAPIParserFactory.create(SupportedAsyncAPIVersions.V2_0_0);
    final AsyncAPI parsed = parser.parseFrom((new File("").getAbsolutePath()) + SUBJECT_FILE);

    // then
    assertNotNull(parsed);
    assertEquals(SupportedAsyncAPIVersions.V2_0_0.value, parsed.getAsyncapi());
  }

  @Test
  public void parseFromAny3() {
    // when
    final AsyncAPIParser parser = AsyncAPIParserFactory.create(SupportedAsyncAPIVersions.V2_0_0);
    final AsyncAPI parsed = parser.parseFrom(SUBJECT_STRING);

    // then
    assertNotNull(parsed);
    assertEquals(SupportedAsyncAPIVersions.V2_0_0.value, parsed.getAsyncapi());
  }

  @Test
  public void parseFromFile() {
    // when
    final AsyncAPIParser parser = AsyncAPIParserFactory.create(SupportedAsyncAPIVersions.V2_0_0);
    final AsyncAPI parsed = parser.parseFromFile((new File("").getAbsolutePath()) + SUBJECT_FILE);

    // then
    assertNotNull(parsed);
    assertEquals(SupportedAsyncAPIVersions.V2_0_0.value, parsed.getAsyncapi());
  }

  @Test
  public void parseFromString() {
    // when
    final AsyncAPIParser parser = AsyncAPIParserFactory.create(SupportedAsyncAPIVersions.V2_0_0);
    final AsyncAPI parsed = parser.parseFromString(SUBJECT_STRING);

    // then
    assertNotNull(parsed);
    assertEquals(SupportedAsyncAPIVersions.V2_0_0.value, parsed.getAsyncapi());
  }

  @Test
  public void parseFromUrl() {
    // when
    final AsyncAPIParser parser = AsyncAPIParserFactory.create(SupportedAsyncAPIVersions.V2_0_0);
    final AsyncAPI parsed = parser.parseFromUrl(SUBJECT_URL);

    // then
    assertNotNull(parsed);
    assertEquals(SupportedAsyncAPIVersions.V2_0_0.value, parsed.getAsyncapi());
  }

}
