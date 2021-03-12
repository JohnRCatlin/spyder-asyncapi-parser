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
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @author johncatlin
 *
 */
final class ExampleParser extends AsyncAPICommonObjectParser {

	//object type smell
	static final Object parse(final String nodeKey, final ObjectNode node) {
		if (null == node) {
			return null;
		}
		final JsonNode example = node.get(nodeKey);
		if (null == example) {
			return null;
		}
		if (isStringNode(example)) {
			return valueOfKeyOrNull(nodeKey, node);
		}
		if (isIntegerNode(example)) {
			return integerFrom(nodeKey, node);
		}
		if (isBigDecimalNode(example)) {
			return bigDecimalFrom(nodeKey, node);
		}
		if (isObjectNode(example)) {
			return objectNodeFrom(nodeKey, node);
		}
		if (isArrayNode(example)) {
			return arrayNodeFrom(nodeKey, node);
		}
		if (isBooleanNode(example)) {
			return booleanFrom(nodeKey, node);
		}
		return null;
	}

	private ExampleParser() {
		/* this static utility should not be instantiated */
	}

}