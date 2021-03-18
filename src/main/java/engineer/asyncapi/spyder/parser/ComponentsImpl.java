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

import engineer.asyncapi.spyder.model.Components;
import engineer.asyncapi.spyder.model.CorrelationIds;
import engineer.asyncapi.spyder.model.Extensions;
import engineer.asyncapi.spyder.model.MessageTraits;
import engineer.asyncapi.spyder.model.Messages;
import engineer.asyncapi.spyder.model.OperationTraits;
import engineer.asyncapi.spyder.model.Parameters;
import engineer.asyncapi.spyder.model.Schema;
import engineer.asyncapi.spyder.model.Schemas;
import engineer.asyncapi.spyder.model.SecurityScheme;
import engineer.asyncapi.spyder.model.SecuritySchemes;
import engineer.asyncapi.spyder.model.bindings.ChannelBindings;
import engineer.asyncapi.spyder.model.bindings.MessageBindings;
import engineer.asyncapi.spyder.model.bindings.OperationBindings;
import engineer.asyncapi.spyder.model.bindings.ServerBindings;

/**
 * 
 * @author johncatlin
 *
 */
final class ComponentsImpl implements Components {

  /**
   * 
   * @author johncatlin
   *
   */
  static final class Builder {

    private ComponentsImpl components = new ComponentsImpl();

    final ComponentsImpl build() {
      return components;
    }

    final Builder channelBindings(final ChannelBindings channelBindings) {
      components.channelBindings = channelBindings;
      return this;
    }

    final Builder correlationIds(final CorrelationIds map) {
      components.correlationIds = map;
      return this;
    }

    final Builder extension(final String name, final String value) {
      if (ExtensionsParser.notValidExtension(name)) {
        return this;
      }
      if (components.extensions == null) {
        components.extensions = new ExtensionsImpl();
      }
      components.extensions.put(name, value);
      return this;
    }

    final Builder extensions(final Extensions extensions) {
      components.extensions = extensions;
      return this;
    }

    final Builder messageBindings(final MessageBindings messageBindings) {
      components.messageBindings = messageBindings;
      return this;
    }

    final Builder messages(Messages messages) {
      components.messages = messages;
      return this;
    }

    final Builder messageTraits(final MessageTraits messageTraits) {
      components.messageTraits = messageTraits;
      return this;
    }

    final Builder operationBindings(final OperationBindings operationBindings) {
      components.operationBindings = operationBindings;
      return this;
    }

    final Builder operationTraits(final OperationTraits map) {
      components.operationTraits = map;
      return this;
    }

    final Builder parameters(final Parameters parameters) {
      components.parameters = parameters;
      return this;
    }

    final Builder schemas(final Schemas schemas) {
      components.schemas = schemas;
      return this;
    }

    final Builder securitySchemes(final SecuritySchemes securitySchemes) {
      components.securitySchemes = securitySchemes;
      return this;
    }

    final Builder serverBindings(final ServerBindings serverBindings) {
      components.serverBindings = serverBindings;
      return this;
    }

  }

  private ChannelBindings channelBindings = null;
  private CorrelationIds correlationIds = null;
  private Extensions extensions = null;
  private MessageBindings messageBindings = null;
  private Messages messages = null;
  private MessageTraits messageTraits = null;
  private OperationBindings operationBindings = null;
  private OperationTraits operationTraits = null;
  private Parameters parameters = null;
  private Schemas schemas = null;
  private SecuritySchemes securitySchemes = null;
  private ServerBindings serverBindings = null;

  private ComponentsImpl() {
    /* Use the builder for construction. */
  }

  public final Components addSchemas(final String key, final Schema schemasItem) {
    if (this.schemas == null) {
      this.schemas = new SchemasImpl();
    }
    this.schemas.put(key, schemasItem);
    return this;
  }

  public final Components addSecuritySchemes(final String key,
      final SecurityScheme securitySchemesItem) {
    if (this.securitySchemes == null) {
      this.securitySchemes = new SecuritySchemesImpl();
    }
    this.securitySchemes.put(key, securitySchemesItem);
    return this;
  }

  @Override
  public final ChannelBindings getChannelBindings() {
    return this.channelBindings;
  }

  @Override
  public final CorrelationIds getCorrelationIds() {
    return this.correlationIds;
  }

  @Override
  public final Extensions getExtensions() {
    return this.extensions;
  }

  @Override
  public final MessageBindings getMessageBindings() {
    return this.messageBindings;
  }

  @Override
  public final Messages getMessages() {
    return this.messages;
  }

  @Override
  public final MessageTraits getMessageTraits() {
    return this.messageTraits;
  }

  @Override
  public final OperationBindings getOperationBindings() {
    return this.operationBindings;
  }

  @Override
  public final OperationTraits getOperationTraits() {
    return this.operationTraits;
  }

  @Override
  public final Parameters getParameters() {
    return this.parameters;
  }

  @Override
  public final Schemas getSchemas() {
    return this.schemas;
  }

  @Override
  public final SecuritySchemes getSecuritySchemes() {
    return this.securitySchemes;
  }

  @Override
  public final ServerBindings getServerBindings() {
    return this.serverBindings;
  }

  @Override
  public final String toString() {
    return ToStringFormatter.toString(this);
  }

}
