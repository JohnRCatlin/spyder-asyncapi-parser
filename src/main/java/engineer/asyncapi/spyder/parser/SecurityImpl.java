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

import engineer.asyncapi.spyder.model.Security;
import engineer.asyncapi.spyder.model.SecurityRequirement;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 * @author johncatlin
 *
 */
final class SecurityImpl implements Security {

  /**
   * 
   * @author johncatlin
   *
   */
  static final class Builder {

    SecurityImpl securityRequirement = new SecurityImpl();

    final Builder add(final SecurityRequirement item) {
      securityRequirement.add(item);
      return this;
    }

    final Builder addAll(final List<SecurityRequirement> item) {
      securityRequirement.addAll(item);
      return this;
    }

    final SecurityImpl build() {
      return securityRequirement;
    }
  }

  private final List<SecurityRequirement> delegate = new LinkedList<>();

  private SecurityImpl() {
    /* Use the builder for construction. */
  }

  @Override
  public final void add(final int index, final SecurityRequirement element) {
    delegate.add(index, element);
  }

  @Override
  public final boolean add(final SecurityRequirement e) {
    return delegate.add(e);
  }

  @Override
  public final boolean addAll(final Collection<? extends SecurityRequirement> c) {
    return delegate.addAll(c);
  }

  @Override
  public final boolean addAll(final int index, Collection<? extends SecurityRequirement> c) {
    return delegate.addAll(index, c);
  }

  @Override
  public final void clear() {
    delegate.clear();
  }

  @Override
  public final boolean contains(final Object o) {
    return delegate.contains(o);
  }

  @Override
  public final boolean containsAll(final Collection<?> c) {
    return delegate.containsAll(c);
  }

  @Override
  public final SecurityRequirement get(final int index) {
    return delegate.get(index);
  }

  @Override
  public final int indexOf(final Object o) {
    return delegate.indexOf(o);
  }

  @Override
  public final boolean isEmpty() {
    return delegate.isEmpty();
  }

  @Override
  public final Iterator<SecurityRequirement> iterator() {
    return delegate.iterator();
  }

  @Override
  public final int lastIndexOf(final Object o) {
    return delegate.lastIndexOf(o);
  }

  @Override
  public final ListIterator<SecurityRequirement> listIterator() {
    return delegate.listIterator();
  }

  @Override
  public final ListIterator<SecurityRequirement> listIterator(final int index) {
    return delegate.listIterator(index);
  }

  @Override
  public final SecurityRequirement remove(final int index) {
    return delegate.remove(index);
  }

  @Override
  public final boolean remove(Object o) {
    return delegate.remove(o);
  }

  @Override
  public final boolean removeAll(Collection<?> c) {
    return delegate.removeAll(c);
  }

  @Override
  public final boolean retainAll(Collection<?> c) {
    return delegate.retainAll(c);
  }

  @Override
  public final SecurityRequirement set(int index, final SecurityRequirement element) {
    return delegate.set(index, element);
  }

  @Override
  public final int size() {
    return delegate.size();
  }

  @Override
  public final List<SecurityRequirement> subList(final int fromIndex, final int toIndex) {
    return delegate.subList(fromIndex, toIndex);
  }

  @Override
  public final Object[] toArray() {
    return delegate.toArray();
  }

  @Override
  public final <T> T[] toArray(T[] a) {
    return delegate.toArray(a);
  }

  @Override
  public final String toString() {
    return ToStringFormatter.toString(this);
  }

  @Override
  public final boolean containsRequirement(final String requirement) {
    for (final SecurityRequirement req : delegate) {
      if (req.getName().equals(requirement)) {
        return true;
      }
    }
    return false;
  }
}
