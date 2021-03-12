package javadoc.samples;

import java.io.File;

import org.junit.Test;

import engineer.asyncapi.spyder.model.AsyncAPI;
import engineer.asyncapi.spyder.parser.AsyncAPIParser;
import engineer.asyncapi.spyder.parser.AsyncAPIParserFactory;
import engineer.asyncapi.spyder.parser.SupportedAsyncAPIVersions;

public class TestDocumentationCodeSamples {

	@Test
	public void fromUrl() {

		final String source = "https://raw.githubusercontent.com/JohnRCatlin/asyncapi-recipes/main/src/fabrics/fabrics.amqp.yml";

		final AsyncAPIParser parser = AsyncAPIParserFactory.create(SupportedAsyncAPIVersions.V2_0_0);
		final AsyncAPI root = parser.parseFromUrl(source);

		assert root.getAsyncapi().equals(SupportedAsyncAPIVersions.V2_0_0.value);
	}

	@Test
	public void fromString() {

		final StringBuilder sb = new StringBuilder();
		sb.append("asyncapi: '2.0.0'\n");
		sb.append("info:\n");
		sb.append("  title: Streetlights API\n");
		sb.append("  version: '1.0.0'\n");
		sb.append("  description: |\n");
		sb.append("    The Smartylighting Streetlights API...\n");
		sb.append("  license:\n");
		sb.append("    name: Apache 2.0\n");
		sb.append("    url: https://www.apache.org/licenses/LICENSE-2.0\n");
		final String source = sb.toString();

		final AsyncAPIParser parser = AsyncAPIParserFactory.create(SupportedAsyncAPIVersions.V2_0_0);
		final AsyncAPI root = parser.parseFromString(source);

		assert root.getAsyncapi().equals(SupportedAsyncAPIVersions.V2_0_0.value);
		assert root.getInfo().getTitle().equals("Streetlights API");
		assert root.getInfo().getLicense().getName().equals("Apache 2.0");
	}

	@Test
	public void fromFile() {

		final String source = new File("").getAbsolutePath() + "/src/test/resources/streetlights2.yml";
		final AsyncAPIParser parser = AsyncAPIParserFactory.create(SupportedAsyncAPIVersions.V2_0_0);
		final AsyncAPI root = parser.parseFromFile(source);

		assert root.getAsyncapi().equals(SupportedAsyncAPIVersions.V2_0_0.value);
	}

}
