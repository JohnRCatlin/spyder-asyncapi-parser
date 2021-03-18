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
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engineer.asyncapi.spyder.model.Message;
import engineer.asyncapi.spyder.model.Operation;
import engineer.asyncapi.spyder.model.OperationTrait;
import engineer.asyncapi.spyder.model.bindings.OperationBindings;
import engineer.asyncapi.spyder.model.fields.Fields;
import java.util.List;

/**
 * 
 * @author johncatlin
 *
 */
final class OperationParser extends AsyncAPICommonObjectParser {

  static final Operation parse(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final OperationImpl.Builder builder = new OperationImpl.Builder();
    builder.operationId(parseOperationId(node));
    builder.summary(parseSummary(node));
    builder.description(parseDescription(node));
    builder.tags(parseTags(node));
    builder.externalDocs(parseExternalDocs(node));
    builder.extensions(parseExtensions(node));
    builder.bindings(parseBindings(node));
    builder.traits(parseOperatoinTraits(node));
    builder.message(parseMessage(node));
    return builder.build();
  }

  static final OperationBindings parseBindings(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    ObjectNode bindingsNode = objectNodeFrom(Fields.BINDINGS.value, node);
    final OperationBindings bindings = OperationBindingsParser.parse(bindingsNode);
    if (bindings != null && bindings.size() > 0) {
      return bindings;
    }
    return null;
  }

  // smelly?
  static Message parseMessage(final ObjectNode node) {
    final ObjectNode requestObjectNode = objectNodeFrom(Fields.MESSAGE.value, node);
    if (requestObjectNode != null) {
      final ArrayNode oneOfArray = arrayNodeFrom(Fields.ONE_OF.value, requestObjectNode);
      if (oneOfArray != null) {
        ObjectNode itObj = null;
        for (final JsonNode n : oneOfArray) {
          if (n.isObject()) {
            itObj = n.deepCopy();
            return MessageParser.parse(itObj);
          }
        }
      } else {
        return MessageParser.parse(requestObjectNode);
      }
    }
    return null;
  }

  static final List<OperationTrait> parseOperatoinTraits(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    ArrayNode array = arrayNodeFrom(Fields.TRAITS.value, node);
    if (array != null && array.size() > 0) {
      return OperationTraitsParser.parse(array);
    }
    return null;
  }

  private OperationParser() {
    /* this static utility should not be instantiated */
  }

}