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

import engineer.asyncapi.spyder.model.Contact;
import engineer.asyncapi.spyder.model.Extensions;

/**
 * 
 * @author johncatlin
 *
 */
final class ContactImpl implements Contact {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private ContactImpl contact = new ContactImpl();

		public final Contact build() {
			return contact;
		}

		public final Builder email(final String email) {
			contact.email = email;
			return this;
		}

		public final Builder extension(final String name, final String value) {
			if (ExtensionsParser.notValidExtension(name)) {
				return this;
			}
			contact.extensions.put(name, value);
			return this;
		}

		public final Builder extensions(final Extensions extensions) {
			contact.extensions = extensions;
			return this;
		}

		public final Builder name(final String name) {
			contact.name = name;
			return this;
		}

		public final Builder url(final String url) {
			contact.url = url;
			return this;
		}
	}

	private String email = null;
	private Extensions extensions = null;
	private String name = null;
	private String url = null;

	private ContactImpl() {
		/* Use the builder for construction. */
	}

	@Override
	public final String getEmail() {
		return email;
	}

	@Override
	public final Extensions getExtensions() {
		return this.extensions;
	}

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final String getUrl() {
		return url;
	}

	@Override
	public final String toString() {
		return ToStringFormatter.toString(this);
	}
}
