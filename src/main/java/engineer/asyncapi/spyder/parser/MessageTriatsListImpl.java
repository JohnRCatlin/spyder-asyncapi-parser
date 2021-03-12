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
	public void add(int index, MessageTrait element) {
		delegate.add(index, element);
	}

	@Override
	public boolean add(MessageTrait e) {
		return delegate.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends MessageTrait> c) {
		return delegate.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends MessageTrait> c) {
		return delegate.addAll(index, c);
	}

	@Override
	public void clear() {
		delegate.clear();
	}

	@Override
	public boolean contains(Object o) {
		return delegate.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return delegate.containsAll(c);
	}

	@Override
	public MessageTrait get(int index) {
		return delegate.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return delegate.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	@Override
	public Iterator<MessageTrait> iterator() {
		return delegate.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return delegate.lastIndexOf(o);
	}

	@Override
	public ListIterator<MessageTrait> listIterator() {
		return delegate.listIterator();
	}

	@Override
	public ListIterator<MessageTrait> listIterator(int index) {
		return delegate.listIterator(index);
	}

	@Override
	public MessageTrait remove(int index) {
		return delegate.remove(index);
	}

	@Override
	public boolean remove(Object o) {
		return delegate.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return delegate.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return delegate.retainAll(c);
	}

	@Override
	public MessageTrait set(int index, MessageTrait element) {
		return delegate.set(index, element);
	}

	@Override
	public int size() {
		return delegate.size();
	}

	@Override
	public List<MessageTrait> subList(int fromIndex, int toIndex) {
		return delegate.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return delegate.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return delegate.toArray(a);
	}

	@Override
	public final String toString() {
		return ToStringFormatter.toString(this);
	}
}
