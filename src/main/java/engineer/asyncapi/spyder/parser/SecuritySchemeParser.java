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

import engineer.asyncapi.spyder.model.SecurityScheme;
import engineer.asyncapi.spyder.model.fields.Fields;
import engineer.asyncapi.spyder.model.security.OAuthFlows;

/**
 * 
 * @author johncatlin
 *
 */
final class SecuritySchemeParser extends AsyncAPICommonObjectParser {

  static final SecurityScheme parse(final ObjectNode node) {
    if (node == null) {
      return null;
    }
    final SecuritySchemeImpl.Builder builder = new SecuritySchemeImpl.Builder();
    if (hasRef(node)) {
      final String ref = valueOfKeyOrNull(Fields.REF.value, node);
      builder.ref(RefUtility.ensureSafeLocalReference(ref));
      return builder.build();
    }
    builder.type(valueOfKeyOrNull("type", node));
    builder.in(parseIn(node));
    builder.description(valueOfKeyOrNull("description", node));
    builder.name(valueOfKeyOrNull("name", node));
    builder.scheme(valueOfKeyOrNull("scheme", node));
    builder.bearerFormat(valueOfKeyOrNull("bearerFormat", node));
    builder.flows(parseFlows(node));
    builder.openIdConnectUrl(valueOfKeyOrNull("openIdConnectUrl", node));
    builder.extensions(parseExtensions(node));
    return builder.build();
  }

  private static final OAuthFlows parseFlows(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode flowsNode = objectNodeFrom("flows", node);
    if (null == flowsNode) {
      return null;
    }
    return OAuthFlowsParser.parse(flowsNode);
  }

  private static final String parseIn(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.IN.value, node);
  }

  private SecuritySchemeParser() {
    /* this static utility should not be instantiated */
  }

}