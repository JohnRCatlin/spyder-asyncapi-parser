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

import engineer.asyncapi.spyder.model.Extensions;
import engineer.asyncapi.spyder.model.bindings.AMQP091ChannelBinding020;
import engineer.asyncapi.spyder.model.bindings.BindingType;

/**
 * 
 * @author johncatlin
 *
 */
final class AMQP091ChannelBinding020Impl implements AMQP091ChannelBinding020 {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private AMQP091ChannelBinding020Impl binding = new AMQP091ChannelBinding020Impl();

		public final AMQP091ChannelBinding020 build() {
			return binding;
		}

		public final Builder exchangeAutoDelete(final boolean exchangeAutoDelete) {
			binding.exchangeAutoDelete = exchangeAutoDelete;
			return this;
		}

		public final Builder exchangeDurable(final boolean exchangeDurable) {
			binding.exchangeDurable = exchangeDurable;
			return this;
		}

		public final Builder exchangeName(final String exchangeName) {
			binding.exchangeName = exchangeName;
			return this;
		}

		public final Builder exchangeType(final String exchangeType) {
			binding.exchangeType = exchangeType;
			return this;
		}

		public final Builder exchangeVHost(final String exchangeVHost) {
			binding.exchangeVHost = exchangeVHost;
			return this;
		}

		final Builder extensions(final Extensions extensions) {
			binding.extensions = extensions;
			return this;
		}

		public final Builder is(final String is) {
			binding.is = is;
			return this;
		}

		public final Builder queueAutoDelete(final boolean queueAutoDelete) {
			binding.queueAutoDelete = queueAutoDelete;
			return this;
		}

		public final Builder queueDurable(final boolean queueDurable) {
			binding.queueDurable = queueDurable;
			return this;
		}

		public final Builder queueExclusive(final boolean queueExclusive) {
			binding.queueExclusive = queueExclusive;
			return this;
		}

		public final Builder queueName(final String queueName) {
			binding.queueName = queueName;
			return this;
		}

		public final Builder queueVHost(final String queueVHost) {
			binding.queueVHost = queueVHost;
			return this;
		}

		public final Builder routingKey(final boolean routingKey) {
			binding.routingKey = routingKey;
			return this;
		}

	}

	static final String BINDING_VERSION = "0.2.0";
	static final String TYPE = BindingType.AMQP.value;

	private boolean exchangeAutoDelete;

	private boolean exchangeDurable;

	private String exchangeName;

	private String exchangeType;

	private String exchangeVHost;

	private Extensions extensions = null;

	private String is;

	private boolean queueAutoDelete;

	private boolean queueDurable;

	private boolean queueExclusive;

	private String queueName;

	private String queueVHost;

	private boolean routingKey;// queue:

	private AMQP091ChannelBinding020Impl() {
		/* Use the builder for construction. */
	}

	@Override
	public final String getBindingVersion() {
		return BINDING_VERSION;
	}

	@Override
	public final String getExchangeName() {
		return exchangeName;
	}

	@Override
	public final String getExchangeType() {
		return exchangeType;
	}

	@Override
	public final String getExchangeVHost() {
		return exchangeVHost;
	}

	@Override
	public final Extensions getExtensions() {
		return this.extensions;
	}

	@Override
	public String getIs() {
		return this.is;
	}

	@Override
	public final String getQueueName() {
		return queueName;
	}

	@Override

	public final String getQueueVHost() {
		return queueVHost;
	}

	@Override
	public final String getType() {
		return TYPE;
	}

	@Override
	public final boolean isExchangeAutoDelete() {
		return exchangeAutoDelete;
	}

	@Override
	public final boolean isExchangeDurable() {
		return exchangeDurable;
	}

	@Override
	public final boolean isQueueAutoDelete() {
		return queueAutoDelete;
	}

	@Override
	public final boolean isQueueDurable() {
		return queueDurable;
	}

	@Override
	public final boolean isQueueExclusive() {
		return queueExclusive;
	}

	@Override
	public final boolean isRoutingKey() {
		return routingKey;
	}

	@Override
	public final String toString() {
		return ToStringFormatter.toString(this);
	}

}
