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
import engineer.asyncapi.spyder.model.bindings.ServerBinding;
import engineer.asyncapi.spyder.model.bindings.ServerBindings;

/**
 * 
 * @author johncatlin
 *
 */
final class ServerBindingsParser extends AsyncAPICommonObjectParser {

	static final Logger log = LoggerFactory.getLogger(ServerBindingsParser.class);

	static final ServerBindings parse(final ObjectNode obj) {
		if (obj == null) {
			return null;
		}
		final ServerBindings serverBindings = new ServerBindingsImpl();
		final Set<String> keys = keySetFrom(obj);
		for (final String key : keys) {
			final JsonNode bindingNode = obj.get(key);
			if (isObjectNode(bindingNode)) {
				final ServerBinding serverBinding = parse(key, (ObjectNode) bindingNode);
				if (serverBinding != null) {
					serverBindings.put(key, serverBinding);
				}
			}
		}
		return serverBindings;
	}

	static ServerBinding parse(final String type, final ObjectNode node) {
		switch (BindingType.getType(type)) {
		case KAFKA:
			return KafkaServerBindingParser.parse(node);
		case AMQP:
			return AMQP091ServerBindingParser.parse(node);
		case HTTP:
		case WEBSOCKETS:
		case AMQP1:
		case MQTT:
		case MQTT5:
		case NATS:
		case IBMMQ:
		case JMS:
		case SNS:
		case SQS:
		case STOMP:
		case REDIS:
		case MERCURE:
			log.warn(ParseMessages.SERVER_BINDINGS_PARSER_NOT_IMPLEMENTED.message, type);
			return null;
		default:
			return null;
		}
	}

	private ServerBindingsParser() {
		/* this static utility should not be instantiated */
	}
}