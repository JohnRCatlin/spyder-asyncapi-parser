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

import engineer.asyncapi.spyder.model.OperationTrait;

/**
 * 
 * @author johncatlin
 *
 */
final class OperationTraitsParser extends AsyncAPICommonObjectParser {

	static final List<OperationTrait> parse(final ArrayNode nodes) {
		if (nodes == null) {
			return null;
		}
		final List<OperationTrait> traits = new ArrayList<>();
		for (final JsonNode node : nodes) {
			if (isObjectNode(node)) {
				traits.add(OperationTraitParser.parse((ObjectNode) node));
			}
		}
		return traits;
	}

	private OperationTraitsParser() {
		/* this static utility should not be instantiated */
	}
}