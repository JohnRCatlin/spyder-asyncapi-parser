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
import engineer.asyncapi.spyder.model.OAuthScopes;
import engineer.asyncapi.spyder.model.security.OAuthFlow;

/**
 * 
 * @author johncatlin
 *
 */
final class OAuthFlowImpl implements OAuthFlow {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private OAuthFlowImpl oAuthFlow = new OAuthFlowImpl();

		final Builder authorizationUrl(final String authorizationUrl) {
			oAuthFlow.authorizationUrl = authorizationUrl;
			return this;
		}

		final OAuthFlow build() {
			return oAuthFlow;
		}

		final Builder extension(final String name, final String value) {
			if (ExtensionsParser.notValidExtension(name)) {
				return this;
			}
			if (oAuthFlow.extensions == null) {
				oAuthFlow.extensions = new ExtensionsImpl();
			}
			oAuthFlow.extensions.put(name, value);
			return this;
		}

		final Builder extensions(final Extensions extensions) {
			oAuthFlow.extensions = extensions;
			return this;
		}

		final Builder refreshUrl(final String refreshUrl) {
			oAuthFlow.refreshUrl = refreshUrl;
			return this;
		}

		final Builder scopes(final OAuthScopes scopes) {
			oAuthFlow.scopes = scopes;
			return this;
		}

		final Builder tokenUrl(final String tokenUrl) {
			oAuthFlow.tokenUrl = tokenUrl;
			return this;
		}
	}

	private String authorizationUrl = null;
	private Extensions extensions = null;
	private String refreshUrl = null;
	private OAuthScopes scopes = null;
	private String tokenUrl = null;

	private OAuthFlowImpl() {
		/* Use the builder for construction. */
	}

	@Override
	public final String getAuthorizationUrl() {
		return this.authorizationUrl;
	}

	@Override
	public final Extensions getExtensions() {
		return this.extensions;
	}

	@Override
	public final String getRefreshUrl() {
		return this.refreshUrl;
	}

	@Override
	public final OAuthScopes getScopes() {
		return this.scopes;
	}

	@Override
	public final String getTokenUrl() {
		return this.tokenUrl;
	}

	@Override
	public final String toString() {
		return ToStringFormatter.toString(this);
	}

}
