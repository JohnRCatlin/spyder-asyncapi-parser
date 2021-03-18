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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import engineer.asyncapi.spyder.model.MessageTrait;
import engineer.asyncapi.spyder.model.MessageTraitsList;

/**
 * 
 * @author johncatlin
 *
 */
class MessageTriatsListImpl implements MessageTraitsList {

  private final List<MessageTrait> delegate = new ArrayList<>();

  @Override
  public final void add(final int index, final MessageTrait element) {
    delegate.add(index, element);
  }

  @Override
  public final boolean add(final MessageTrait e) {
    return delegate.add(e);
  }

  @Override
  public final boolean addAll(final Collection<? extends MessageTrait> c) {
    return delegate.addAll(c);
  }

  @Override
  public final boolean addAll(final int index, final Collection<? extends MessageTrait> c) {
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
  public final MessageTrait get(final int index) {
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
  public final Iterator<MessageTrait> iterator() {
    return delegate.iterator();
  }

  @Override
  public final int lastIndexOf(final Object o) {
    return delegate.lastIndexOf(o);
  }

  @Override
  public final ListIterator<MessageTrait> listIterator() {
    return delegate.listIterator();
  }

  @Override
  public final ListIterator<MessageTrait> listIterator(final int index) {
    return delegate.listIterator(index);
  }

  @Override
  public final MessageTrait remove(final int index) {
    return delegate.remove(index);
  }

  @Override
  public final boolean remove(final Object o) {
    return delegate.remove(o);
  }

  @Override
  public final boolean removeAll(final Collection<?> c) {
    return delegate.removeAll(c);
  }

  @Override
  public final boolean retainAll(final Collection<?> c) {
    return delegate.retainAll(c);
  }

  @Override
  public final MessageTrait set(final int index, final MessageTrait element) {
    return delegate.set(index, element);
  }

  @Override
  public final int size() {
    return delegate.size();
  }

  @Override
  public final List<MessageTrait> subList(final int fromIndex, final int toIndex) {
    return delegate.subList(fromIndex, toIndex);
  }

  @Override
  public final Object[] toArray() {
    return delegate.toArray();
  }

  @Override
  public final <T> T[] toArray(final T[] a) {
    return delegate.toArray(a);
  }

  @Override
  public final String toString() {
    return ToStringFormatter.toString(this);
  }
}
