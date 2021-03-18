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
import engineer.asyncapi.spyder.model.ExternalDocs;
import engineer.asyncapi.spyder.model.Tag;

/**
 * 
 * @author johncatlin
 *
 */
final class TagImpl implements Tag {

  /**
   * 
   * @author johncatlin
   *
   */
  static final class Builder {

    private TagImpl tag = new TagImpl();

    final Tag build() {
      return tag;
    }

    final Builder description(final String description) {
      tag.description = description;
      return this;
    }

    final Builder extension(final String name, final String value) {
      if (ExtensionsParser.notValidExtension(name)) {
        return this;
      }
      tag.extensions.put(name, value);
      return this;
    }

    final Builder extensions(final Extensions extensions) {
      tag.extensions = extensions;
      return this;
    }

    final Builder externalDocs(final ExternalDocs externalDocs) {
      tag.externalDocs = externalDocs;
      return this;
    }

    final Builder name(final String name) {
      tag.name = name;
      return this;
    }
  }

  private String description = null;
  private Extensions extensions = null;
  private ExternalDocs externalDocs = null;
  private String name = null;

  private TagImpl() {
    /* Use the builder for construction. */
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
  public final ExternalDocs getExternalDocs() {
    return this.externalDocs;
  }

  @Override
  public final String getName() {
    return this.name;
  }

  @Override
  public final String toString() {
    return ToStringFormatter.toString(this);
  }
}
