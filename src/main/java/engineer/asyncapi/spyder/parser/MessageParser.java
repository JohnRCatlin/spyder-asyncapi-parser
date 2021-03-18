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

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engineer.asyncapi.spyder.model.CorrelationId;
import engineer.asyncapi.spyder.model.Message;
import engineer.asyncapi.spyder.model.MessageTraitsList;
import engineer.asyncapi.spyder.model.Schema;
import engineer.asyncapi.spyder.model.bindings.MessageBindings;
import engineer.asyncapi.spyder.model.fields.Fields;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author johncatlin
 *
 */
final class MessageParser extends AsyncAPICommonObjectParser {

  static final Message parse(final ObjectNode node) {
    if (node == null) {
      return null;
    }

    if (hasRef(node)) {
      final MessageImpl.Builder builder = new MessageImpl.Builder();
      builder.ref(parseRef(node));
      return builder.build();
    }

    final MessageImpl.Builder builder = new MessageImpl.Builder();
    builder.schemaFormat(parserSchemaFormat(node));
    builder.contentType(parseContentType(node));
    builder.name(parseName(node));
    builder.title(parseTitle(node));
    builder.summary(parseSummary(node));
    builder.description(parseDescription(node));
    builder.headers(parseHeaders(node));
    builder.payload(parsePayload(node));
    builder.correlationId(parseCorrelationId(node));
    builder.tags(parseTags(node));
    builder.externalDocs(parseExternalDocs(node));
    builder.bindings(parseMessageBindings(node));
    builder.traits(parseMessageTraits(node));
    builder.extensions(parseExtensions(objectNodeFrom(Fields.EXTENSIONS.value, node)));

    return builder.build();
  }

  static final CorrelationId parseCorrelationId(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode correlationIdNode = objectNodeFrom(Fields.CORRELATION_ID.value, node);
    if (correlationIdNode != null) {
      return CorrelationIdParser.parse(correlationIdNode);
    }
    return null;
  }

  static final MessageBindings parseMessageBindings(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode bindingsNode = objectNodeFrom(Fields.BINDINGS.value, node);
    if (bindingsNode != null) {
      return MessageBindingsParser.parse(bindingsNode);
    }
    return null;
  }

  static final MessageTraitsList parseMessageTraits(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ArrayNode traitsNode = arrayNodeFrom(Fields.TRAITS.value, node);
    if (traitsNode != null) {
      return MessageTraitsParser.parseList(traitsNode);
    }
    return null;
  }

  static final Schema parsePayload(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode payloadNode = objectNodeFrom(Fields.PAYLOAD.value, node);
    if (payloadNode != null) {
      return SchemaParser.parse(payloadNode);
    }
    return null;
  }

  static final String parserSchemaFormat(final ObjectNode node) {
    String value = valueOfKeyOrNull(Fields.SCHEMA_FORMAT.value, node);
    if (StringUtils.isNotBlank(value)) {
      return value;
    }
    return null;
  }

  private MessageParser() {
    /* this static utility should not be instantiated */
  }

}
