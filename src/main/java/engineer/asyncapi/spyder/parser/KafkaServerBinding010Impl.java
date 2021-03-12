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
import engineer.asyncapi.spyder.model.bindings.BindingType;
import engineer.asyncapi.spyder.model.bindings.KafkaServerBinding010;

/**
 * 
 * @author johncatlin
 *
 */
final class KafkaServerBinding010Impl implements KafkaServerBinding010 {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		KafkaServerBinding010Impl binding = new KafkaServerBinding010Impl();

		final KafkaServerBinding010 build() {
			return binding;
		}

		final Builder extensions(final Extensions extensions) {
			binding.extensions = extensions;
			return this;
		}
	}

	static final String BINDING_VERSION = "0.1.0";
	static final String TYPE = BindingType.KAFKA.value;

	private Extensions extensions = null;

	private KafkaServerBinding010Impl() {
		/* Use the builder for construction. */
	}

	@Override
	public final String getBindingVersion() {
		return BINDING_VERSION;
	}

	@Override
	public final Extensions getExtensions() {
		return this.extensions;
	}

	@Override
	public final String getType() {
		return TYPE;
	}

	@Override
	public final String toString() {
		return ToStringFormatter.toString(this);
	}
}
