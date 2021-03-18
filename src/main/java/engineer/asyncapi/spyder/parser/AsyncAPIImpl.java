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

import engineer.asyncapi.spyder.model.AsyncAPI;
import engineer.asyncapi.spyder.model.Channels;
import engineer.asyncapi.spyder.model.Components;
import engineer.asyncapi.spyder.model.Extensions;
import engineer.asyncapi.spyder.model.ExternalDocs;
import engineer.asyncapi.spyder.model.Identifier;
import engineer.asyncapi.spyder.model.Info;
import engineer.asyncapi.spyder.model.Schema;
import engineer.asyncapi.spyder.model.Server;
import engineer.asyncapi.spyder.model.Servers;
import engineer.asyncapi.spyder.model.Tag;
import engineer.asyncapi.spyder.model.Tags;

/**
 * 
 * @author johncatlin
 *
 */
class AsyncAPIImpl implements AsyncAPI {

  /**
   * 
   * @author johncatlin
   *
   */
  static final class Builder {

    private AsyncAPIImpl asyncApi = new AsyncAPIImpl();

    public final Builder asyncapi(final String asyncapi) {
      asyncApi.asyncapiVersion = asyncapi;
      return this;
    }

    public final AsyncAPI build() {
      return asyncApi;
    }

    public final Builder channels(final Channels channels) {
      asyncApi.channels = channels;
      return this;
    }

    public final Builder components(final Components components) {
      asyncApi.components = components;
      return this;
    }

    public final Builder defaultContentType(final String defaultContentType) {
      asyncApi.defaultContentType = defaultContentType;
      return this;
    }

    public final Builder extensions(final Extensions extensions) {
      asyncApi.extensions = extensions;
      return this;
    }

    public final Builder externalDocs(final ExternalDocs externalDocs) {
      asyncApi.externalDocs = externalDocs;
      return this;
    }

    public final Builder id(final Identifier id) {
      asyncApi.identifier = id;
      return this;
    }

    public final Builder info(final Info info) {
      asyncApi.info = info;
      return this;
    }

    public final Builder schema(final String name, final Schema schema) {
      if (asyncApi.components == null) {
        asyncApi.components = new ComponentsImpl.Builder().build();
      }
      ((ComponentsImpl) asyncApi.components).addSchemas(name, schema);
      return this;
    }

    public final Builder schemaRequirement(final String name,
        final SecuritySchemeImpl securityScheme) {
      if (asyncApi.components == null) {
        asyncApi.components = new ComponentsImpl.Builder().build();
      }
      ((ComponentsImpl) asyncApi.components).addSecuritySchemes(name, securityScheme);
      return this;
    }

    public final Builder server(final String name, final Server serversItem) {
      if (asyncApi.servers == null) {
        asyncApi.servers = new ServersImpl();
      }
      asyncApi.servers.put(name, serversItem);
      return this;
    }

    public final Builder servers(final Servers servers) {
      asyncApi.servers = servers;
      return this;
    }

    public final Builder tag(final Tag tagsItem) {
      if (asyncApi.tags == null) {
        asyncApi.tags = new TagsImpl();
      }
      asyncApi.tags.add(tagsItem);
      return this;
    }

    public final Builder tags(final Tags tags) {
      asyncApi.tags = tags;
      return this;
    }
  }

  private String asyncapiVersion = "2.0.0";
  private Channels channels = new ChannelsImpl();
  private Components components = null;
  private String defaultContentType = null;
  private Extensions extensions = null;
  private ExternalDocs externalDocs = null;
  private Identifier identifier = null;
  private Info info = null;
  private Servers servers = null;
  private Tags tags = null;

  private AsyncAPIImpl() {
    /* Use the builder for construction. */
  }

  @Override
  public final String getAsyncapi() {
    return this.asyncapiVersion;
  }

  @Override
  public final Channels getChannels() {
    return this.channels;
  }

  @Override
  public final Components getComponents() {
    return this.components;
  }

  @Override
  public final String getDefaultContentType() {
    return this.defaultContentType;
  }

  @Override
  public final Extensions getExtensions() {
    return this.extensions;
  }

  @Override
  public final ExternalDocs getExternalDocs() {
    return this.externalDocs;
  }

  @Override
  public final Identifier getIdentifier() {
    return this.identifier;
  }

  @Override
  public final Info getInfo() {
    return this.info;
  }

  @Override
  public final Servers getServers() {
    return this.servers;
  }

  @Override
  public final Tags getTags() {
    return this.tags;
  }

  @Override
  public final String toString() {
    return ToStringFormatter.toString(this);
  }
}
