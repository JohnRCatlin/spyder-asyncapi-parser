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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;

/**
 * 
 * @author johncatlin
 *
 */
final class FileReader {

  protected final String currentWorkingDirectory() {
    return new File("").getAbsolutePath();
  }

  public final String fromLocation(final String fileLocation) {
    String modelRawString = null;
    File file = new File(fileLocation);
    try (InputStream modelInputStream = new FileInputStream(file)) {
      modelRawString = IOUtils.toString(modelInputStream, Charset.defaultCharset()).trim();
    } catch (Exception e) {
      return null;
    }
    return modelRawString;
  }
}
