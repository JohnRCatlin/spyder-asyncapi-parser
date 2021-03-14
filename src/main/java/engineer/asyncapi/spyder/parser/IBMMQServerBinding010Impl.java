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
import engineer.asyncapi.spyder.model.bindings.IBMMQServerBinding010;

/**
 * 
 * @author johncatlin
 *
 */
final class IBMMQServerBinding010Impl implements IBMMQServerBinding010 {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private IBMMQServerBinding010Impl binding = new IBMMQServerBinding010Impl();

		public final IBMMQServerBinding010Impl build() {
			return binding;
		}

		final Builder ccdtQueueManagerName(final String ccdtQueueManagerName) {
			binding.ccdtQueueManagerName = ccdtQueueManagerName;
			return this;
		}

		final Builder cipherSpec(final String cipherSpec) {
			binding.cipherSpec = cipherSpec;
			return this;
		}

		final Builder extensions(final Extensions extensions) {
			binding.extensions = extensions;
			return this;
		}

		public Builder groupId(final String groupId) {
			binding.groupId = groupId;
			return this;
		}

		final Builder heartBeatInterval(final Integer heartBeatInterval) {
			binding.heartBeatInterval = heartBeatInterval;
			return this;
		}

		final Builder multiEndpointServer(final Boolean multiEndpointServer) {
			binding.multiEndpointServer = multiEndpointServer;
			return this;
		}
	}

	static final String BINDING_VERSION = "0.1.0";
	static final String TYPE = BindingType.IBMMQ.value;

	private String ccdtQueueManagerName = null;
	private String cipherSpec = null;
	private Extensions extensions = null;
	private String groupId = null;
	private Integer heartBeatInterval = null;
	private Boolean multiEndpointServer = null;

	private IBMMQServerBinding010Impl() {
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
	public final String getCcdtQueueManagerName() {
		return this.ccdtQueueManagerName;
	}

	@Override
	public final String getCipherSpec() {
		return this.cipherSpec;
	}

	@Override
	public final Extensions getExtensions() {
		return this.extensions;
	}

	@Override
	public final String getGroupId() {
		return this.groupId;
	}

	@Override
	public final Integer getHeartBeatInterval() {
		return this.heartBeatInterval;
	}

	@Override
	public final Boolean getMultiEndpointServer() {
		return this.multiEndpointServer;
	}

	@Override
	public final String toString() {
		return ToStringFormatter.toString(this);
	}

}
