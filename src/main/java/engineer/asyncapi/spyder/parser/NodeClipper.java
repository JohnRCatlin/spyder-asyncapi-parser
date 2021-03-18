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
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
class NodeClipper {

  static final ArrayNode arrayNodeFrom(final String key, final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final JsonNode value = node.get(key);
    if (null == value) {
      return null;
    }
    if (!isArrayNode(value)) {
      return null;
    }
    return (ArrayNode) value;
  }

  static final BigDecimal bigDecimalFrom(final String key, final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final JsonNode v = node.get(key);
    if (null == v) {
      return null;
    } else if (v.getNodeType().equals(JsonNodeType.NUMBER)) {
      return new BigDecimal(v.asText());
    }
    return null;
  }

  static final Boolean booleanFrom(final String key, final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final JsonNode v = node.get(key);
    if (null == v) {
      return null;
    }
    if (v.isBoolean()) {
      return v.asBoolean();
    }
    if (v.getNodeType().equals(JsonNodeType.STRING)) {
      return Boolean.parseBoolean(v.textValue());
    }
    return null;
  }

  static final Integer integerFrom(final String key, final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final JsonNode v = node.get(key);
    if (null != v && v.canConvertToInt()) {
      return v.intValue();
    }
    return null;
  }

  static final boolean isArrayNode(final JsonNode node) {
    if (null == node) {
      return false;
    }
    return node.getNodeType().equals(JsonNodeType.ARRAY);
  }

  static final boolean isBigDecimalNode(final JsonNode node) {
    if (null == node) {
      return false;
    }
    return node.isBigDecimal();
  }

  static final boolean isBooleanNode(final JsonNode node) {
    if (null == node) {
      return false;
    }
    return node.getNodeType().equals(JsonNodeType.BOOLEAN);
  }

  static final boolean isIntegerNode(final JsonNode node) {
    if (null == node) {
      return false;
    }
    return node.isIntegralNumber();
  }

  static final boolean isNullOrEmpty(final String string) {
    return string == null || string.trim().isEmpty();
  }

  static final boolean isNumberNode(final JsonNode node) {
    if (null == node) {
      return false;
    }
    return node.getNodeType().equals(JsonNodeType.NUMBER);
  }

  static final boolean isObjectNode(final JsonNode node) {
    if (null == node) {
      return false;
    }
    return node.getNodeType().equals(JsonNodeType.OBJECT);
  }

  static final boolean isStringNode(final JsonNode node) {
    if (null == node) {
      return false;
    }
    return node.getNodeType().equals(JsonNodeType.STRING);
  }

  static final Set<String> keySetFrom(final JsonNode node) {
    return keySetFrom((ObjectNode) node);
  }

  static final Set<String> keySetFrom(final ObjectNode node) {
    if (null == node) {
      return new LinkedHashSet<>();
    }
    final Set<String> keys = new LinkedHashSet<>();
    final Iterator<String> i = node.fieldNames();
    while (i.hasNext()) {
      keys.add(i.next());
    }
    return keys;
  }

  static final Long longFrom(final String key, final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final JsonNode v = node.get(key);
    if (null != v && v.canConvertToLong()) {
      return v.longValue();
    }
    return null;
  }

  static final ObjectNode objectNodeFrom(final String key, final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final JsonNode value = node.get(key);
    if (null == value) {
      return null;
    }
    if (!isObjectNode(value)) {
      return null;
    }
    return (ObjectNode) value;
  }

  static final String stringFrom(final String key, final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final JsonNode value = node.get(key);
    if (null == value) {
      return null;
    }
    if (!value.isValueNode()) {
      return null;
    }
    return value.asText();
  }

  static final String valueOfKeyOrNull(final String key, final ObjectNode node) {
    final String value = stringFrom(key, node);
    if (StringUtils.isNotBlank(value)) {
      if (value.equals(Fields.NULL.value)) {
        return null;
      }
      return value;
    }
    return null;
  }

  protected NodeClipper() {
    /* this static utility should not be instantiated */
  }
}
