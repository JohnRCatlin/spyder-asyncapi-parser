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
import engineer.asyncapi.spyder.model.Schema;
import engineer.asyncapi.spyder.model.bindings.BindingType;
import engineer.asyncapi.spyder.model.bindings.WebSocketsChannelBinding010;

/**
 * 
 * @author johncatlin
 *
 */
final class WebSocketsChannelBinding010Impl implements WebSocketsChannelBinding010 {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private WebSocketsChannelBinding010Impl binding = new WebSocketsChannelBinding010Impl();

		public final WebSocketsChannelBinding010Impl build() {
			return binding;
		}

		final Builder extensions(final Extensions extensions) {
			binding.extensions = extensions;
			return this;
		}

		final Builder headers(final Schema headers) {
			binding.headers = headers;
			return this;
		}

		final Builder method(final String method) {
			binding.method = method;
			return this;
		}

		final Builder query(final Schema query) {
			binding.query = query;
			return this;
		}
	}

	static final String BINDING_VERSION = "0.1.0";
	static final String TYPE = BindingType.WEBSOCKETS.value;

	private Extensions extensions = null;
	private Schema headers;
	private String method;
	private Schema query;

	private WebSocketsChannelBinding010Impl() {
		/* Use the builder for construction. */
	}

	@Override
	public final String getBindingType() {
		return TYPE;
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
	public Schema getHeaders() {
		return this.headers;
	}

	@Override
	public String getMethod() {
		return this.method;
	}

	@Override
	public Schema getQuery() {
		return this.query;
	}

	@Override
	public final String toString() {
		return ToStringFormatter.toString(this);
	}

}
