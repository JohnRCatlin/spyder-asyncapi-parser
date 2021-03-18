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
import engineer.asyncapi.spyder.model.Components;
import engineer.asyncapi.spyder.model.CorrelationIds;
import engineer.asyncapi.spyder.model.MessageTraits;
import engineer.asyncapi.spyder.model.Messages;
import engineer.asyncapi.spyder.model.OperationTrait;
import engineer.asyncapi.spyder.model.OperationTraits;
import engineer.asyncapi.spyder.model.Schemas;
import engineer.asyncapi.spyder.model.SecuritySchemes;
import engineer.asyncapi.spyder.model.bindings.ChannelBinding;
import engineer.asyncapi.spyder.model.bindings.ChannelBindings;
import engineer.asyncapi.spyder.model.bindings.MessageBinding;
import engineer.asyncapi.spyder.model.bindings.MessageBindings;
import engineer.asyncapi.spyder.model.bindings.OperationBinding;
import engineer.asyncapi.spyder.model.bindings.OperationBindings;
import engineer.asyncapi.spyder.model.bindings.ServerBinding;
import engineer.asyncapi.spyder.model.bindings.ServerBindings;
import engineer.asyncapi.spyder.model.fields.Fields;
import java.util.Set;

/**
 * 
 * @author johncatlin
 *
 */
final class ComponentsParser extends AsyncAPICommonObjectParser {

  static final Components parse(final ObjectNode node) {
    try {
      final ComponentsImpl.Builder builder = new ComponentsImpl.Builder();
      builder.schemas(parseSchemas(node));
      builder.messages(parseMessages(node));
      builder.securitySchemes(parseSecuritySchemes(node));
      builder.parameters(parseParameters(node));
      builder.correlationIds(parseCorrelationIDs(node));
      builder.operationTraits(parseOperationTraits(node));
      builder.messageTraits(parseMessageTraits(node));
      builder.serverBindings(parseServerBindings(node));
      builder.channelBindings(parseChannelBindings(node));
      builder.operationBindings(parseOperationBindings(node));
      builder.messageBindings(parseMessageBindings(node));
      builder.extensions(parseExtensions(node));
      return builder.build();
    } catch (Exception e) {
      return null;
    }
  }

  private static final ChannelBindings parseChannelBindings(final ObjectNode node) {
    try {
      ObjectNode bindingsNode = objectNodeFrom(Fields.CHANNEL_BINDINGS.value, node);
      if (null == bindingsNode) {
        return null;
      }
      final ChannelBindings bindings = new ChannelBindingsImpl();
      final Set<String> keys = keySetFrom(bindingsNode);
      for (final String key : keys) {
        final JsonNode bindingTypeNode = bindingsNode.get(key);
        final String bindingType = keySetFrom(bindingTypeNode).iterator().next();
        final JsonNode bindingNode = bindingTypeNode.get(bindingType);
        if (isObjectNode(bindingNode)) {
          final ChannelBinding binding = ChannelBindingsParser.parse(bindingType,
              (ObjectNode) bindingNode);
          if (binding != null) {
            bindings.put(key, binding);
          }
        }
      }
      return bindings;
    } catch (Exception e) {
      return null;
    }
  }

  private static final CorrelationIds parseCorrelationIDs(final ObjectNode node) {
    try {
      final ObjectNode objectNode = objectNodeFrom(Fields.CORRELATION_IDS.value, node);
      if (null == objectNode) {
        return null;
      }
      return CorrelationIdsParser.parse(objectNode);
    } catch (Exception e) {
      return null;
    }
  }

  private static final MessageBindings parseMessageBindings(final ObjectNode node) {
    try {
      ObjectNode bindingsNode = objectNodeFrom(Fields.MESSAGE_BINDINGS.value, node);
      if (null == bindingsNode) {
        return null;
      }
      final MessageBindings messageBindings = new MessageBindingsImpl();
      final Set<String> keys = keySetFrom(bindingsNode);
      for (final String key : keys) {
        final JsonNode bindingTypeNode = bindingsNode.get(key);
        final String bindingType = keySetFrom(bindingTypeNode).iterator().next();
        final JsonNode bindingNode = bindingTypeNode.get(bindingType);
        if (isObjectNode(bindingNode)) {
          final MessageBinding binding = MessageBindingsParser.parse(bindingType, bindingNode);
          messageBindings.put(key, binding);
        }
      }
      return messageBindings;
    } catch (Exception e) {
      return null;
    }
  }

  private static final Messages parseMessages(final ObjectNode node) {
    try {
      final ObjectNode objectNode = objectNodeFrom(Fields.MESSAGES.value, node);
      if (null == objectNode) {
        return null;
      }
      return MessagesParser.parse(objectNode);
    } catch (Exception e) {
      return null;
    }
  }

  private static final MessageTraits parseMessageTraits(final ObjectNode node) {
    try {
      ObjectNode objectNode = objectNodeFrom(Fields.MESSAGE_TRAITS.value, node);
      if (null == objectNode) {
        return null;
      }
      return MessageTraitsParser.parse(objectNode);
    } catch (Exception e) {
      return null;
    }
  }

  private static final OperationBindings parseOperationBindings(final ObjectNode node) {
    try {
      ObjectNode objectNode = objectNodeFrom(Fields.OPERATION_BINDINGS.value, node);
      if (null == objectNode) {
        return null;
      }
      return parseComponent(objectNode);
    } catch (Exception e) {
      return null;
    }
  }

  private static final OperationBindings parseComponent(final ObjectNode node) {
    try {
      final OperationBindings operationBindings = new OperationBindingsImpl();
      final Set<String> keys = keySetFrom(node);
      for (final String key : keys) {
        final JsonNode bindingTypeNode = node.get(key);
        final String bindingType = keySetFrom(bindingTypeNode).iterator().next();
        final JsonNode bindingNode = bindingTypeNode.get(bindingType);
        if (isObjectNode(bindingNode)) {
          final OperationBinding binding = OperationBindingParser.parse((ObjectNode) bindingNode,
              bindingType);
          if (binding != null) {
            operationBindings.put(key, binding);
          }
        }
      }
      return operationBindings;
    } catch (Exception e) {
      return null;
    }
  }

  private static final OperationTraits parseOperationTraits(final ObjectNode node) {
    try {
      ObjectNode triatsNode = objectNodeFrom(Fields.OPERATION_TRAITS.value, node);
      if (null == triatsNode) {
        return null;
      }
      final OperationTraits operationTraits = new OperationTraitsImpl();
      final Set<String> operationTraitKeys = keySetFrom(triatsNode);
      for (final String operationTraitName : operationTraitKeys) {
        final JsonNode operationTraitValue = triatsNode.get(operationTraitName);
        if (isObjectNode(operationTraitValue)) {
          final ObjectNode operationTraitNode = (ObjectNode) operationTraitValue;
          final OperationTrait operationTraitObj = OperationTraitParser.parse(operationTraitNode);
          if (operationTraitObj != null) {
            operationTraits.put(operationTraitName, operationTraitObj);
          }
        }
      }
      return operationTraits;
    } catch (Exception e) {
      return null;
    }
  }

  private static final Schemas parseSchemas(final ObjectNode node) {
    try {
      final ObjectNode objectNode = objectNodeFrom(Fields.SCHEMAS.value, node);
      return SchemasParser.parse(objectNode);
    } catch (Exception e) {
      return null;
    }
  }

  private static final SecuritySchemes parseSecuritySchemes(final ObjectNode node) {
    try {
      final ObjectNode objectNode = objectNodeFrom(Fields.SECURITY_SCHEMES.value, node);
      return SecuritySchemesParser.parse(objectNode);
    } catch (Exception e) {
      return null;
    }
  }

  private static final ServerBindings parseServerBindings(final ObjectNode node) {
    try {
      final ObjectNode bindingsNode = objectNodeFrom(Fields.SERVER_BINDINGS.value, node);
      if (null == bindingsNode) {
        return null;
      }
      final ServerBindings bindings = new ServerBindingsImpl();
      final Set<String> keys = keySetFrom(bindingsNode);
      for (final String key : keys) {
        final JsonNode bindingTypeNode = bindingsNode.get(key);
        final String bindingType = keySetFrom(bindingTypeNode).iterator().next();
        final JsonNode bindingNode = bindingTypeNode.get(bindingType);
        if (isObjectNode(bindingNode)) {
          final ServerBinding binding = ServerBindingsParser.parse(bindingType,
              (ObjectNode) bindingNode);
          if (binding != null) {
            bindings.put(key, binding);
          }
        }
      }
      return bindings;
    } catch (Exception e) {
      return null;
    }
  }

  private ComponentsParser() {
    /* this static utility should not be instantiated */
  }
}