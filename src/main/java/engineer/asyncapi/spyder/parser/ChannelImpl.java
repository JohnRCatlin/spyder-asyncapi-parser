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

import engineer.asyncapi.spyder.model.Channel;
import engineer.asyncapi.spyder.model.Extensions;
import engineer.asyncapi.spyder.model.Operation;
import engineer.asyncapi.spyder.model.Parameters;
import engineer.asyncapi.spyder.model.bindings.ChannelBindings;

/**
 * 
 * @author johncatlin
 *
 */
class ChannelImpl implements Channel {

  /**
   * 
   * @author johncatlin
   *
   */
  static final class Builder {

    private static final String REF_BASE = "#/components/channels/channel";
    private ChannelImpl channel = new ChannelImpl();

    public final Builder bindings(final ChannelBindings bindings) {
      channel.bindings = bindings;
      return this;
    }

    public final Channel build() {
      return channel;
    }

    public final Builder description(final String description) {
      channel.description = description;
      return this;
    }

    public final Builder extensions(final Extensions extensions) {
      channel.extensions = extensions;
      return this;
    }

    public final Builder parameters(final Parameters parameters) {
      channel.parameters = parameters;
      return this;
    }

    public final Builder publish(final Operation publish) {
      channel.publish = publish;
      return this;
    }

    public final Builder ref(final String ref) {
      channel.ref = (ref != null && RefUtility.isRelative(ref)) ? REF_BASE + ref : ref;
      return this;
    }

    public final Builder subscribe(final Operation subscribe) {
      channel.subscribe = subscribe;
      return this;
    }
  }

  private ChannelBindings bindings = null;
  private String description = null;
  private Extensions extensions = null;
  private Parameters parameters = null;
  private Operation publish = null;
  private String ref = null;

  private Operation subscribe = null;

  private ChannelImpl() {
    /* Use the builder for construction. */
  }

  @Override
  public final ChannelBindings getBindings() {
    return this.bindings;
  }

  @Override
  public final String getDescription() {
    return this.description;
  }

  @Override
  public final Extensions getExtensions() {
    return this.extensions;
  }

  @Override
  public final Parameters getParameters() {
    return parameters;
  }

  @Override
  public final Operation getPublish() {
    return this.publish;
  }

  @Override
  public final String getRef() {
    return ref;
  }

  @Override
  public final Operation getSubscribe() {
    return this.subscribe;
  }

  @Override
  public final String toString() {
    return ToStringFormatter.toString(this);
  }

}
