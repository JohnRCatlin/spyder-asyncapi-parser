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

import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.bindings.AMQP091ChannelBinding;
import engineer.asyncapi.spyder.model.bindings.AMQP091ChannelBinding020;
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
final class AMQP091ChannelBindingParser extends AsyncAPICommonObjectParser {

	static final AMQP091ChannelBinding parse(final ObjectNode node) {
		try {
			final String bindingVersion = parseBindingVersion(node);
			if (null == bindingVersion || bindingVersion.equals(AMQP091ChannelBinding020Impl.BINDING_VERSION)) {
				return parseBindingV020(node);
			}
			// use latest
			return parseBindingV020(node);
		} catch (Exception e) {
			return null;
		}
	}

	private static final AMQP091ChannelBinding020 parseBindingV020(final ObjectNode node) {
		final AMQP091ChannelBinding020Impl.Builder builder = new AMQP091ChannelBinding020Impl.Builder();
		builder.extensions(parseExtensions(node));
		builder.is(parserIs(node));
		builder.queueName(parserQueueName(node));
		builder.queueDurable(parserQueueDurable(node));
		builder.queueExclusive(parserQueueExclusive(node));
		builder.queueAutoDelete(parserQueueAutoDelete(node));
		builder.queueVHost(parserQueueVhost(node));
		builder.exchangeName(parserExchangeName(node));
		builder.exchangeType(parserExchangeType(node));
		builder.exchangeDurable(parserExchangeDurable(node));
		builder.exchangeAutoDelete(parserExchangeAutoDelete(node));
		builder.exchangeVHost(parserExchangeVhost(node));
		return builder.build();
	}

	private static final Boolean parserExchangeAutoDelete(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		ObjectNode exchange = (ObjectNode) node.get(Fields.EXCHANGE.value);
		if (null == exchange) {
			return null;
		}
		return booleanFrom(Fields.AUTO_DELETE.value, exchange);
	}

	private static final Boolean parserExchangeDurable(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		ObjectNode exchange = (ObjectNode) node.get(Fields.EXCHANGE.value);
		if (null == exchange) {
			return null;
		}
		return booleanFrom(Fields.DURABLE.value, exchange);
	}

	private static final String parserExchangeName(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		ObjectNode exchange = (ObjectNode) node.get(Fields.EXCHANGE.value);
		if (null == exchange) {
			return null;
		}
		return stringFrom(Fields.NAME.value, exchange);
	}

	private static final String parserExchangeType(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		ObjectNode exchange = (ObjectNode) node.get(Fields.EXCHANGE.value);
		if (null == exchange) {
			return null;
		}
		return stringFrom(Fields.TYPE.value, exchange);
	}

	private static final String parserExchangeVhost(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		ObjectNode exchange = (ObjectNode) node.get(Fields.EXCHANGE.value);
		if (null == exchange) {
			return null;
		}
		return stringFrom(Fields.VHOST.value, exchange);
	}

	private static final String parserIs(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		return stringFrom(Fields.IS.value, node);
	}

	private static final Boolean parserQueueAutoDelete(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		ObjectNode queue = (ObjectNode) node.get(Fields.QUEUE.value);
		if (null == queue) {
			return null;
		}
		return booleanFrom(Fields.AUTO_DELETE.value, queue);
	}

	private static final Boolean parserQueueDurable(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		ObjectNode queue = (ObjectNode) node.get(Fields.QUEUE.value);
		if (null == queue) {
			return null;
		}
		return booleanFrom(Fields.DURABLE.value, queue);
	}

	private static final Boolean parserQueueExclusive(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		ObjectNode queue = (ObjectNode) node.get(Fields.QUEUE.value);
		if (null == queue) {
			return null;
		}
		return booleanFrom(Fields.EXCLUSIVE.value, queue);
	}

	private static final String parserQueueName(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		ObjectNode queue = (ObjectNode) node.get(Fields.QUEUE.value);
		if (null == queue) {
			return null;
		}
		return stringFrom(Fields.NAME.value, queue);
	}

	private static final String parserQueueVhost(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		ObjectNode queue = (ObjectNode) node.get(Fields.QUEUE.value);
		if (null == queue) {
			return null;
		}
		return stringFrom(Fields.VHOST.value, queue);
	}

	private AMQP091ChannelBindingParser() {
		/* this static utility should not be instantiated */
	}
}