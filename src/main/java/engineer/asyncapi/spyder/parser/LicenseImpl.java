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
import engineer.asyncapi.spyder.model.License;

/**
 * 
 * @author johncatlin
 *
 */
final class LicenseImpl implements License {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private LicenseImpl license = new LicenseImpl();

		final License build() {
			return license;
		}

		final Builder extensions(final Extensions extensions) {
			license.extensions = extensions;
			return this;
		}

		final Builder name(final String name) {
			license.name = name;
			return this;
		}

		final Builder url(final String url) {
			license.url = url;
			return this;
		}
	}

	private Extensions extensions = null;
	private String name = null;
	private String url = null;

	private LicenseImpl() {
		/* Use the builder for construction. */
	}

	@Override
	public final Extensions getExtensions() {
		return this.extensions;
	}

	@Override
	public final String getName() {
		return this.name;
	}

	@Override
	public final String getUrl() {
		return this.url;
	}

	@Override
	public final String toString() {
		return ToStringFormatter.toString(this);
	}
}
