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

import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.Extensions;
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
final class ExtensionsParser extends NodeClipper {

	public static boolean isValidExtension(final String extensionKey) {
		return !notValidExtension(extensionKey);
	}

	public static boolean notValidExtension(final String extensionKey) {
		return extensionKey == null || extensionKey.isEmpty() || !extensionKey.startsWith(Fields.EXTENSIONS_PRFIX.value);
	}

	static final Extensions parse(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		return parseExtensions(node);
	}

	private static final Extensions parseExtensions(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		final Set<String> keys = keySetFrom(node);
		final ExtensionsImpl extensions = new ExtensionsImpl();
		for (final String key : keys) {
			if (key.startsWith(Fields.EXTENSIONS_PRFIX.value)) {
				extensions.put(key, valueOfKeyOrNull(key, node));
			}
		}
		if (extensions.isEmpty()) {
			return null;
		}
		return extensions;
	}

	private ExtensionsParser() {
		/* this static utility should not be instantiated */
	}
}