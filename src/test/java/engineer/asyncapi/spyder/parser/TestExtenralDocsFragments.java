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

public class TestExtenralDocsFragments extends AsyncApiV2ParserTestBase {

	private static final String EMPTY = "/src/test/resources/fragments/externalDocs.empty.yml";
	private static final String FULL = "/src/test/resources/fragments/externalDocs.full.yml";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
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
		assertNotNull(api.getExternalDocs());
		assertNull(api.getExternalDocs().getDescription());
		assertNull(api.getExternalDocs().getUrl());
		assertNull(api.getExtensions());
	}

	@Test
	public void testFull() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FULL);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNull(api.getInfo());
		assertNull(api.getIdentifier());
		assertNull(api.getServers());
		assertNull(api.getDefaultContentType());
		assertNull(api.getChannels());
		assertNull(api.getTags());
		assertNotNull(api.getExternalDocs());
		assertNull(api.getExtensions());
		assertNotNull(api.getExternalDocs().getExtensions());
		assertTrue(api.getExternalDocs().getDescription().contains("Find more info here"));
		assertTrue(api.getExternalDocs().getUrl().contains("https://example.com"));
		assertEquals(2, api.getExternalDocs().getExtensions().size());
		assertNotNull(api.getExternalDocs().getExtensions().get("x-a"));
		assertNotNull(api.getExternalDocs().getExtensions().get("x-b"));
		assertEquals("x-a-value", api.getExternalDocs().getExtensions().get("x-a"));
		assertEquals("x-b-value", api.getExternalDocs().getExtensions().get("x-b"));

	}

}
