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
import engineer.asyncapi.spyder.model.Operation;

public class TestChannelsFragments extends AsyncApiV2ParserTestBase {

	private static final String BROKEN = "/src/test/resources/fragments/channels.broken.yml";
	private static final String EMPTY = "/src/test/resources/fragments/channels.empty.yml";
	private static final String FULL = "/src/test/resources/fragments/channels.full.yml";

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
		assertNotNull(api.getChannels());
		assertEquals(3, api.getChannels().size());

		assertNotNull(api.getChannels().get("feeds/foo"));
		assertNull(api.getChannels().get("feeds/foo").getRef());
		assertNull(api.getChannels().get("feeds/foo").getDescription());
		assertNull(api.getChannels().get("feeds/foo").getSubscribe());
		assertNull(api.getChannels().get("feeds/foo").getPublish());
		assertNull(api.getChannels().get("feeds/foo").getParameters());
		assertNull(api.getChannels().get("feeds/foo").getBindings());

		assertNotNull(api.getChannels().get("commands/foo"));
		assertNull(api.getChannels().get("commands/foo").getRef());
		assertNull(api.getChannels().get("commands/foo").getDescription());
		assertNull(api.getChannels().get("commands/foo").getSubscribe());
		assertNull(api.getChannels().get("commands/foo").getPublish());
		assertNull(api.getChannels().get("commands/foo").getParameters());
		assertNull(api.getChannels().get("commands/foo").getBindings());

		assertNotNull(api.getChannels().get("events/foo"));
		assertNull(api.getChannels().get("events/foo").getRef());
		assertNull(api.getChannels().get("events/foo").getDescription());
		assertNull(api.getChannels().get("events/foo").getSubscribe());
		assertNull(api.getChannels().get("events/foo").getPublish());
		assertNull(api.getChannels().get("events/foo").getParameters());
		assertNull(api.getChannels().get("events/foo").getBindings());
	}

	@Test
	public void testEmpty() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + EMPTY);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNull(api.getChannels());
	}

	@Test
	public void testFull() {
		// when
		final AsyncAPIParser parser = new AsyncAPIv20ParserImpl();
		final AsyncAPI api = parser.parseFromFile(currentWorkingDirectory() + FULL);

		// then
		assertNotNull(api);
		assertEquals("2.0.0", api.getAsyncapi());
		assertNotNull(api.getChannels());
		assertEquals(2, api.getChannels().size());

		assertNotNull(api.getChannels().get("feeds/foo"));
		assertNotNull(api.getChannels().get("feeds/foo").getRef());
		assertEquals("#/.../feeds/foo", api.getChannels().get("feeds/foo").getRef());
		assertNull(api.getChannels().get("feeds/foo").getDescription());
		assertNull(api.getChannels().get("feeds/foo").getSubscribe());
		assertNull(api.getChannels().get("feeds/foo").getPublish());
		assertNull(api.getChannels().get("feeds/foo").getParameters());
		assertNull(api.getChannels().get("feeds/foo").getBindings());

		assertNotNull(api.getChannels().get("commands/foo"));
		assertNull(api.getChannels().get("commands/foo").getRef());
		assertNotNull(api.getChannels().get("commands/foo").getDescription());
		assertEquals("description value", api.getChannels().get("commands/foo").getDescription());

		assertNotNull(api.getChannels().get("commands/foo").getParameters());
		assertTrue(api.getChannels().get("commands/foo").getParameters().containsKey("foo"));
		assertEquals("#/components/.../ref1", api.getChannels().get("commands/foo").getParameters().get("foo").getRef());

		Operation subscribe = api.getChannels().get("commands/foo").getSubscribe();
		assertEquals("subscribe summary", subscribe.getSummary());
		assertEquals("subscribe description", subscribe.getDescription());
		assertEquals("subscribeFooId", subscribe.getOperationId());

		assertNotNull(subscribe.getTraits());
		assertEquals(1, subscribe.getTraits().size());
		assertEquals("#/components/.../ref2", subscribe.getTraits().get(0).getRef());

		assertNotNull(subscribe.getMessage());
		assertNotNull(subscribe.getMessage().getRef());
		assertEquals("#/components/.../ref3", subscribe.getMessage().getRef());

		assertNull(subscribe.getTags());

		assertNotNull(api.getChannels().get("commands/foo").getPublish());
		Operation publish = api.getChannels().get("commands/foo").getPublish();
		assertEquals("publish summary", publish.getSummary());
		assertEquals("publish description", publish.getDescription());
		assertEquals("publishFooId", publish.getOperationId());

		assertNotNull(publish.getTraits());
		assertEquals(1, publish.getTraits().size());
		assertEquals("#/components/.../ref4", publish.getTraits().get(0).getRef());

		assertNotNull(publish.getMessage());
		assertNotNull(publish.getMessage().getRef());
		assertEquals("#/components/.../ref5", publish.getMessage().getRef());

		assertNull(publish.getTags());
		assertNull(api.getChannels().get("commands/foo").getBindings());
	}
}
