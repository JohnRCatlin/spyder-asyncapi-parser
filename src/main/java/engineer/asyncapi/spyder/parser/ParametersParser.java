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

import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.Parameter;
import engineer.asyncapi.spyder.model.Parameters;

/**
 * 
 * @author johncatlin
 *
 */
final class ParametersParser extends AsyncAPICommonObjectParser {

  static final Parameters parse(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final Parameters parameters = new ParametersImpl();
    final Set<String> keys = keySetFrom(node);
    for (final String key : keys) {
      final JsonNode parameterNode = node.get(key);
      if (isObjectNode(parameterNode)) {
        Parameter parameter = ParameterParser.parse((ObjectNode) parameterNode);
        if (parameter != null) {
          parameters.put(key, parameter);
        }
      }
    }
    return parameters;
  }

  private ParametersParser() {
    /* this static utility should not be instantiated */
  }
}
