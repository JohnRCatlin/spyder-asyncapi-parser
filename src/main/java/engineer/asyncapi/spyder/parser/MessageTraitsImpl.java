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

import engineer.asyncapi.spyder.model.MessageTrait;
import engineer.asyncapi.spyder.model.MessageTraits;

/**
 * 
 * @author johncatlin
 *
 */
class MessageTraitsImpl implements MessageTraits {

	private final Map<String, MessageTrait> delegate = new LinkedHashMap<>();

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
	public Set<Entry<String, MessageTrait>> entrySet() {
		return delegate.entrySet();
	}

	@Override
	public MessageTrait get(Object key) {
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
	public MessageTrait put(String key, MessageTrait value) {
		return delegate.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends MessageTrait> m) {
		delegate.putAll(m);
	}

	@Override
	public MessageTrait remove(Object key) {
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
	public Collection<MessageTrait> values() {
		return delegate.values();
	}

}
