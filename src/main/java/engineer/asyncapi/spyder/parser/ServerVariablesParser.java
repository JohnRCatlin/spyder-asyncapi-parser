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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.ServerVariable;

/**
 * 
 * @author johncatlin
 *
 */
final class ServerVariablesParser extends AsyncAPICommonObjectParser {

	static final Map<String, ServerVariable> parse(final ObjectNode node) {
		if (node == null) {
			return null;
		}
		final Map<String, ServerVariable> serverVariables = new LinkedHashMap<>();
		final Set<String> serverKeys = keySetFrom(node);
		for (final String serverName : serverKeys) {
			final JsonNode serverValue = node.get(serverName);
			final ObjectNode server = (ObjectNode) serverValue;
			final ServerVariable serverVariable = ServerVariableParser.parse(server);
			serverVariables.put(serverName, serverVariable);
		}
		return serverVariables;
	}

	private ServerVariablesParser() {
		/* this static utility should not be instantiated */
	}

}