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
import engineer.asyncapi.spyder.model.ExternalDocs;
import engineer.asyncapi.spyder.model.Message;
import engineer.asyncapi.spyder.model.MessageTraitsList;
import engineer.asyncapi.spyder.model.Schema;
import engineer.asyncapi.spyder.model.Tags;
import engineer.asyncapi.spyder.model.bindings.MessageBindings;

/**
 * 
 * @author johncatlin
 *
 */
final class MessageImpl implements Message {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private static final String REF_BASE = "#/components/messages/";
		private MessageImpl message = new MessageImpl();

		final Builder bindings(final MessageBindings bindings) {
			message.bindings = bindings;
			return this;
		}

		final Message build() {
			return message;
		}

		final Builder contentType(final String contentType) {
			message.contentType = contentType;
			return this;
		}

		final Builder correlationId(final CorrelationId correlationId) {
			message.correlationId = correlationId;
			return this;
		}

		final Builder description(final String description) {
			message.description = description;
			return this;
		}

		final Builder extensions(final Extensions extensions) {
			message.extensions = extensions;
			return this;
		}

		final Builder externalDocs(final ExternalDocs externalDocs) {
			message.externalDocs = externalDocs;
			return this;
		}

		final Builder headers(final Schema schema2) {
			message.headers = schema2;
			return this;
		}

		final Builder name(final String name) {
			message.name = name;
			return this;
		}

		final Builder payload(final Schema payload) {
			message.payload = payload;
			return this;
		}

		final Builder ref(final String ref) {
			message.ref = (ref != null && RefUtility.isRelative(ref)) ? REF_BASE + ref : ref;
			return this;
		}

		final Builder schemaFormat(final String schemaFormat) {
			message.schemaFormat = schemaFormat;
			return this;
		}

		final Builder summary(final String summary) {
			message.summary = summary;
			return this;
		}

		final Builder tags(final Tags tags) {
			message.tags = tags;
			return this;
		}

		final Builder title(final String title) {
			message.title = title;
			return this;
		}

		final Builder traits(final MessageTraitsList traits) {
			message.traits = traits;
			return this;
		}
	}

	private MessageBindings bindings = null;
	private String contentType = null;
	private CorrelationId correlationId = null;
	private String description = null;
	private Extensions extensions = null;
	private ExternalDocs externalDocs = null;
	private Schema headers = null;
	private String name = null;
	private Schema payload = null;
	private String ref = null;
	private String schemaFormat = null;
	private String summary = null;
	private Tags tags = null;
	private String title = null;
	private MessageTraitsList traits = null;

	private MessageImpl() {
		/* Use the builder for construction. */
	}

	@Override
	public final MessageBindings getBindings() {
		return this.bindings;
	}

	@Override
	public final String getContentType() {
		return this.contentType;
	}

	@Override
	public final CorrelationId getCorrelationId() {
		return this.correlationId;
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
	public final ExternalDocs getExternalDocs() {
		return this.externalDocs;
	}

	@Override
	public final Schema getHeaders() {
		return this.headers;
	}

	@Override
	public final String getName() {
		return this.name;
	}

	@Override
	public final Schema getPayload() {
		return this.payload;
	}

	@Override
	public final String getRef() {
		return this.ref;
	}

	@Override
	public final String getSchemaFormat() {
		return this.schemaFormat;
	}

	@Override
	public final String getSummary() {
		return this.summary;
	}

	@Override
	public final Tags getTags() {
		return this.tags;
	}

	@Override
	public final String getTitle() {
		return this.title;
	}

	@Override
	public final MessageTraitsList getTraits() {
		return this.traits;
	}

	@Override
	public final String toString() {
		return ToStringFormatter.toString(this);
	}
}
