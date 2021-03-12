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
import engineer.asyncapi.spyder.model.bindings.KafkaMessageBinding;
import engineer.asyncapi.spyder.model.bindings.KafkaMessageBinding010;
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
final class KafkaMessageBindingParser extends AsyncAPICommonObjectParser {

	static final KafkaMessageBinding parse(final ObjectNode node) {
		KafkaMessageBinding binding = null;
		if (node == null) {
			return null;
		}
		final String bindingVersion = parseBindingVersion(node);
		if (null == bindingVersion || bindingVersion.equals(KafkaMessageBinding010Impl.BINDING_VERSION)) {
			binding = parseBindingV010(node);
		}
		return binding;
	}

	private static final Schema parseKey(final ObjectNode node) {
		final ObjectNode obj = objectNodeFrom(Fields.KEY.value, node);
		if (obj != null) {
			return SchemaParser.parse(obj);
		}
		return null;
	}

	private static final KafkaMessageBinding010 parseBindingV010(final ObjectNode node) {
		final KafkaMessageBinding010Impl.Builder builder = new KafkaMessageBinding010Impl.Builder();
		builder.setKey(parseKey(node));
		builder.extensions(parseExtensions(node));
		return builder.build();
	}

	private KafkaMessageBindingParser() {
		/* this static utility should not be instantiated */
	}
}