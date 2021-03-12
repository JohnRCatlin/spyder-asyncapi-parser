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

import java.util.Map;

import engineer.asyncapi.spyder.model.Extensions;
import engineer.asyncapi.spyder.model.Security;
import engineer.asyncapi.spyder.model.Server;
import engineer.asyncapi.spyder.model.ServerVariable;
import engineer.asyncapi.spyder.model.bindings.ServerBindings;

/**
 * 
 * @author johncatlin
 *
 */
final class ServerImpl implements Server {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private ServerImpl server = new ServerImpl();

		final Builder bindings(final ServerBindings bindings) {
			server.bindings = bindings;
			return this;
		}

		final ServerImpl build() {
			return server;
		}

		final Builder description(final String description) {
			server.description = description;
			return this;
		}

		final Builder extension(final String name, final String value) {
			if (ExtensionsParser.notValidExtension(name)) {
				return this;
			}
			server.extensions.put(name, value);
			return this;
		}

		final Builder extensions(final Extensions extensions) {
			server.extensions = extensions;
			return this;
		}

		final Builder protocol(final String protocol) {
			server.protocol = protocol;
			return this;
		}

		final Builder protocolVersion(final String protocolVersion) {
			server.protocolVersion = protocolVersion;
			return this;
		}

		final Builder security(final Security security) {
			server.security = security;
			return this;
		}

		final Builder url(final String url) {
			server.url = url;
			return this;
		}

		final Builder variables(final Map<String, ServerVariable> variables) {
			server.variables = variables;
			return this;
		}
	}

	private ServerBindings bindings = null;
	private String description = null;
	private Extensions extensions = null;
	private String protocol = null;
	private String protocolVersion = null;
	private Security security = null;
	private String url = null;

	private Map<String, ServerVariable> variables = null;

	private ServerImpl() {
		/* Use the builder for construction. */
	}

	@Override
	public ServerBindings getBindings() {
		return this.bindings;
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
	public String getProtocol() {
		return this.protocol;
	}

	@Override
	public String getProtocolVersion() {
		return this.protocolVersion;
	}

	@Override
	public Security getSecurity() {
		return this.security;
	}

	@Override
	public String getUrl() {
		return this.url;
	}

	@Override
	public Map<String, ServerVariable> getVariables() {
		return this.variables;
	}

	@Override
	public String toString() {
		return ToStringFormatter.toString(this);
	}
}
