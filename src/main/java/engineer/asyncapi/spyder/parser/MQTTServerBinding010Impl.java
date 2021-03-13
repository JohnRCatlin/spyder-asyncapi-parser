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
import engineer.asyncapi.spyder.model.bindings.BindingType;
import engineer.asyncapi.spyder.model.bindings.MQTTServerBinding010;

/**
 * 
 * @author johncatlin
 *
 */
final class MQTTServerBinding010Impl implements MQTTServerBinding010 {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private MQTTServerBinding010Impl binding = new MQTTServerBinding010Impl();

		public final MQTTServerBinding010Impl build() {
			return binding;
		}

		final Builder cleanSession(final Boolean cleanSession) {
			binding.cleanSession = cleanSession;
			return this;
		}

		final Builder clientId(final String clientId) {
			binding.clientId = clientId;
			return this;
		}

		final Builder extensions(final Extensions extensions) {
			binding.extensions = extensions;
			return this;
		}

		final Builder keepAlive(final Integer keepAlive) {
			binding.keepAlive = keepAlive;
			return this;
		}

		final Builder lastWillMessage(final String lastWillMessage) {
			binding.lastWillMessage = lastWillMessage;
			return this;
		}

		final Builder lastWillQos(final Integer lastWillQos) {
			binding.lastWillQos = lastWillQos;
			return this;
		}

		final Builder lastWillRetain(final Boolean lastWillRetain) {
			binding.lastWillRetain = lastWillRetain;
			return this;
		}

		final Builder lastWillTopic(final String lastWillTopic) {
			binding.lastWillTopic = lastWillTopic;
			return this;
		}
	}

	static final String BINDING_VERSION = "0.1.0";
	static final String TYPE = BindingType.MQTT.value;

	private Boolean cleanSession = null;
	private String clientId = null;
	private Extensions extensions = null;
	private Integer keepAlive = null;
	private String lastWillMessage = null;
	private Integer lastWillQos = null;
	private Boolean lastWillRetain = null;
	private String lastWillTopic = null;

	private MQTTServerBinding010Impl() {
		/* Use the builder for construction. */
	}

	@Override
	public final String getBindingType() {
		return TYPE;
	}

	@Override
	public final String getBindingVersion() {
		return BINDING_VERSION;
	}

	@Override
	public Boolean getCleanSession() {
		return this.cleanSession;
	}

	@Override
	public String getClientId() {
		return this.clientId;
	}

	@Override
	public final Extensions getExtensions() {
		return this.extensions;
	}

	@Override
	public Integer getKeepAlive() {
		return this.keepAlive;
	}

	@Override
	public String getLastWillMessage() {
		return this.lastWillMessage;
	}

	@Override
	public Integer getLastWillQos() {
		return this.lastWillQos;
	}

	@Override
	public Boolean getLastWillRetain() {
		return this.lastWillRetain;
	}

	@Override
	public String getLastWillTopic() {
		return this.lastWillTopic;
	}

	@Override
	public final String toString() {
		return ToStringFormatter.toString(this);
	}

}
