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

import engineer.asyncapi.spyder.model.Example;
import engineer.asyncapi.spyder.model.Extensions;

/**
 * 
 * @author johncatlin
 *
 */
final class ExampleImpl implements Example {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private static final String REF_BASE = "#/components/examples/";
		private ExampleImpl example = new ExampleImpl();

		public final Example build() {
			return this.example;
		}

		public final Builder description(final String description) {
			example.description = description;
			return this;
		}

		public final Builder extension(final String name, final String value) {
			if (ExtensionsParser.notValidExtension(name)) {
				return this;
			}
			example.extensions.put(name, value);
			return this;
		}

		public final Builder extensions(final Extensions extensions) {
			example.extensions = extensions;
			return this;
		}

		public final Builder externalValue(final String externalValue) {
			example.externalValue = externalValue;
			return this;
		}

		public final Builder ref(String ref) {
			example.ref = (ref != null && RefUtility.isRelative(ref)) ? REF_BASE + ref : ref;
			return this;
		}

		public final Builder summary(final String summary) {
			example.summary = summary;
			return this;
		}

		public final Builder value(final Object value) {
			example.value = value;
			return this;
		}
	}
	private String description = null;
	private Extensions extensions = null;
	private String externalValue = null;
	private String ref = null;
	private String summary = null;

	private Object value = null;

	private ExampleImpl() {
		/* Use the builder for construction. */
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public Extensions getExtensions() {
		return this.extensions;
	}

	@Override
	public String getExternalValue() {
		return this.externalValue;
	}

	@Override
	public String getRef() {
		return this.ref;
	}

	@Override
	public String getSummary() {
		return this.summary;
	}

	@Override
	public Object getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return ToStringFormatter.toString(this);
	}
}
