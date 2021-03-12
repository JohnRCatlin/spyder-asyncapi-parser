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

/**
 * @author johncatlin
 *
 */
public class TestParseInfoFragments extends AsyncApiV2ParserTestBase {

	private static final String BROKEN = "/src/test/resources/fragments/info.broken.yml";
	private static final String EMPTY = "/src/test/resources/fragments/info.empty.yml";
	private static final String FULL = "/src/test/resources/fragments/info.full.yml";
	private static final String MISSING = "/src/test/resources/fragments/info.missing.yml";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBroke() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + BROKEN);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNotNull(api.getInfo());
		assertNull(api.getInfo().getTitle());
		assertNull(api.getInfo().getVersion());
		assertNull(api.getInfo().getDescription());
		assertNull(api.getInfo().getTermsOfService());
		assertNotNull(api.getInfo().getLicense());
		assertNotNull(api.getInfo().getLicense().getExtensions());
		assertNull(api.getInfo().getLicense().getExtensions().get("x-a"));
		assertNull(api.getInfo().getLicense().getExtensions().get("x-b"));
		assertNotNull(api.getInfo().getContact());
		assertNotNull(api.getInfo().getContact().getExtensions());
		assertNull(api.getInfo().getContact().getExtensions().get("x-c"));
		assertNull(api.getInfo().getContact().getExtensions().get("x-d"));
		assertNotNull(api.getInfo().getExtensions());
		assertNull(api.getInfo().getExtensions().get("x-e"));
		assertNull(api.getInfo().getExtensions().get("x-f"));
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
	}

	@Test
	public void testFull() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FULL);

		// then...
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());

		// title
		assertNotNull(api.getInfo());
		assertNotNull(api.getInfo().getTitle());
		assertEquals("example api", api.getInfo().getTitle());

		// version
		assertNotNull(api.getInfo().getVersion());
		assertEquals("1.0.0", api.getInfo().getVersion());

		// description
		assertNotNull(api.getInfo().getDescription());
		assertTrue(api.getInfo().getDescription().contains("description text..."));

		// terms of service
		assertNotNull(api.getInfo().getTermsOfService());
		assertTrue(api.getInfo().getTermsOfService()
				.contains("A URL to the Terms of Service for the API. MUST be in the format of a URL."));

		// extensions
		assertNotNull(api.getInfo().getExtensions());
		assertEquals(2, api.getInfo().getExtensions().size());
		assertEquals("x-e-value", api.getInfo().getExtensions().get("x-e"));
		assertEquals("x-f-value", api.getInfo().getExtensions().get("x-f"));

		// license
		assertNotNull(api.getInfo().getLicense());
		assertNotNull(api.getInfo().getLicense().getName());
		assertNotNull(api.getInfo().getLicense().getUrl());

		// extensions
		assertNotNull(api.getInfo().getLicense().getExtensions());
		assertEquals(2, api.getInfo().getLicense().getExtensions().size());
		assertEquals("x-a-value", api.getInfo().getLicense().getExtensions().get("x-a"));
		assertEquals("x-b-value", api.getInfo().getLicense().getExtensions().get("x-b"));

		// name
		assertNotNull(api.getInfo().getContact());
		assertNotNull(api.getInfo().getContact().getName());
		assertEquals("john", api.getInfo().getContact().getName());

		// url
		assertNotNull(api.getInfo().getContact().getUrl());
		assertEquals("https://example.com", api.getInfo().getContact().getUrl());

		// email
		assertNotNull(api.getInfo().getContact().getEmail());
		assertEquals("no@way.com", api.getInfo().getContact().getEmail());

		// extensions
		assertNotNull(api.getInfo().getContact().getExtensions());
		assertEquals(2, api.getInfo().getContact().getExtensions().size());
		assertEquals("x-c-value", api.getInfo().getContact().getExtensions().get("x-c"));
		assertEquals("x-d-value", api.getInfo().getContact().getExtensions().get("x-d"));

	}

	@Test
	public void testMissingInfoParse() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + MISSING);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNull(api.getInfo());
	}
}
