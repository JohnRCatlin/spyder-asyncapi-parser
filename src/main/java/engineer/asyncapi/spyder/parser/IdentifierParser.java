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
import engineer.asyncapi.spyder.model.Identifier;
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
final class IdentifierParser extends AsyncAPICommonObjectParser {

  static final Identifier parse(final ObjectNode node) {
    if (node == null) {
      return null;
    }
    final IdentifierImpl.Builder builder = new IdentifierImpl.Builder();
    builder.id(parseId(node));
    return builder.build();
  }

  static final String parseId(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.ID.value, node);
  }

  private IdentifierParser() {
    /* this static utility should not be instantiated */
  }
}