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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * 
 * @author johncatlin
 *
 */
final class ObjectMapperFactory {

	private static final ObjectMapper create(final JsonFactory jsonFactory) {
		final ObjectMapper mapper = new ObjectMapper(jsonFactory);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper;
	}

	public static final ObjectMapper forJson() {
		JsonFactory factory = new JsonFactoryBuilder().enable(StreamReadFeature.STRICT_DUPLICATE_DETECTION).build();
		return create(factory);
	}

	public static final ObjectMapper forYaml() {
		JsonFactory factory = YAMLFactory.builder().enable(StreamReadFeature.STRICT_DUPLICATE_DETECTION).build();
		return create(factory);
	}

	private ObjectMapperFactory() {
		/* Use the builder for construction. */
	}
}
