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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engineer.asyncapi.spyder.model.Schema;
import engineer.asyncapi.spyder.model.Schemas;
import java.util.Set;

/**
 * 
 * @author johncatlin
 *
 */
final class SchemasParser extends AsyncAPICommonObjectParser {

  static final Schemas parse(final ObjectNode node) {
    if (node == null) {
      return null;
    }
    final Schemas schemas = new SchemasImpl();
    final Set<String> schemaKeys = keySetFrom(node);
    for (final String schemaName : schemaKeys) {
      final JsonNode schemaValue = node.get(schemaName);
      if (isObjectNode(schemaValue)) {
        final ObjectNode schema = (ObjectNode) schemaValue;
        final Schema schemaObj = SchemaParser.parse(schema);
        if (schemaObj != null) {
          schemas.put(schemaName, schemaObj);
        }
      }
    }
    return schemas;
  }

  private SchemasParser() {
    /* this static utility should not be instantiated */
  }

}