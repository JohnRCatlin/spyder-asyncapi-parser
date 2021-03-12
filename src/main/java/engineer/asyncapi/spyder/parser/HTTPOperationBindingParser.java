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

import engineer.asyncapi.spyder.model.Schema;
import engineer.asyncapi.spyder.model.bindings.HTTPOperationBinding;
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
final class HTTPOperationBindingParser extends AsyncAPICommonObjectParser {

	static final HTTPOperationBinding parse(final ObjectNode node) {
		HTTPOperationBinding binding = null;
		if (node == null) {
			return null;
		}
		final String bindingVersion = parseBindingVersion(node);
		if (null == bindingVersion || bindingVersion.equals(HTTPOperationBinding010Impl.BINDING_VERSION)) {
			binding = parseBindingV010(node);
		}
		return binding;
	}

	private static final HTTPOperationBinding parseBindingV010(final ObjectNode node) {
		final HTTPOperationBinding010Impl.Builder builder = new HTTPOperationBinding010Impl.Builder();
		builder.extensions(parseExtensions(node));
		builder.query(parseQuery(node));
		builder.method(parseMethod(node));
		builder.type(parseType(node));
		return builder.build();
	}

	private static String parseType(ObjectNode node) {
		return valueOfKeyOrNull(Fields.TYPE.value, node);
	}

	private static String parseMethod(ObjectNode node) {
		return valueOfKeyOrNull(Fields.METHOD.value, node);
	}

	private static Schema parseQuery(ObjectNode node) {
		if (null == node) {
			return null;
		}
		final ObjectNode query = objectNodeFrom(Fields.QUERY.value, node);
		if (null == query) {
			return null;
		}
		return SchemaParser.parse(query);
	}
	
	private HTTPOperationBindingParser() {
		/* this static utility should not be instantiated */
	}
}