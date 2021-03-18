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

import java.util.List;

import engineer.asyncapi.spyder.model.Extensions;
import engineer.asyncapi.spyder.model.bindings.AMQP091OperationBinding020;
import engineer.asyncapi.spyder.model.bindings.BindingType;

/**
 * 
 * @author johncatlin
 *
 */
final class AMQP091OperationBinding020Impl implements AMQP091OperationBinding020 {

  /**
   * 
   * @author johncatlin
   *
   */
  static final class Builder {

    private AMQP091OperationBinding020Impl binding = new AMQP091OperationBinding020Impl();

    public final Builder ack(final Boolean ack) {
      binding.ack = ack;
      return this;
    }

    public final Builder bcc(final List<String> bcc) {
      binding.bcc = bcc;
      return this;
    }

    public final AMQP091OperationBinding020 build() {
      return binding;
    }

    public final Builder cc(final List<String> cc) {
      binding.cc = cc;
      return this;
    }

    public final Builder deliveryMode(final Integer deliveryMode) {
      binding.deliveryMode = deliveryMode;
      return this;
    }

    public final Builder expiration(final Long expiration) {
      binding.expiration = expiration;
      return this;
    }

    public final Builder extensions(final Extensions extensions) {
      binding.extensions = extensions;
      return this;
    }

    public final Builder mandatory(final Boolean mandatory) {
      binding.mandatory = mandatory;
      return this;
    }

    public final Builder priority(final Integer priority) {
      binding.priority = priority;
      return this;
    }

    public final Builder replyTo(final String replyTo) {
      binding.replyTo = replyTo;
      return this;
    }

    public final Builder timestamp(final Boolean timestamp) {
      binding.timestamp = timestamp;
      return this;
    }

    public final Builder userId(final String userId) {
      binding.userId = userId;
      return this;
    }
  }

  static final String BINDING_VERSION = "0.2.0";

  static final String TYPE = BindingType.AMQP.value;

  private Boolean ack;

  private List<String> bcc;

  private List<String> cc;

  private Integer deliveryMode;

  private Long expiration;

  private Extensions extensions = null;

  private Boolean mandatory;

  private Integer priority;

  private String replyTo;

  private Boolean timestamp;

  private String userId;

  @Override
  public final List<String> getBcc() {
    return bcc;
  }

  @Override
  public final String getBindingVersion() {
    return BINDING_VERSION;
  }

  @Override
  public final List<String> getCc() {
    return cc;
  }

  @Override
  public final int getDeliveryMode() {
    return deliveryMode;
  }

  @Override
  public final long getExpiration() {
    return expiration;
  }

  @Override
  public final Extensions getExtensions() {
    return this.extensions;
  }

  @Override
  public final int getPriority() {
    return priority;
  }

  @Override
  public final String getReplyTo() {
    return replyTo;
  }

  @Override
  public final String getBindingType() {
    return TYPE;
  }

  @Override
  public final String getUserId() {
    return userId;
  }

  @Override
  public final boolean isAck() {
    return ack;
  }

  @Override
  public final boolean isMandatory() {
    return mandatory;
  }

  @Override
  public final boolean isTimestamp() {
    return timestamp;
  }

  @Override
  public final String toString() {
    return ToStringFormatter.toString(this);
  }
}
