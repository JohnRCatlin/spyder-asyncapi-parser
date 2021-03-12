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
import engineer.asyncapi.spyder.model.Examples;
import engineer.asyncapi.spyder.model.Extensions;
import engineer.asyncapi.spyder.model.ExternalDocs;
import engineer.asyncapi.spyder.model.MessageTrait;
import engineer.asyncapi.spyder.model.Schema;
import engineer.asyncapi.spyder.model.Tags;
import engineer.asyncapi.spyder.model.bindings.MessageBindings;

/**
 * 
 * @author johncatlin
 *
 */
class MessageTraitImpl implements MessageTrait {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private static final String REF_BASE = "#/components/messages/messageTraits/";
		private MessageTraitImpl trait = new MessageTraitImpl();

		final Builder bindings(final MessageBindings bindings) {
			trait.bindings = bindings;
			return this;
		}

		final MessageTraitImpl build() {
			return trait;
		}

		final Builder contentType(final String contentType) {
			trait.contentType = contentType;
			return this;
		}

		final Builder correlationId(final CorrelationId correlationId) {
			trait.correlationId = correlationId;
			return this;
		}

		final Builder description(final String description) {
			trait.description = description;
			return this;
		}

		final Builder examlpes(final Examples examples) {
			trait.examples = examples;
			return this;
		}

		final Builder extensions(final Extensions extensions) {
			trait.extensions = extensions;
			return this;
		}

		final Builder externalDocs(final ExternalDocs externalDocs) {
			trait.externalDocs = externalDocs;
			return this;
		}

		final Builder headers(final Schema schema2) {
			trait.headers = schema2;
			return this;
		}

		final Builder name(final String name) {
			trait.name = name;
			return this;
		}

		final Builder ref(String ref) {
			trait.ref = (ref != null && RefUtility.isRelative(ref)) ? REF_BASE + ref : ref;
			return this;
		}

		final Builder schemaFormat(final String schemaFormat) {
			trait.schemaFormat = schemaFormat;
			return this;
		}

		final Builder summary(final String summary) {
			trait.summary = summary;
			return this;
		}

		final Builder tags(final Tags tags) {
			trait.tags = tags;
			return this;
		}

		final Builder title(final String title) {
			trait.title = title;
			return this;
		}

	}

	private MessageBindings bindings = null;
	private String contentType = null;
	private CorrelationId correlationId = null;
	private String description = null;
	private Examples examples = null;
	private Extensions extensions = null;
	private ExternalDocs externalDocs = null;
	private Schema headers = null;
	private String name = null;
	private String ref = null;
	private String schemaFormat = null;
	private String summary = null;
	private Tags tags = null;

	private String title = null;

	private MessageTraitImpl() {
		/* Use the builder for construction. */
	}

	@Override
	public MessageBindings getBindings() {
		return this.bindings;
	}

	@Override
	public String getContentType() {
		return this.contentType;
	}

	@Override
	public CorrelationId getCorrelationId() {
		return this.correlationId;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public Examples getExamples() {
		return this.examples;
	}

	@Override
	public Extensions getExtensions() {
		return this.extensions;
	}

	@Override
	public ExternalDocs getExternalDocs() {
		return this.externalDocs;
	}

	@Override
	public Schema getHeaders() {
		return this.headers;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getRef() {
		return this.ref;
	}

	@Override
	public String getSchemaFormat() {
		return this.schemaFormat;
	}

	@Override
	public String getSummary() {
		return this.summary;
	}

	@Override
	public Tags getTags() {
		return this.tags;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public String toString() {
		return ToStringFormatter.toString(this);
	}

}
