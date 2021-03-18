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

import engineer.asyncapi.spyder.model.AsyncAPI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAsyncAPIV2Parser_fragment_asyncapi extends AsyncApiV2ParserTestBase {

  private static AsyncAPI api;
  private static String rawSubjectModel;

  private static final String SUBJECT_MODEL_SOURCE = "/src/test/resources/fragments/root.empty.yml";

  @Test
  public void fundamentalParseExpectation() {
    // then
    assertNotNull(api);
  }

  @Before
  public void setUp() throws Exception {
    // given
    rawSubjectModel = rawModelFromFile(currentWorkingDirectory() + SUBJECT_MODEL_SOURCE);
    // given
    AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
    // when
    api = parser.parseFromString(rawSubjectModel);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void versionExpectations() {
    // then
    assertEquals("2.0.0", api.getAsyncapi());
  }

}
