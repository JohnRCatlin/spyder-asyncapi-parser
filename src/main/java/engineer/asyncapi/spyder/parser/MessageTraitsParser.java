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
import engineer.asyncapi.spyder.model.MessageTrait;
import engineer.asyncapi.spyder.model.MessageTraits;
import engineer.asyncapi.spyder.model.MessageTraitsList;
import java.util.Set;

/**
 * 
 * @author johncatlin
 *
 */
final class MessageTraitsParser extends AsyncAPICommonObjectParser {

  static final MessageTraits parse(final ObjectNode obj) {
    if (obj == null) {
      return null;
    }
    final MessageTraits messageTraits = new MessageTraitsImpl();
    final Set<String> messageTraitKeys = keySetFrom(obj);
    for (final String messageTraitName : messageTraitKeys) {
      final JsonNode messageTraitValue = obj.get(messageTraitName);
      if (isObjectNode(messageTraitValue)) {
        final ObjectNode messageTrait = (ObjectNode) messageTraitValue;
        final MessageTrait messageTraitObj = MessageTraitParser.parse(messageTrait);
        if (messageTraitObj != null) {
          messageTraits.put(messageTraitName, messageTraitObj);
        }
      }
    }
    return messageTraits;
  }

  static final MessageTraitsList parseList(final ArrayNode nodes) {
    if (nodes == null) {
      return null;
    }
    final MessageTraitsList messageTraits = new MessageTriatsListImpl();
    for (final JsonNode node : nodes) {
      if (isObjectNode(node)) {
        final MessageTrait messageTrait = MessageTraitParser.parse((ObjectNode) node);
        messageTraits.add(messageTrait);
      }
    }
    return messageTraits;
  }

  private MessageTraitsParser() {
    /* this static utility should not be instantiated */
  }
}
