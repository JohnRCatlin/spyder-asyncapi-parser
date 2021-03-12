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
import engineer.asyncapi.spyder.model.SecurityScheme;
import engineer.asyncapi.spyder.model.security.OAuthFlows;

/**
 * 
 * @author johncatlin
 *
 */
final class SecuritySchemeImpl implements SecurityScheme {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private static final String REF_BASE = "#/components/securityschemes/";
		private SecuritySchemeImpl securityScheme = new SecuritySchemeImpl();

		final Builder bearerFormat(final String bearerFormat) {
			securityScheme.bearerFormat = bearerFormat;
			return this;
		}

		final SecurityScheme build() {
			return securityScheme;
		}

		final Builder description(final String description) {
			securityScheme.description = description;
			return this;
		}

		final Builder extension(final String name, final String value) {
			if (ExtensionsParser.notValidExtension(name)) {
				return this;
			}
			if (securityScheme.extensions == null) {
				securityScheme.extensions = new ExtensionsImpl();
			}
			securityScheme.extensions.put(name, value);
			return this;
		}

		final Builder extensions(final Extensions extensions) {
			securityScheme.extensions = extensions;
			return this;
		}

		final Builder flows(final OAuthFlows flows) {
			securityScheme.flows = flows;
			return this;
		}

		final Builder in(final String in) {
			securityScheme.in = in;
			return this;
		}

		final Builder name(final String name) {
			securityScheme.name = name;
			return this;
		}

		final Builder openIdConnectUrl(final String openIdConnectUrl) {
			securityScheme.openIdConnectUrl = openIdConnectUrl;
			return this;
		}

		final Builder ref(final String ref) {
			securityScheme.ref = (ref != null && RefUtility.isRelative(ref)) ? REF_BASE + ref : ref;
			return this;
		}

		final Builder scheme(final String scheme) {
			securityScheme.scheme = scheme;
			return this;
		}

		final Builder type(final String type) {
			securityScheme.type = type;
			return this;
		}

	}

	private String bearerFormat = null;
	private String description = null;
	private Extensions extensions = null;
	private OAuthFlows flows = null;
	private String in = null;
	private String name = null;
	private String openIdConnectUrl = null;
	private String ref = null;
	private String scheme = null;
	private String type = null;

	private SecuritySchemeImpl() {
		/* Use the builder for construction. */
	}

	@Override
	public String getBearerFormat() {
		return this.bearerFormat;
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
	public OAuthFlows getFlows() {
		return this.flows;
	}

	@Override
	public String getIn() {
		return this.in;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getOpenIdConnectUrl() {
		return this.openIdConnectUrl;
	}

	@Override
	public String getRef() {
		return this.ref;
	}

	@Override
	public String getScheme() {
		return this.scheme;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String toString() {
		return ToStringFormatter.toString(this);
	}
}
