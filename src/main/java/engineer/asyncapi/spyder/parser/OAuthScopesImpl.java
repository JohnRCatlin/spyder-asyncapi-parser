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

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import engineer.asyncapi.spyder.model.OAuthScopes;

/**
 * 
 * @author johncatlin
 *
 */
final class OAuthScopesImpl implements OAuthScopes {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private OAuthScopesImpl scopes = new OAuthScopesImpl();

		final Builder addString(final String name, final String item) {
			scopes.put(name, item);
			return this;
		}

		final OAuthScopes build() {
			return scopes;
		}

	}

	private final Map<String, String> delegate = new LinkedHashMap<>();

	private OAuthScopesImpl() {
		/* Use the builder for construction. */
	}

	@Override
	public void clear() {
		delegate.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return delegate.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return delegate.containsValue(value);
	}

	@Override
	public Set<Entry<String, String>> entrySet() {
		return delegate.entrySet();
	}

	@Override
	public String get(Object key) {
		return delegate.get(key);
	}

	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return delegate.keySet();
	}

	@Override
	public String put(String key, String value) {
		return delegate.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends String> m) {
		delegate.putAll(m);
	}

	@Override
	public String remove(Object key) {
		return delegate.remove(key);
	}

	@Override
	public int size() {
		return delegate.size();
	}

	@Override
	public String toString() {
		return ToStringFormatter.toString(this);
	}

	@Override
	public Collection<String> values() {
		return delegate.values();
	}
}
