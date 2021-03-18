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
import engineer.asyncapi.spyder.model.OperationTrait;
import engineer.asyncapi.spyder.model.Tags;
import engineer.asyncapi.spyder.model.bindings.OperationBinding;

/**
 * 
 * @author johncatlin
 *
 */
final class OperationTraitImpl implements OperationTrait {

  /**
   * 
   * @author johncatlin
   *
   */
  static final class Builder {

    private static final String REF_BASE = "#/components/operations/operationTraits/";
    OperationTraitImpl trait = new OperationTraitImpl();

    final Builder bindings(final OperationBinding operationBinding) {
      trait.bindings = operationBinding;
      return this;
    }

    final OperationTrait build() {
      return trait;
    }

    final Builder description(final String description) {
      trait.description = description;
      return this;
    }

    final Builder extensions(final Extensions extensions) {
      trait.extensions = extensions;
      return this;
    }

    final Builder externalDocs(final ExternalDocs externalDocs) {
      trait.externalDocs = externalDocs;
      return this;
    }

    final Builder operationId(final String operationId) {
      trait.operationId = operationId;
      return this;
    }

    final Builder ref(final String ref) {
      trait.ref = (ref != null && RefUtility.isRelative(ref)) ? REF_BASE + ref : ref;
      return this;
    }

    final Builder summary(final String summary) {
      trait.summary = summary;
      return this;
    }

    final Builder tags(final Tags tags) {
      trait.tags = tags;
      return this;
    }
  }

  private OperationBinding bindings = null;
  private String description = null;
  private Extensions extensions = null;
  private ExternalDocs externalDocs = null;
  private String operationId = null;
  private String ref = null;
  private String summary = null;
  private Tags tags = null;

  private OperationTraitImpl() {
    /* Use the builder for construction. */
  }

  @Override
  public final OperationBinding getBindings() {
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
  public final ExternalDocs getExternalDocs() {
    return this.externalDocs;
  }

  @Override
  public final String getOperationId() {
    return this.operationId;
  }

  @Override
  public final String getRef() {
    return this.ref;
  }

  @Override
  public final String getSummary() {
    return this.summary;
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
