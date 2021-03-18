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
import engineer.asyncapi.spyder.model.OAuthScopes;
import engineer.asyncapi.spyder.model.fields.Fields;
import engineer.asyncapi.spyder.model.security.OAuthFlow;
import java.util.Set;

/**
 * 
 * @author johncatlin
 *
 */
final class OAuthFlowParser extends AsyncAPICommonObjectParser {

  static final OAuthFlow parse(final ObjectNode node) {
    if (node == null) {
      return null;
    }
    final OAuthFlowImpl.Builder builder = new OAuthFlowImpl.Builder();
    builder.authorizationUrl(valueOfKeyOrNull(Fields.AUTHORIZATION_URL.value, node));
    builder.tokenUrl(valueOfKeyOrNull(Fields.TOKEN_URL.value, node));
    builder.refreshUrl(valueOfKeyOrNull(Fields.REFRESH_URL.value, node));
    builder.scopes(parseScopes(node));
    builder.extensions(parseExtensions(node));
    return builder.build();
  }

  private static final OAuthScopes parseScopes(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode scopesNode = objectNodeFrom(Fields.SCOPES.value, node);
    if (null == scopesNode) {
      return null;
    }
    final OAuthScopesImpl.Builder builder = new OAuthScopesImpl.Builder();
    final Set<String> keys = keySetFrom(scopesNode);
    for (final String name : keys) {
      final JsonNode scopeNode = scopesNode.get(name);
      if (scopeNode != null) {
        builder.addString(name, scopeNode.asText());
      }
    }
    return builder.build();
  }

  private OAuthFlowParser() {
    /* this static utility should not be instantiated */
  }

}
