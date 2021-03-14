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

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.bindings.BindingType;
import engineer.asyncapi.spyder.model.bindings.ChannelBinding;
import engineer.asyncapi.spyder.model.bindings.ChannelBindings;

/**
 * 
 * @author johncatlin
 *
 */
final class ChannelBindingsParser extends AsyncAPICommonObjectParser {

	static final Logger log = LoggerFactory.getLogger(ChannelBindingsParser.class);

	static final ChannelBindings parse(final ObjectNode node) {
		if (node == null) {
			return null;
		}
		final ChannelBindings bindings = new ChannelBindingsImpl();
		final Set<String> keys = keySetFrom(node);
		for (final String key : keys) {
			final JsonNode bindingNode = node.get(key);
			if (isObjectNode(bindingNode)) {
				final ChannelBinding binding = parse(key, (ObjectNode) bindingNode);
				if (binding != null) {
					bindings.put(key, binding);
				}
			}
		}
		return bindings;
	}

	static final ChannelBinding parse(final String type, final ObjectNode node) {
		switch (BindingType.getType(type)) {
		case KAFKA:
			return KafkaChannelBindingParser.parse(node);
		case AMQP:
			return AMQP091ChannelBindingParser.parse(node);
		case WEBSOCKETS:
			return WebSocketsChannelBindingParser.parse(node);
		case IBMMQ:
			return IBMMQChannelBindingParser.parse(node);
		case HTTP:
		case AMQP1:
		case MQTT:
		case MQTT5:
		case NATS:
		case JMS:
		case SNS:
		case SQS:
		case STOMP:
		case REDIS:
		case MERCURE:
			log.warn(ParseMessages.CHANNEL_BINDINGS_PARSER_NOT_IMPLEMENTED.message, type);
			return null;
		default:
			return null;
		}
	}

	private ChannelBindingsParser() {
		/* this static utility should not be instantiated */
	}
}