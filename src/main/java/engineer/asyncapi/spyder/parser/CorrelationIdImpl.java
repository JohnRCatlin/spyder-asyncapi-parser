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

import engineer.asyncapi.spyder.model.CorrelationId;
import engineer.asyncapi.spyder.model.Extensions;

/**
 * 
 * @author johncatlin
 *
 */
final class CorrelationIdImpl implements CorrelationId {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private static final String REF_BASE = "#/components/messages/correlationId/";
		private CorrelationIdImpl correlationId = new CorrelationIdImpl();

		public final CorrelationIdImpl build() {
			return correlationId;
		}

		public final Builder description(final String description) {
			correlationId.description = description;
			return this;
		}

		public final Builder extensions(final Extensions extensions) {
			correlationId.extensions = extensions;
			return this;
		}

		public final Builder location(final String location) {
			correlationId.location = location;
			return this;
		}

		public final Builder ref(String ref) {
			correlationId.ref = (ref != null && RefUtility.isRelative(ref)) ? REF_BASE + ref : ref;
			return this;
		}
	}

	private String description = null;
	private Extensions extensions = null;
	private String location = null;

	private String ref = null;

	private CorrelationIdImpl() {
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
	public String getLocation() {
		return this.location;
	}

	@Override
	public String getRef() {
		return this.ref;
	}

	@Override
	public String toString() {
		return ToStringFormatter.toString(this);
	}
}
