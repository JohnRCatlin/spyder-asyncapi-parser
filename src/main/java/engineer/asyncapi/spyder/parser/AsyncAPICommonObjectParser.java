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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.Extensions;
import engineer.asyncapi.spyder.model.ExternalDocs;
import engineer.asyncapi.spyder.model.Parameters;
import engineer.asyncapi.spyder.model.Tags;
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
abstract class AsyncAPICommonObjectParser extends AsyncAPICommonFieldParser {
	
	static final List<String> parseEnum(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		ArrayNode arrayNode = arrayNodeFrom(Fields.ENUM.value, node);
		if (null == arrayNode) {
			return null;
		}
		final List<String> theEnum = new ArrayList<>();
		for (final JsonNode n : arrayNode) {
			if (n.isValueNode()) {
				theEnum.add(n.asText());
			}
		}
		return theEnum;
	}

	static final Extensions parseExtensions(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		// smell?
		final Extensions extensionsOuter = parseExtensionsInnernals(objectNodeFrom(Fields.EXTENSIONS.value, node));
		if (extensionsOuter != null && extensionsOuter.size() > 0) {
			return extensionsOuter;
		}
		final Extensions extensionsInner = parseExtensionsInnernals(node);
		if (extensionsInner != null && extensionsInner.size() > 0) {
			return extensionsInner;
		}
		return null;
	}

	private static final Extensions parseExtensionsInnernals(final ObjectNode node) {
		final Extensions extensions = ExtensionsParser.parse(node);
		if (extensions != null && extensions.size() > 0) {
			return extensions;
		}
		return null;
	}

	static final ExternalDocs parseExternalDocs(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		final ObjectNode objectNode = objectNodeFrom(Fields.EXTERNAL_DOCS.value, node);
		return ExternalDocsParser.parse(objectNode);
	}

	static final Parameters parseParameters(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		final ObjectNode objectNode = objectNodeFrom(Fields.PARAMETERS.value, node);
		final Parameters parameters = ParametersParser.parse(objectNode);
		if (parameters != null && parameters.size() > 0) {
			return parameters;
		}
		return null;
	}

	static final Tags parseTags(final ObjectNode node) {
		ArrayNode tagsNode = arrayNodeFrom(Fields.TAGS.value, node);
		final Tags tags = TagsParser.parse(tagsNode);
		if (tags != null && !tags.isEmpty()) {
			return tags;
		}
		return null;
	}

	protected AsyncAPICommonObjectParser() {
		/* this static utility should not be instantiated */
	}
}