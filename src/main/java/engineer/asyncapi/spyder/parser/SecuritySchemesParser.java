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

import engineer.asyncapi.spyder.model.SecurityScheme;
import engineer.asyncapi.spyder.model.SecuritySchemes;

/**
 * 
 * @author johncatlin
 *
 */
final class SecuritySchemesParser extends AsyncAPICommonObjectParser {

	static final SecuritySchemes parse(final ObjectNode obj) {
		if (obj == null) {
			return null;
		}
		final SecuritySchemes securitySchemes = new SecuritySchemesImpl();
		final Set<String> keys = keySetFrom(obj);
		for (final String key : keys) {
			final JsonNode securitySchemeNode = obj.get(key);
			if (isObjectNode(securitySchemeNode)) {
				final SecurityScheme securityScheme = SecuritySchemeParser.parse((ObjectNode) securitySchemeNode);
				if (securityScheme != null) {
					securitySchemes.put(key, securityScheme);
				}
			}
		}
		return securitySchemes;
	}

	private SecuritySchemesParser() {
		/* this static utility should not be instantiated */
	}
}