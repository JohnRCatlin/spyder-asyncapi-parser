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
import engineer.asyncapi.spyder.model.Schema;
import engineer.asyncapi.spyder.model.bindings.BindingType;
import engineer.asyncapi.spyder.model.bindings.KafkaOperationBinding010;

/**
 * 
 * @author johncatlin
 *
 */
final class KafkaOperationBinding010Impl implements KafkaOperationBinding010 {

  /**
   * 
   * @author johncatlin
   *
   */
  static final class Builder {

    private KafkaOperationBinding010Impl binding = new KafkaOperationBinding010Impl();

    public final KafkaOperationBinding010 build() {
      return binding;
    }

    public final Builder clientId(final Schema schema2) {
      binding.clientId = schema2;
      return this;
    }

    final Builder extensions(final Extensions extensions) {
      binding.extensions = extensions;
      return this;
    }

    public final Builder groupId(final Schema schema2) {
      binding.groupId = schema2;
      return this;
    }
  }

  static final String BINDING_VERSION = "0.1.0";
  static final String TYPE = BindingType.KAFKA.value;

  private Schema clientId = null;
  private Extensions extensions = null;
  private Schema groupId = null;

  private KafkaOperationBinding010Impl() {
    /* Use the builder for construction. */
  }

  @Override
  public final Schema getClientId() {
    return this.clientId;
  }

  @Override
  public final Extensions getExtensions() {
    return this.extensions;
  }

  @Override
  public final Schema getGroupId() {
    return this.groupId;
  }

  @Override
  public final String getBindingType() {
    return TYPE;
  }

  @Override
  public final String toString() {
    return ToStringFormatter.toString(this);
  }

  @Override
  public final String getBindingVersion() {
    return BINDING_VERSION;
  }
}
