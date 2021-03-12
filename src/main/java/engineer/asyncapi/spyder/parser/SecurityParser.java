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
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.Security;

/**
 * 
 * @author johncatlin
 *
 */
final class SecurityParser extends AsyncAPICommonObjectParser {

	static final Security parse(final ArrayNode node) {
		if (null == node) {
			return null;
		}
		final SecurityImpl.Builder builder = new SecurityImpl.Builder();
		for (final JsonNode item : node) {
			builder.add(SecurityRequirementParser.parse((ObjectNode) item));
		}
		return builder.build();
	}

	private SecurityParser() {
		/* this static utility should not be instantiated */
	}
}