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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import engineer.asyncapi.spyder.model.SecurityRequirement;

/**
 * 
 * @author johncatlin
 *
 */
final class SecurityRequirementImpl implements SecurityRequirement {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		SecurityRequirementImpl securityRequirement = new SecurityRequirementImpl();

		final Builder add(final String item) {
			securityRequirement.add(item);
			return this;
		}

		final Builder addAll(final List<String> item) {
			securityRequirement.addAll(item);
			return this;
		}

		final SecurityRequirementImpl build() {
			return securityRequirement;
		}

		final Builder name(final String name) {
			securityRequirement.name = name;
			return this;
		}
	}

	private final LinkedList<String> delegate = new LinkedList<>();
	private String name = null;

	private SecurityRequirementImpl() {
		/* Use the builder for construction. */
	}

	@Override
	public void add(int index, String element) {
		delegate.add(index, element);
	}

	@Override
	public boolean add(String e) {
		return delegate.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends String> c) {
		return delegate.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends String> c) {
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
	public String get(int index) {
		return delegate.get(index);
	}

	@Override
	public String getName() {
		return this.name;
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
	public Iterator<String> iterator() {
		return delegate.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return delegate.lastIndexOf(o);
	}

	@Override
	public ListIterator<String> listIterator() {
		return delegate.listIterator();
	}

	@Override
	public ListIterator<String> listIterator(int index) {
		return delegate.listIterator(index);
	}

	@Override
	public String remove(int index) {
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
	public String set(int index, String element) {
		return delegate.set(index, element);
	}

	@Override
	public int size() {
		return delegate.size();
	}

	@Override
	public List<String> subList(int fromIndex, int toIndex) {
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
	public String toString() {
		return ToStringFormatter.toString(this);
	}
}
