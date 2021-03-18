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

import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.License;

/**
 * 
 * @author johncatlin
 *
 */
final class LicenseParser extends AsyncAPICommonObjectParser {

  static final License parse(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final LicenseImpl.Builder builder = new LicenseImpl.Builder();
    builder.name(parseName(node));
    builder.url(parseUrl(node));
    builder.extensions(parseExtensions(node));
    return builder.build();
  }

  private LicenseParser() {
    /* this static utility should not be instantiated */
  }
}