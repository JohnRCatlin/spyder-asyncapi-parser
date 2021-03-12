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

import engineer.asyncapi.spyder.model.ServerVariable;
import engineer.asyncapi.spyder.model.ServerVariables;

/**
 * 
 * @author johncatlin
 *
 */
class ServerVariablesImpl implements ServerVariables {

	private final Map<String, ServerVariable> delegate = new LinkedHashMap<>();

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
	public Set<Entry<String, ServerVariable>> entrySet() {
		return delegate.entrySet();
	}

	@Override
	public ServerVariable get(Object key) {
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
	public ServerVariable put(String key, ServerVariable value) {
		return delegate.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends ServerVariable> m) {
		delegate.putAll(m);
	}

	@Override
	public ServerVariable remove(Object key) {
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
	public Collection<ServerVariable> values() {
		return delegate.values();
	}

}
