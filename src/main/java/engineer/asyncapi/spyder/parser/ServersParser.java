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

import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.Servers;

/**
 * 
 * @author johncatlin
 *
 */
final class ServersParser extends AsyncAPICommonObjectParser {

	static final Servers parse(final ObjectNode node) {
		if (node == null) {
			return null;
		}
		final Servers servers = new ServersImpl();
		final Iterator<Map.Entry<String, JsonNode>> i = node.fields();
		while (i.hasNext()) {
			final Map.Entry<String, JsonNode> entry = i.next();
			if (isObjectNode(entry.getValue())) {
				final ServerImpl server = ServerParser.parse((ObjectNode) entry.getValue());
				if (server != null) {
					servers.put(entry.getKey(), server);
				} else {
					final ServerImpl.Builder defaultServer = new ServerImpl.Builder();
					defaultServer.url("/");
					servers.put("null", defaultServer.build());
				}
			}
		}
		return servers;
	}

	private ServersParser() {
		/* this static utility should not be instantiated */
	}
}