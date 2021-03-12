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

import java.util.Map;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.Security;
import engineer.asyncapi.spyder.model.ServerVariable;
import engineer.asyncapi.spyder.model.bindings.ServerBindings;
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
final class ServerParser extends AsyncAPICommonObjectParser {

	static final ServerImpl parse(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		final ServerImpl.Builder builder = new ServerImpl.Builder();
		builder.extensions(parseExtensions(node));
		builder.url(parseUrl(node));
		builder.description(parseDescription(node));
		builder.protocol(parseProtocol(node));
		builder.protocolVersion(parseProtocolVersion(node));
		builder.variables(parseVariables(node));
		builder.security(parseSecurity(node));
		builder.bindings(parseServerBindings(node));
		return builder.build();
	}

	static final String parseProtocol(final ObjectNode node) {
		return valueOfKeyOrNull(Fields.PROTOCOL.value, node);
	}

	static final String parseProtocolVersion(final ObjectNode node) {
		return valueOfKeyOrNull(Fields.PROTOCOL_VERSION.value, node);
	}

	static final Security parseSecurity(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		final ArrayNode secNode = arrayNodeFrom(Fields.SECURITY.value, node);
		final Security security = SecurityParser.parse(secNode);
		if (security != null) {
			return security;
		}
		return null;
	}

	static final ServerBindings parseServerBindings(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		final ObjectNode objectNode = objectNodeFrom(Fields.BINDINGS.value, node);
		final ServerBindings bindings = ServerBindingsParser.parse(objectNode);
		if (bindings != null && bindings.size() > 0) {
			return bindings;
		}
		return null;
	}

	static final Map<String, ServerVariable> parseVariables(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		if (node.get("variables") != null) {
			final ObjectNode variables = objectNodeFrom(Fields.VARIABLES.value, node);
			final Map<String, ServerVariable> serverVariables = ServerVariablesParser.parse(variables);
			if (serverVariables != null && serverVariables.size() > 0) {
				return serverVariables;
			}
		}
		return null;
	}

	private ServerParser() {
		/* this static utility should not be instantiated */
	}
}