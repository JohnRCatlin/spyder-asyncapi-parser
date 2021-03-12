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
import engineer.asyncapi.spyder.model.ExternalDocs;

/**
 * 
 * @author johncatlin
 *
 */
final class ExternalDocsImpl implements ExternalDocs {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private ExternalDocsImpl externalDocumentation = new ExternalDocsImpl();

		public final ExternalDocs build() {
			return this.externalDocumentation;
		}

		public final Builder description(final String description) {
			externalDocumentation.description = description;
			return this;
		}

		public final Builder extensions(final Extensions extensions) {
			externalDocumentation.extensions = extensions;
			return this;
		}

		public final Builder url(final String url) {
			externalDocumentation.url = url;
			return this;
		}
	}
	private String description = null;
	private Extensions extensions = null;

	private String url = null;

	private ExternalDocsImpl() {
		/* Use the builder for construction. */
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public Extensions getExtensions() {
		return this.extensions;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public String getUrl() {
		return this.url;
	}

	@Override
	public String toString() {
		return ToStringFormatter.toString(this);
	}
}
