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
import engineer.asyncapi.spyder.model.bindings.BindingType;
import engineer.asyncapi.spyder.model.bindings.MessageBinding;
import engineer.asyncapi.spyder.model.bindings.MessageBindings;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author johncatlin
 *
 */
final class MessageBindingsParser extends AsyncAPICommonObjectParser {

  static final Logger log = LoggerFactory.getLogger(MessageBindingsParser.class);

  static final MessageBindings parse(final JsonNode node) {
    return parse((ObjectNode) node);
  }

  static final MessageBindings parse(final ObjectNode node) {
    if (node == null) {
      return null;
    }
    final MessageBindings messageBindings = new MessageBindingsImpl();
    final Set<String> keys = keySetFrom(node);
    for (final String key : keys) {
      final JsonNode bindingNode = node.get(key);
      if (isObjectNode(bindingNode)) {
        final MessageBinding binding = parse(key, (ObjectNode) bindingNode);
        if (binding != null) {
          messageBindings.put(key, binding);
        }
      }
    }
    return messageBindings;
  }

  static final MessageBinding parse(final String name, final JsonNode node) {
    return parse(name, (ObjectNode) node);
  }

  static final MessageBinding parse(final String type, final ObjectNode node) {
    switch (BindingType.getType(type)) {
      case KAFKA:
        return KafkaMessageBindingParser.parse(node);
      case AMQP:
        return AMQP091MessageBindingParser.parse(node);
      case HTTP:
        return HTTPMessageBindingParser.parse(node);
      case MQTT:
        return MQTTMessageBindingParser.parse(node);
      case IBMMQ:
        return IBMMQMessageBindingParser.parse(node);
      case WEBSOCKETS:
      case AMQP1:
      case MQTT5:
      case NATS:
      case JMS:
      case SNS:
      case SQS:
      case STOMP:
      case REDIS:
      case MERCURE:
        log.warn(ParseMessages.MESSAGE_BINDINGS_PARSER_NOT_IMPLEMENTED.message, type);
        return null;
      default:
        return null;
    }
  }

  private MessageBindingsParser() {
    /* this static utility should not be instantiated */
  }
}
