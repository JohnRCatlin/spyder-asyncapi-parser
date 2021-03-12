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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import engineer.asyncapi.spyder.model.AsyncAPI;

public class TestRootFragments extends AsyncApiV2ParserTestBase {

	private static final String BROKEN = "/src/test/resources/fragments/root.broken.yml";
	private static final String EMPTY = "/src/test/resources/fragments/root.empty.yml";
	private static final String PARTIAL = "/src/test/resources/fragments/root.partial.yml";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBroken() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + BROKEN);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNull(api.getInfo());
		assertNull(api.getIdentifier());
		assertNull(api.getServers());
		assertNull(api.getDefaultContentType());
		assertNull(api.getChannels());
		assertNull(api.getTags());
		assertNull(api.getExternalDocs());
		assertNotNull(api.getExtensions());
		assertEquals(2, api.getExtensions().size());
		assertNull(api.getExtensions().get("x-a"));
		assertNull(api.getExtensions().get("x-b"));
	}

	@Test
	public void testEmpty() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + EMPTY);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNull(api.getInfo());
		assertNull(api.getIdentifier());
		assertNull(api.getServers());
		assertNull(api.getDefaultContentType());
		assertNull(api.getChannels());
		assertNull(api.getTags());
		assertNull(api.getExternalDocs());
		assertNull(api.getExtensions());
	}

	@Test
	public void testPartial() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + PARTIAL);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());

		assertNull(api.getInfo());

		assertNotNull(api.getIdentifier());
		assertTrue(api.getIdentifier().getId().contains("urn:com:smartylighting:streetlights:server"));

		assertNull(api.getServers());

		assertNotNull(api.getDefaultContentType());
		assertTrue(api.getDefaultContentType().contains("application/json"));

		assertNull(api.getChannels());

		assertNotNull(api.getTags());
		assertNotNull(api.getTags());
		assertEquals(2, api.getTags().size());
		assertEquals("foo tag name", api.getTags().get(0).getName());
		assertEquals("foo tag description", api.getTags().get(0).getDescription());
		assertEquals("bar tag name", api.getTags().get(1).getName());
		assertEquals("bar tag description", api.getTags().get(1).getDescription());

		assertNotNull(api.getExternalDocs());
		assertNotNull(api.getExternalDocs().getDescription());
		assertEquals("Find more info here", api.getExternalDocs().getDescription());
		assertNotNull(api.getExternalDocs().getUrl());
		assertEquals("https://example.com", api.getExternalDocs().getUrl());
		assertNotNull(api.getExternalDocs().getExtensions());
		assertEquals(2, api.getExternalDocs().getExtensions().size());
		assertNotNull(api.getExternalDocs().getExtensions().get("x-a"));
		assertNotNull(api.getExternalDocs().getExtensions().get("x-b"));
		assertEquals("x-a-value", api.getExternalDocs().getExtensions().get("x-a"));
		assertEquals("x-b-value", api.getExternalDocs().getExtensions().get("x-b"));

		assertNotNull(api.getExtensions());
		assertEquals(2, api.getExtensions().size());
		assertNotNull(api.getExtensions().get("x-c"));
		assertNotNull(api.getExtensions().get("x-d"));
		assertEquals("x-c-value", api.getExtensions().get("x-c"));
		assertEquals("x-d-value", api.getExtensions().get("x-d"));
	}
}
