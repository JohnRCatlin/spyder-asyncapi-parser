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

import engineer.asyncapi.spyder.model.bindings.BindingType;
import engineer.asyncapi.spyder.model.bindings.OperationBinding;

/**
 * 
 * @author johncatlin
 *
 */
final class OperationBindingParser extends AsyncAPICommonObjectParser {

  static final OperationBinding parse(final ObjectNode node) {
    try {
      final String operationBindingName = keySetFrom(node).iterator().next();
      final ObjectNode binding = objectNodeFrom(operationBindingName, node);
      return parse(binding, operationBindingName);
    } catch (Exception e) {
      return null;
    }
  }

  static final OperationBinding parse(final ObjectNode node, final String name) {
    if (node == null) {
      return null;
    }
    switch (BindingType.getType(name)) {
    case KAFKA:
      return KafkaOperationBindingParser.parse(node);
    case AMQP:
      return AMQP091OperationBindingParser.parse(node);
    case HTTP:
      return HTTPOperationBindingParser.parse(node);
    case MQTT:
      return MQTTOperationBindingParser.parse(node);
    case IBMMQ:
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
      return null;
    default:
      return null;
    }
  }

  private OperationBindingParser() {
    /* this static utility should not be instantiated */
  }
}