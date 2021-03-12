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
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import engineer.asyncapi.spyder.model.AsyncAPI;
import engineer.asyncapi.spyder.model.Schema;

public class TestSchemaFragments extends AsyncApiV2ParserTestBase {

	private static final String FRAGMENT_1 = "/src/test/resources/fragments/schema.1.yml";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSchema1() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FRAGMENT_1);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNotNull(api.getComponents());
		assertNotNull(api.getComponents().getSchemas());
		assertEquals(1, api.getComponents().getSchemas().size());
		assertTrue(api.getComponents().getSchemas().containsKey("test"));

		Schema test = api.getComponents().getSchemas().get("test");
		assertEquals("object", test.getType());
		assertEquals(1, test.getRequiredProperties().size());
		assertEquals("name", test.getRequiredProperties().get(0));
		assertNotNull(test.getProperties());
		assertEquals(3, test.getProperties().size());
		assertTrue(test.getProperties().containsKey("name"));
		assertTrue(test.getProperties().containsKey("address"));
		assertTrue(test.getProperties().containsKey("age"));
		assertNotNull(test.getExtensions());

		Schema name = test.getProperties().get("name");
		assertEquals("string", name.getType());

		Schema address = test.getProperties().get("address");
		assertEquals("#/components/schemas/Address", address.getRef());

		Schema age = test.getProperties().get("age");
		assertEquals("integer", age.getType());
		assertEquals("int32", age.getFormat());
		assertEquals(0, age.getMinimum().intValue());
	}

}
