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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import engineer.asyncapi.spyder.model.Schema;
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
final class SchemaParser extends AsyncAPICommonObjectParser {

  static final Schema parse(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    SchemaImpl.Builder builder = new SchemaImpl.Builder();
    // parse discrete property nodes
    builder.required(parseRequired(node));
    builder.extensions(parseExtensions(node));
    builder.format(parseFormat(node));
    builder.minimum(parseMinimum(node));
    builder.maximum(parseMaximum(node));
    builder.ref(parseRef(node));
    builder.theEnum(parseEnum(node));
    builder.title(parseTitle(node));
    builder.description(parseDescription(node));
    builder.multipleOf(parseMultipleOf(node));
    builder.exclusiveMaximum(parseExclusiveMaximum(node));
    builder.exclusiveMinimum(parseExclusiveMinimum(node));
    builder.maxLength(parseMaxLength(node));
    builder.minLength(parseMinLength(node));
    builder.pattern(parsePattern(node));
    builder.items(parseItems(node));
    builder.maxItems(parseMaxItems(node));
    builder.minItems(parseMinItems(node));
    builder.uniqueItems(parseUniqueItems(node));
    builder.maxProperties(parseMaxProperties(node));
    builder.minProperties(parseMinProperties(node));
    builder.theConst(parseConst(node));
    builder.readOnly(parseReadOnly(node));
    builder.writeOnly(parseWriteOnly(node));
    builder.additionalItems(parseAdditionalItems(node));
    builder.additionalProperties(parseAdditionalProperties(node));
    builder.allOf(parseAllOf(node));
    builder.anyOf(parseAnyOf(node));
    builder.oneOf(parseOneOf(node));
    builder.not(parseNot(node));
    builder.externalDocs(parseExternalDocs(node));
    builder.discriminator(parseDiscriminator(node));
    builder.deprecated(parseDeprecated(node));
    builder.type(parseType(node));
    builder.theDefault(parseDefault(node));
    // parse (potentially) recursive nodes
    builder.properties(parseProperties(node));
    return builder.build();
  }

  static final Boolean parseAdditionalItems(final ObjectNode node) {
    return booleanFrom(Fields.ADDITIONAL_ITEMS.value, node);
  }

  static final Boolean parseAdditionalProperties(final ObjectNode node) {
    return booleanFrom(Fields.ADDITIONAL_PROPERTIES.value, node);
  }

  static final List<String> parseAllOf(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ArrayNode constraintsNode = arrayNodeFrom(Fields.ALL_OF.value, node);
    if (constraintsNode != null) {
      final List<String> constraints = new ArrayList<>();
      for (final JsonNode itemNode : constraintsNode) {
        final String item = valueOfKeyOrNull(Fields.REF.value, (ObjectNode) itemNode);
        constraints.add(item);
      }
      if (!constraints.isEmpty()) {
        return constraints;
      }
    }
    return null;
  }

  static final List<String> parseAnyOf(ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ArrayNode constraintsNode = arrayNodeFrom(Fields.ANY_OF.value, node);
    if (constraintsNode != null) {
      final List<String> constraints = new ArrayList<>();
      for (final JsonNode itemNode : constraintsNode) {
        final String item = valueOfKeyOrNull(Fields.REF.value, (ObjectNode) itemNode);
        constraints.add(item);
      }
      if (!constraints.isEmpty()) {
        return constraints;
      }
    }
    return null;
  }

  static final Boolean parseConst(final ObjectNode node) {
    return booleanFrom(Fields.CONST.value, node);
  }

  static final Boolean parseDeprecated(final ObjectNode node) {
    return booleanFrom(Fields.DEPRECATED.value, node);
  }

  static final String parseDiscriminator(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.DISCRIMINATOR.value, node);
  }

  static final Boolean parseExclusiveMaximum(final ObjectNode node) {
    return booleanFrom(Fields.EXCLUSIVE_MAXIMUM.value, node);
  }

  static final Boolean parseExclusiveMinimum(final ObjectNode node) {
    return booleanFrom(Fields.EXCLUSIVE_MINIMUM.value, node);
  }

  static final String parseFormat(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.FORMAT.value, node);
  }

  static final Schema parseItems(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    ObjectNode itemsNode = objectNodeFrom(Fields.ITEMS.value, node);
    return SchemaParser.parse(itemsNode);
  }

  static final BigDecimal parseMaximum(final ObjectNode node) {
    return bigDecimalFrom(Fields.MAXIMUM.value, node);
  }

  static final Integer parseMaxItems(final ObjectNode node) {
    return integerFrom(Fields.MAX_ITEMS.value, node);
  }

  static final Integer parseMaxLength(final ObjectNode node) {
    return integerFrom(Fields.MAX_LENGTH.value, node);
  }

  static final Integer parseMaxProperties(final ObjectNode node) {
    return integerFrom(Fields.MAX_PROPERTIES.value, node);
  }

  static final BigDecimal parseMinimum(final ObjectNode node) {
    return bigDecimalFrom(Fields.MINIMUM.value, node);
  }

  static final Integer parseMinItems(final ObjectNode node) {
    return integerFrom(Fields.MIN_ITEMS.value, node);
  }

  static final Integer parseMinLength(final ObjectNode node) {
    return integerFrom(Fields.MIN_LENGTH.value, node);
  }

  static final Integer parseMinProperties(final ObjectNode node) {
    return integerFrom(Fields.MIN_PROPERTIES.value, node);
  }

  static final BigDecimal parseMultipleOf(final ObjectNode node) {
    return bigDecimalFrom(Fields.MULTIPLE_OF.value, node);
  }

  static final List<String> parseNot(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ArrayNode constraintsNode = arrayNodeFrom(Fields.NOT.value, node);
    if (constraintsNode != null) {
      final List<String> constraints = new ArrayList<>();
      for (final JsonNode itemNode : constraintsNode) {
        final String item = valueOfKeyOrNull(Fields.REF.value, (ObjectNode) itemNode);
        constraints.add(item);
      }
      if (!constraints.isEmpty()) {
        return constraints;
      }
    }
    return null;
  }

  static final List<String> parseOneOf(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ArrayNode constraintsNode = arrayNodeFrom(Fields.ONE_OF.value, node);
    if (constraintsNode != null) {
      final List<String> constraints = new ArrayList<>();
      for (final JsonNode itemNode : constraintsNode) {
        final String item = valueOfKeyOrNull(Fields.REF.value, (ObjectNode) itemNode);
        constraints.add(item);
      }
      if (!constraints.isEmpty()) {
        return constraints;
      }
    }
    return null;
  }

  static final String parsePattern(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.PATTERN.value, node);
  }

  static final Map<String, Schema> parseProperties(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    ObjectNode propertiesNode = objectNodeFrom(Fields.PROPERTIES.value, node);
    final Set<String> keys = keySetFrom(propertiesNode);
    Map<String, Schema> properties = new HashMap<>();
    for (final String key : keys) {
      final JsonNode propertyNode = propertiesNode.get(key);
      if (propertyNode != null) {
        Schema property = SchemaParser.parse((ObjectNode) propertyNode);
        properties.put(key, property);
      }
    }
    if (properties.isEmpty()) {
      return null;
    }
    return properties;
  }

  static final Boolean parseReadOnly(final ObjectNode node) {
    return booleanFrom(Fields.WRITE_ONLY.value, node);
  }

  static final List<String> parseRequired(final ObjectNode node) {
    final ArrayNode requiredNode = arrayNodeFrom(Fields.REQUIRED.value, node);
    if (requiredNode != null) {
      final List<String> required = new ArrayList<>();
      for (final JsonNode itemNode : requiredNode) {
        if (itemNode.getNodeType().equals(JsonNodeType.STRING)) {
          final String requiredItem = ((TextNode) itemNode).textValue();
          required.add(requiredItem);
        }
      }
      if (!required.isEmpty()) {
        return required;
      }
    }
    return null;
  }

  static final Boolean parseUniqueItems(final ObjectNode node) {
    return booleanFrom(Fields.UNIQUE_ITEMS.value, node);
  }

  static final Boolean parseWriteOnly(final ObjectNode node) {
    return booleanFrom(Fields.READ_ONLY.value, node);
  }

  private SchemaParser() {
    /* this static utility should not be instantiated */
  }
}