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

import engineer.asyncapi.spyder.model.fields.Fields;
import engineer.asyncapi.spyder.model.security.OAuthFlows;

/**
 * 
 * @author johncatlin
 *
 */
final class OAuthFlowsParser extends AsyncAPICommonObjectParser {

	static final OAuthFlows parse(final ObjectNode node) {
		if (node == null) {
			return null;
		}
		final OAuthFlowsImpl.Builder builder = new OAuthFlowsImpl.Builder();
		builder.implicit(OAuthFlowParser.parse(objectNodeFrom(Fields.IMPILICT.value, node)));
		builder.password(OAuthFlowParser.parse(objectNodeFrom(Fields.PASSWORD.value, node)));
		builder.clientCredentials(OAuthFlowParser.parse(objectNodeFrom(Fields.CLIENT_CREDENTIALS.value, node)));
		builder.authorizationCode(OAuthFlowParser.parse(objectNodeFrom(Fields.AUTHORIZATION_CODE.value, node)));
		builder.extensions(parseExtensions(node));
		return builder.build();
	}

	private OAuthFlowsParser() {
		/* this static utility should not be instantiated */
	}
}
