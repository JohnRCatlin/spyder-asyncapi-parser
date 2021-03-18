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

/**
 * 
 * @author johncatlin
 *
 */
class RefUtility {

  static final String ensureSafeLocalReference(final String ref) {
    return (ref.contains(":") || ref.startsWith("#") || ref.startsWith("/")
        || ref.indexOf(".") <= 0) ? ref : "./" + ref;
  }

  static final boolean isRelative(final String ref) {
    return (ref != null && ref.indexOf('.') == -1 && ref.indexOf('/') == -1);
  }

  private RefUtility() {
    // don't instantiate utility.
  }
}
