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

import engineer.asyncapi.spyder.model.Channel;
import engineer.asyncapi.spyder.model.Channels;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author johncatlin
 *
 */
class ChannelsImpl implements Channels {

  private final Map<String, Channel> delegate = new LinkedHashMap<>();

  @Override
  public final void clear() {
    delegate.clear();
  }

  @Override
  public final boolean containsKey(final Object key) {
    return delegate.containsKey(key);
  }

  @Override
  public final boolean containsValue(final Object value) {
    return delegate.containsValue(value);
  }

  @Override
  public final Set<Entry<String, Channel>> entrySet() {
    return delegate.entrySet();
  }

  @Override
  public final Channel get(final Object key) {
    return delegate.get(key);
  }

  @Override
  public final boolean isEmpty() {
    return delegate.isEmpty();
  }

  @Override
  public final Set<String> keySet() {
    return delegate.keySet();
  }

  @Override
  public final Channel put(final String key, final Channel value) {
    return delegate.put(key, value);
  }

  @Override
  public final void putAll(final Map<? extends String, ? extends Channel> m) {
    delegate.putAll(m);
  }

  @Override
  public final Channel remove(final Object key) {
    return delegate.remove(key);
  }

  @Override
  public final int size() {
    return delegate.size();
  }

  @Override
  public final String toString() {
    return ToStringFormatter.toString(this);
  }

  @Override
  public final Collection<Channel> values() {
    return delegate.values();
  }
}
