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

import engineer.asyncapi.spyder.model.bindings.ServerBinding;
import engineer.asyncapi.spyder.model.bindings.ServerBindings;

/**
 * 
 * @author johncatlin
 *
 */
final class ServerBindingsImpl implements ServerBindings {

	private final Map<String, ServerBinding> delegate = new LinkedHashMap<>();

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
	public Set<Entry<String, ServerBinding>> entrySet() {
		return delegate.entrySet();
	}

	@Override
	public ServerBinding get(Object key) {
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
	public ServerBinding put(String key, ServerBinding value) {
		return delegate.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends ServerBinding> m) {
		delegate.putAll(m);
	}

	@Override
	public ServerBinding remove(Object key) {
		return delegate.remove(key);
	}

	@Override
	public int size() {
		return delegate.size();
	}

	@Override
	public final String toString() {
		return ToStringFormatter.toString(this);
	}

	@Override
	public Collection<ServerBinding> values() {
		return delegate.values();
	}
}
