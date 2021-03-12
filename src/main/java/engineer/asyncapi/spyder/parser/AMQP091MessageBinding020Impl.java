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
import engineer.asyncapi.spyder.model.bindings.AMQP091MessageBinding020;
import engineer.asyncapi.spyder.model.bindings.BindingType;

/**
 * 
 * @author johncatlin
 *
 */
final class AMQP091MessageBinding020Impl implements AMQP091MessageBinding020 {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private AMQP091MessageBinding020Impl binding = new AMQP091MessageBinding020Impl();

		public final AMQP091MessageBinding020 build() {
			return binding;
		}

		public final Builder contentEncoding(final String contentEncoding) {
			binding.contentEncoding = contentEncoding;
			return this;
		}

		final Builder extensions(final Extensions extensions) {
			binding.extensions = extensions;
			return this;
		}

		public final Builder messageType(final String messageType) {
			binding.messageType = messageType;
			return this;
		}

	}

	static final String BINDING_VERSION = "0.2.0";
	static final String TYPE = BindingType.AMQP.value;
	private String contentEncoding = null;
	private Extensions extensions = null;
	private String messageType = null;

	private AMQP091MessageBinding020Impl() {
		/* Use the builder for construction. */
	}

	@Override
	public final String getBindingVersion() {
		return BINDING_VERSION;
	}

	@Override
	public final String getContentEncoding() {
		return this.contentEncoding;
	}

	@Override
	public final Extensions getExtensions() {
		return this.extensions;
	}

	@Override
	public final String getMessageType() {
		return this.messageType;
	}

	@Override
	public final String getBindingType() {
		return TYPE;
	}

	@Override
	public final String toString() {
		return ToStringFormatter.toString(this);
	}

}
