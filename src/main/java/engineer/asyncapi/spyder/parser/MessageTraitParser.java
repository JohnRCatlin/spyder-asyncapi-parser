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
import engineer.asyncapi.spyder.model.CorrelationId;
import engineer.asyncapi.spyder.model.MessageTrait;
import engineer.asyncapi.spyder.model.bindings.MessageBindings;
import engineer.asyncapi.spyder.model.fields.Fields;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author johncatlin
 *
 */
final class MessageTraitParser extends AsyncAPICommonObjectParser {

  static final MessageTrait parse(final ObjectNode node) {
    if (node == null) {
      return null;
    }
    if (hasRef(node)) {
      final String ref = parseRef(node);
      final MessageTraitImpl.Builder messageTrait = new MessageTraitImpl.Builder();
      messageTrait.ref(RefUtility.ensureSafeLocalReference(ref));
      return messageTrait.build();
    }
    final MessageTraitImpl.Builder builder = new MessageTraitImpl.Builder();
    builder.schemaFormat(parseSchemaFormat(node));
    builder.bindings(parseMessageBindings(node));
    builder.correlationId(parseCorrelationId(node));
    builder.headers(parseHeaders(node));
    builder.tags(parseTags(node));
    builder.contentType(parseContentType(node));
    builder.name(parseName(node));
    builder.title(parseTitle(node));
    builder.summary(parseSummary(node));
    builder.description(parseDescription(node));
    builder.externalDocs(parseExternalDocs(node));
    builder.extensions(parseExtensions(node));
    return builder.build();
  }

  static final CorrelationId parseCorrelationId(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode correlationIdObject = objectNodeFrom(Fields.CORRELATION_ID.value, node);
    if (correlationIdObject != null) {
      return CorrelationIdParser.parse(correlationIdObject);
    }
    return null;
  }

  static final MessageBindings parseMessageBindings(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode bindingsObject = objectNodeFrom(Fields.BINDINGS.value, node);
    if (bindingsObject != null) {
      return MessageBindingsParser.parse(bindingsObject);
    }
    return null;
  }

  static final String parseSchemaFormat(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    String value = valueOfKeyOrNull(Fields.SCHEMA_FORMAT.value, node);
    if (StringUtils.isNotBlank(value)) {
      return value;
    }
    return null;
  }

  private MessageTraitParser() {
    /* this static utility should not be instantiated */
  }
}
