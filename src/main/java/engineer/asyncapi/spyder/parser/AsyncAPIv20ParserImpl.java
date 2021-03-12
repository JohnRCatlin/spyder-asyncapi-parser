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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.AsyncAPI;
import engineer.asyncapi.spyder.model.Channels;
import engineer.asyncapi.spyder.model.Components;
import engineer.asyncapi.spyder.model.Identifier;
import engineer.asyncapi.spyder.model.Info;
import engineer.asyncapi.spyder.model.Servers;
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
class AsyncAPIv20ParserImpl extends AsyncAPICommonObjectParser implements AsyncAPIParser {

	private static final boolean isVersion2dot0(final ObjectNode node) {
		final String value = parseAsyncAPISpecVersion(node);
		if (null == value) {
			return false;
		}
		return value.startsWith("2.0");
	}

	private static ObjectMapper mapperInstance(final String apiAsString) {
		if (apiAsString.trim().startsWith("{")) {
			return ObjectMapperFactory.forJson();
		}
		return ObjectMapperFactory.forYaml();
	}

	private static final AsyncAPI parse(final ObjectNode rootNode) {
		final AsyncAPIImpl.Builder builder = new AsyncAPIImpl.Builder();
		try {
			if (isObjectNode(rootNode) && isVersion2dot0(rootNode)) {
				builder.asyncapi(parseAsyncAPISpecVersion(rootNode));
				builder.defaultContentType(parseDefaultContentType(rootNode));
				builder.id(parseIdentifier(rootNode));
				builder.info(parseInfo(rootNode));
				builder.tags(parseTags(rootNode));
				builder.externalDocs(parseExternalDocs(rootNode));
				builder.extensions(parseExtensions(rootNode));
				builder.channels(parseChannels(rootNode));
				builder.servers(parseServers(rootNode));
				builder.components(parseComponents(rootNode));
			}
		} catch (Exception e) {
			return builder.build();
		}
		return builder.build();
	}

	static final String parseAsyncAPISpecVersion(final ObjectNode node) {
		final String value = valueOfKeyOrNull(Fields.ASYNCAPI.value, node);
		if (null != value) {
			return value;
		}
		return null;
	}

	static final Channels parseChannels(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		final ObjectNode obj = objectNodeFrom(Fields.CHANNELS.value, node);
		if (obj != null) {
			return ChannelsParser.parse(obj);
		}
		return null;
	}

	static final Components parseComponents(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		ObjectNode obj = objectNodeFrom(Fields.COMPONENTS.value, node);
		if (obj != null) {
			return ComponentsParser.parse(obj);
		}
		return null;
	}

	static final String parseDefaultContentType(final ObjectNode node) {
		return valueOfKeyOrNull(Fields.DEFAULT_CONTENT_TYPE.value, node);
	}

	static final Identifier parseIdentifier(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		final String value = valueOfKeyOrNull(Fields.ID.value, node);
		if (value != null) {
			return new IdentifierImpl.Builder().id(value).build();
		}
		return null;
	}

	static final Info parseInfo(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		final ObjectNode obj = objectNodeFrom(Fields.INFO.value, node);
		if (obj != null) {
			return InfoParser.parse(obj);
		}
		return null;
	}

	static final Servers parseServers(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		ObjectNode obj = objectNodeFrom(Fields.SERVERS.value, node);
		if (obj != null) {
			return ServersParser.parse(obj);
		}
		return null;
	}

	@Override
	public final AsyncAPI parseFrom(final String urlOrFileOrRawString) {
		AsyncAPI result = null;
		if (null != (result = parseFromString(urlOrFileOrRawString))) {
			return result;
		}
		if (null != (result = parseFromFile(urlOrFileOrRawString))) {
			return result;
		}
		if (null != (result = parseFromUrl(urlOrFileOrRawString))) {
			return result;
		}
		return null;
	}

	@Override
	public final AsyncAPI parseFromFile(final String fileLocation) {
		try {
			final FileReader reader = new FileReader();
			final String rawModel = reader.fromLocation(fileLocation);
			return parseFromString(rawModel);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public final AsyncAPI parseFromString(final String documentContents) {
		try {
			final ObjectMapper mapper = mapperInstance(documentContents);
			final JsonNode rootNode = mapper.readTree(documentContents);
			return parse((ObjectNode) rootNode);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public final AsyncAPI parseFromUrl(final String url) {
		try {
			final URLReader reader = new URLReader();
			final String rawModel = reader.fromLocation(url);
			return parseFromString(rawModel);
		} catch (Exception e) {
			return null;
		}
	}
	
}