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
import engineer.asyncapi.spyder.model.bindings.AMQP091OperationBinding;
import engineer.asyncapi.spyder.model.bindings.AMQP091OperationBinding020;
import engineer.asyncapi.spyder.model.fields.Fields;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author johncatlin
 *
 */
final class AMQP091OperationBindingParser extends AsyncAPICommonObjectParser {

  static final AMQP091OperationBinding parse(final ObjectNode node) {
    try {
      final String bindingVersion = parseBindingVersion(node);
      if (null == bindingVersion
          || bindingVersion.equals(AMQP091OperationBinding020Impl.BINDING_VERSION)) {
        return parseBindingV020(node);
      }
      // use latest
      return parseBindingV020(node);
    } catch (Exception e) {
      return null;
    }
  }

  private static final Boolean parseAck(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    return booleanFrom(Fields.ACK.value, node);
  }

  private static final List<String> parseBcc(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    ArrayNode array = arrayNodeFrom("bcc", node);
    if (null == array) {
      return null;
    }
    final List<String> list = new ArrayList<>();
    for (final JsonNode itemNode : array) {
      final String item = itemNode.asText();
      list.add(item);
    }
    if (!list.isEmpty()) {
      return list;
    }
    return null;
  }

  private static final List<String> parseCc(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    ArrayNode array = arrayNodeFrom("cc", node);
    if (null == array) {
      return null;
    }
    final List<String> list = new ArrayList<>();
    for (final JsonNode itemNode : array) {
      final String item = itemNode.asText();
      list.add(item);
    }
    if (!list.isEmpty()) {
      return list;
    }
    return null;
  }

  private static final Integer parseDeliveryMode(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    return integerFrom(Fields.DELIVERY_MODE.value, node);
  }

  private static final Long parseExpiration(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    return longFrom(Fields.EXPIRATION.value, node);
  }

  private static final Boolean parseMandatory(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    return booleanFrom(Fields.MANDATORY.value, node);
  }

  static final AMQP091OperationBinding020 parseBindingV020(final ObjectNode node) {
    final AMQP091OperationBinding020Impl.Builder builder = new AMQP091OperationBinding020Impl.Builder();
    builder.extensions(parseExtensions(node));
    builder.ack(parseAck(node));
    builder.bcc(parseBcc(node));
    builder.cc(parseCc(node));
    builder.deliveryMode(parseDeliveryMode(node));
    builder.expiration(parseExpiration(node));
    builder.extensions(parseExtensions(node));
    builder.mandatory(parseMandatory(node));
    builder.priority(parsePriority(node));
    builder.replyTo(parseReplyTo(node));
    builder.timestamp(parseTimestamp(node));
    builder.userId(parseUserId(node));
    return builder.build();
  }

  private static final Integer parsePriority(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    return integerFrom(Fields.PRIORITY.value, node);
  }

  private static final String parseReplyTo(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    return stringFrom(Fields.REPLY_TO.value, node);
  }

  private static final Boolean parseTimestamp(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    return booleanFrom(Fields.TIMESTAMP.value, node);
  }

  private static final String parseUserId(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    return stringFrom(Fields.USER_ID.value, node);
  }

  private AMQP091OperationBindingParser() {
    /* this static utility should not be instantiated */
  }

}