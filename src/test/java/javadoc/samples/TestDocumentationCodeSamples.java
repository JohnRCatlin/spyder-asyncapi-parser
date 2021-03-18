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

package javadoc.samples;

import java.io.File;

import org.junit.Test;

import engineer.asyncapi.spyder.model.AsyncAPI;
import engineer.asyncapi.spyder.parser.AsyncAPIParser;
import engineer.asyncapi.spyder.parser.AsyncAPIParserFactory;
import engineer.asyncapi.spyder.parser.SupportedAsyncAPIVersions;

public class TestDocumentationCodeSamples {

  @Test
  public void fromUrl() {

    final String source = "https://raw.githubusercontent.com/JohnRCatlin/asyncapi-recipes/main/src/fabrics/fabrics.amqp.yml";

    final AsyncAPIParser parser = AsyncAPIParserFactory.create(SupportedAsyncAPIVersions.V2_0_0);
    final AsyncAPI root = parser.parseFromUrl(source);

    assert root.getAsyncapi().equals(SupportedAsyncAPIVersions.V2_0_0.value);
  }

  @Test
  public void fromString() {

    final StringBuilder sb = new StringBuilder();
    sb.append("asyncapi: '2.0.0'\n");
    sb.append("info:\n");
    sb.append("  title: Streetlights API\n");
    sb.append("  version: '1.0.0'\n");
    sb.append("  description: |\n");
    sb.append("    The Smartylighting Streetlights API...\n");
    sb.append("  license:\n");
    sb.append("    name: Apache 2.0\n");
    sb.append("    url: https://www.apache.org/licenses/LICENSE-2.0\n");
    final String source = sb.toString();

    final AsyncAPIParser parser = AsyncAPIParserFactory.create(SupportedAsyncAPIVersions.V2_0_0);
    final AsyncAPI root = parser.parseFromString(source);

    assert root.getAsyncapi().equals(SupportedAsyncAPIVersions.V2_0_0.value);
    assert root.getInfo().getTitle().equals("Streetlights API");
    assert root.getInfo().getLicense().getName().equals("Apache 2.0");
  }

  @Test
  public void fromFile() {

    final String source = new File("").getAbsolutePath() + "/src/test/resources/streetlights2.yml";
    final AsyncAPIParser parser = AsyncAPIParserFactory.create(SupportedAsyncAPIVersions.V2_0_0);
    final AsyncAPI root = parser.parseFromFile(source);

    assert root.getAsyncapi().equals(SupportedAsyncAPIVersions.V2_0_0.value);
  }

}
