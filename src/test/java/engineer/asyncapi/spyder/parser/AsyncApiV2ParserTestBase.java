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

import engineer.asyncapi.spyder.model.MessageTrait;
import engineer.asyncapi.spyder.model.OperationTrait;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.commons.io.IOUtils;

/*
 * 
 */
public class AsyncApiV2ParserTestBase {

  /*
   * 
   */
  protected final String currentWorkingDirectory() {
    return new File("").getAbsolutePath();
  }

  /*
   * 
   */
  protected boolean messageTraitListContainsRef(final List<MessageTrait> list, final String value) {

    for (MessageTrait trait : list) {
      if (trait.getRef().equals(value)) {
        return true;
      }
    }

    return false;
  }

  /*
   * 
   */
  protected boolean operationTraitListContainsRef(final List<OperationTrait> list,
      final String value) {

    for (OperationTrait trait : list) {
      if (trait.getRef().equals(value)) {
        return true;
      }
    }

    return false;
  }

  /*
   * 
   */
  protected final String rawModelFromFile(final String modelSourceLocation) throws Exception {

    String modelRawString = null;
    File file = new File(modelSourceLocation);
    try (InputStream modelInputStream = new FileInputStream(file)) {
      modelRawString = IOUtils.toString(modelInputStream, Charset.defaultCharset()).trim();
    } catch (Exception e) {
      throw e;
    }

    return modelRawString;
  }
}
