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
import engineer.asyncapi.spyder.model.security.OAuthFlow;
import engineer.asyncapi.spyder.model.security.OAuthFlows;

/**
 * 
 * @author johncatlin
 *
 */
final class OAuthFlowsImpl implements OAuthFlows {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private OAuthFlowsImpl oAuthFlows = new OAuthFlowsImpl();

		void addExtension(final String name, final String value) {
			if (ExtensionsParser.notValidExtension(name)) {
				return;
			}
			if (oAuthFlows.extensions == null) {
				oAuthFlows.extensions = new ExtensionsImpl();
			}
			oAuthFlows.extensions.put(name, value);
		}

		final Builder authorizationCode(final OAuthFlow authorizationCode) {
			oAuthFlows.authorizationCode = authorizationCode;
			return this;
		}

		final OAuthFlows build() {
			return oAuthFlows;
		}

		final Builder clientCredentials(final OAuthFlow clientCredentials) {
			oAuthFlows.clientCredentials = clientCredentials;
			return this;
		}

		final Builder extensions(final Extensions extensions) {
			oAuthFlows.extensions = extensions;
			return this;
		}

		final Builder implicit(final OAuthFlow implicit) {
			oAuthFlows.implicit = implicit;
			return this;
		}

		final Builder password(final OAuthFlow password) {
			oAuthFlows.password = password;
			return this;
		}
	}

	private OAuthFlow authorizationCode = null;
	private OAuthFlow clientCredentials = null;
	private Extensions extensions = null;
	private OAuthFlow implicit = null;
	private OAuthFlow password = null;

	private OAuthFlowsImpl() {
		/* Use the builder for construction. */
	}

	@Override
	public OAuthFlow getAuthorizationCode() {
		return this.authorizationCode;
	}

	@Override
	public OAuthFlow getClientCredentials() {
		return this.clientCredentials;
	}

	@Override
	public Extensions getExtensions() {
		return this.extensions;
	}

	@Override
	public OAuthFlow getImplicit() {
		return this.implicit;
	}

	@Override
	public OAuthFlow getPassword() {
		return this.password;
	}

	@Override
	public String toString() {
		return ToStringFormatter.toString(this);
	}

}
