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

import engineer.asyncapi.spyder.model.Parameter;
import engineer.asyncapi.spyder.model.Schema;
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
final class ParameterParser extends AsyncAPICommonObjectParser {

	public static final Parameter parse(final ObjectNode node) {
		if (node == null) {
			return null;
		}
		ParameterImpl.Builder builder = new ParameterImpl.Builder();
		builder.schema(parseSchema(node));
		builder.ref(parseRef(node));
		builder.description(parseDescription(node));
		builder.location(parseLocation(node));
		builder.extensions(parseExtensions(node));
		return builder.build();
	}

	static final Schema parseSchema(final ObjectNode node) {
		final ObjectNode schemaNode = objectNodeFrom(Fields.SCHEMA.value, node);
		if (schemaNode != null) {
			return SchemaParser.parse(schemaNode);
		}
		return null;
	}

	private ParameterParser() {
		/* this static utility should not be instantiated */
	}
}