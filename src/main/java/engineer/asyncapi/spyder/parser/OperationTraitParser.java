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

import engineer.asyncapi.spyder.model.OperationTrait;
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
final class OperationTraitParser extends AsyncAPICommonObjectParser {

  static final OperationTrait parse(final JsonNode node) {
    return parse((ObjectNode) node);
  }

  static final OperationTrait parse(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final JsonNode ref = node.get(Fields.REF.value);
    if (hasRef(node)) {
      if (isStringNode(ref)) {
        return parseReferenceToOperationTrait(ref);
      } else {
        return null;
      }
    } else {
      return parseInlineOperationTrait(node);
    }
  }

  static final OperationTrait parseInlineOperationTrait(final JsonNode node) {
    return parseInlineOperationTrait((ObjectNode) node);
  }

  static final OperationTrait parseInlineOperationTrait(final ObjectNode node) {
    final OperationTraitImpl.Builder builder = new OperationTraitImpl.Builder();
    builder.operationId(parseOperationId(node));
    builder.summary(parseSummary(node));
    builder.description(parseDescription(node));
    builder.tags(TagsParser.parse(arrayNodeFrom(Fields.TAGS.value, node)));
    builder
        .externalDocs(ExternalDocsParser.parse(objectNodeFrom(Fields.EXTERNAL_DOCS.value, node)));
    builder.bindings(OperationBindingParser.parse(objectNodeFrom(Fields.BINDINGS.value, node)));
    builder.extensions(ExtensionsParser.parse(node));
    return builder.build();
  }

  static final OperationTrait parseReferenceToOperationTrait(final JsonNode ref) {
    final OperationTraitImpl.Builder builder = new OperationTraitImpl.Builder();
    builder.ref(RefUtility.ensureSafeLocalReference(ref.textValue()));
    return builder.build();
  }

  private OperationTraitParser() {
    /* this static utility should not be instantiated */
  }

}