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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.bindings.OperationBinding;
import engineer.asyncapi.spyder.model.bindings.OperationBindings;

/**
 * 
 * @author johncatlin
 *
 */
final class OperationBindingsParser extends AsyncAPICommonObjectParser {

	static final Logger log = LoggerFactory.getLogger(OperationBindingsParser.class);

	static final OperationBindings parse(final ObjectNode node) {
		if (node == null) {
			return null;
		}
		final OperationBindings operationBindings = new OperationBindingsImpl();
		final Set<String> keys = keySetFrom(node);
		for (final String key : keys) {
			final JsonNode bindingNode = node.get(key);
			if (isObjectNode(bindingNode)) {
				final OperationBinding binding = OperationBindingParser.parse((ObjectNode) bindingNode, key);
				if (binding != null) {
					operationBindings.put(key, binding);
				}
			}
		}
		return operationBindings;
	}

	private OperationBindingsParser() {
		/* this static utility should not be instantiated */
	}
}