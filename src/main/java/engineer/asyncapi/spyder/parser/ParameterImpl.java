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

import engineer.asyncapi.spyder.model.Extensions;
import engineer.asyncapi.spyder.model.Parameter;
import engineer.asyncapi.spyder.model.Schema;

/**
 * 
 * @author johncatlin
 *
 */
final class ParameterImpl implements Parameter {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private static final String REF_BASE = "#/components/parameters/";
		private ParameterImpl parameter = new ParameterImpl();

		final ParameterImpl build() {
			return parameter;
		}

		final Builder description(final String description) {
			parameter.description = description;
			return this;
		}

		final Builder extension(final String name, final String value) {
			if (ExtensionsParser.notValidExtension(name)) {
				return this;
			}
			parameter.extensions.put(name, value);
			return this;
		}

		final Builder extensions(final Extensions extensions) {
			parameter.extensions = extensions;
			return this;
		}

		final Builder location(final String location) {
			parameter.location = location;
			return this;
		}

		final Builder ref(final String ref) {
			parameter.ref = (ref != null && RefUtility.isRelative(ref)) ? REF_BASE + ref : ref;
			return this;
		}

		final Builder schema(final Schema schema2) {
			parameter.schema = schema2;
			return this;
		}
	}

	private String description = null;
	private Extensions extensions = null;
	private String location = null;
	private String ref = null;
	private Schema schema = null;

	private ParameterImpl() {
		/* Use the builder for construction. */
	}

	@Override
	public final String getDescription() {
		return this.description;
	}

	@Override
	public final Extensions getExtensions() {
		return this.extensions;
	}

	@Override
	public final String getLocation() {
		return this.location;
	}

	@Override
	public final String getRef() {
		return this.ref;
	}

	@Override
	public final Schema getSchema() {
		return this.schema;
	}

	@Override
	public final String toString() {
		return ToStringFormatter.toString(this);
	}
}
