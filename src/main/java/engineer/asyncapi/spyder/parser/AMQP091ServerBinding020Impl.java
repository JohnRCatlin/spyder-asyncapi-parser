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
import engineer.asyncapi.spyder.model.bindings.AMQP091ServerBinding;
import engineer.asyncapi.spyder.model.bindings.AMQP091ServerBinding020;
import engineer.asyncapi.spyder.model.bindings.BindingType;

/**
 * 
 * @author johncatlin
 *
 */
final class AMQP091ServerBinding020Impl implements AMQP091ServerBinding020 {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private AMQP091ServerBinding020Impl binding = new AMQP091ServerBinding020Impl();

		public final AMQP091ServerBinding build() {
			return binding;
		}

		public void extensions(final Extensions extensions) {
			binding.extensions = extensions;
		}

	}

	static final String BINDING_VERSION = "0.2.0";
	static final String TYPE = BindingType.AMQP.value;

	private Extensions extensions = null;

	@Override
	public final String getBindingVersion() {
		return BINDING_VERSION;
	}

	@Override
	public final Extensions getExtensions() {
		return this.extensions;
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
