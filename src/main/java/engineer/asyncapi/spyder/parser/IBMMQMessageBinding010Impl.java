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
import engineer.asyncapi.spyder.model.bindings.IBMMQMessageBinding010;

/**
 * 
 * @author johncatlin
 *
 */
final class IBMMQMessageBinding010Impl implements IBMMQMessageBinding010 {

  /**
   * 
   * @author johncatlin
   *
   */
  static final class Builder {

    private IBMMQMessageBinding010Impl binding = new IBMMQMessageBinding010Impl();

    public final IBMMQMessageBinding010Impl build() {
      return binding;
    }

    final Builder description(final String description) {
      binding.description = description;
      return this;
    }

    final Builder expiry(final Integer expiry) {
      binding.expiry = expiry;
      return this;
    }

    final Builder extensions(final Extensions extensions) {
      binding.extensions = extensions;
      return this;
    }

    final Builder headers(final String headers) {
      binding.headers = headers;
      return this;
    }

    final Builder type(final String type) {
      binding.type = type;
      return this;
    }

  }

  static final String BINDING_TYPE = BindingType.IBMMQ.value;
  static final String BINDING_VERSION = "0.1.0";

  private String description = null;
  private Integer expiry = null;
  private Extensions extensions = null;
  private String headers = null;
  private String type = null;

  private IBMMQMessageBinding010Impl() {
    /* Use the builder for construction. */
  }

  @Override
  public final String getBindingType() {
    return BINDING_TYPE;
  }

  @Override
  public final String getBindingVersion() {
    return BINDING_VERSION;
  }

  @Override
  public final String getDescription() {
    return this.description;
  }

  @Override
  public final Integer getExpiry() {
    return this.expiry;
  }

  @Override
  public final Extensions getExtensions() {
    return this.extensions;
  }

  @Override
  public final String getHeaders() {
    return this.headers;
  }

  @Override
  public final String getType() {
    return this.type;
  }

  @Override
  public final String toString() {
    return ToStringFormatter.toString(this);
  }

}
