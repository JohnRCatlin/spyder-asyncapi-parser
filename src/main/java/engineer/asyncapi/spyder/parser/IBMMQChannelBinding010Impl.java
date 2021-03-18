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
import engineer.asyncapi.spyder.model.bindings.IBMMQChannelBinding010;

/**
 * 
 * @author johncatlin
 *
 */
final class IBMMQChannelBinding010Impl implements IBMMQChannelBinding010 {

  /**
   * 
   * @author johncatlin
   *
   */
  static final class Builder {

    private IBMMQChannelBinding010Impl binding = new IBMMQChannelBinding010Impl();

    public final IBMMQChannelBinding010Impl build() {
      return binding;
    }

    final Builder destinationType(final String destinationType) {
      binding.destinationType = destinationType;
      return this;
    }

    final Builder extensions(final Extensions extensions) {
      binding.extensions = extensions;
      return this;
    }

    final Builder maxMsgLength(final Integer maxMsgLength) {
      binding.maxMsgLength = maxMsgLength;
      return this;
    }

    final Builder queueExclusive(final Boolean queueExclusive) {
      binding.queueExclusive = queueExclusive;
      return this;
    }

    final Builder queueIsPartitioned(final Boolean queueIsPartitioned) {
      binding.queueIsPartitioned = queueIsPartitioned;
      return this;
    }

    final Builder queueObjectName(final String queueObjectName) {
      binding.queueObjectName = queueObjectName;
      return this;
    }

    final Builder topicDurablePermitted(final Boolean topicDurablePermitted) {
      binding.topicDurablePermitted = topicDurablePermitted;
      return this;
    }

    final Builder topicLastMsgRetained(final Boolean topicLastMsgRetained) {
      binding.topicLastMsgRetained = topicLastMsgRetained;
      return this;
    }

    final Builder topicObjectName(final String topicObjectName) {
      binding.topicObjectName = topicObjectName;
      return this;
    }

    final Builder topicString(final String topicString) {
      binding.topicString = topicString;
      return this;
    }

  }

  static final String BINDING_VERSION = "0.1.0";
  static final String TYPE = BindingType.IBMMQ.value;

  private String destinationType = null;
  private Extensions extensions = null;
  private Integer maxMsgLength = null;
  private Boolean queueExclusive = null;
  private Boolean queueIsPartitioned = null;
  private String queueObjectName = null;
  private Boolean topicDurablePermitted = null;
  private Boolean topicLastMsgRetained = null;
  private String topicObjectName = null;
  private String topicString = null;

  private IBMMQChannelBinding010Impl() {
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
  public String getDestinationType() {
    return this.destinationType;
  }

  @Override
  public final Extensions getExtensions() {
    return this.extensions;
  }

  @Override
  public Boolean getQueueExclusive() {
    return this.queueExclusive;
  }

  @Override
  public final Boolean getQueueIsPartitioned() {
    return this.queueIsPartitioned;
  }

  @Override
  public final String getQueueObjectName() {
    return this.queueObjectName;
  }

  @Override
  public final Boolean getTopicDurablePermitted() {
    return this.topicDurablePermitted;
  }

  @Override
  public final Boolean getTopicLastMsgRetained() {
    return this.topicLastMsgRetained;
  }

  @Override
  public final String getTopicObjectName() {
    return this.topicObjectName;
  }

  @Override
  public final String getTopicString() {
    return this.topicString;
  }

  @Override
  public final Integer maxMsgLength() {
    return this.maxMsgLength;
  }

  @Override
  public final String toString() {
    return ToStringFormatter.toString(this);
  }

}
