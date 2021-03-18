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
import engineer.asyncapi.spyder.model.Message;
import engineer.asyncapi.spyder.model.Operation;
import engineer.asyncapi.spyder.model.OperationTrait;
import engineer.asyncapi.spyder.model.Tag;
import engineer.asyncapi.spyder.model.bindings.OperationBindings;
import java.util.List;

/**
 * 
 * @author johncatlin
 *
 */
final class OperationImpl implements Operation {

  /**
   * 
   * @author johncatlin
   *
   */
  static final class Builder {

    private OperationImpl operation = new OperationImpl();

    final Builder bindings(final OperationBindings bindings) {
      operation.bindings = bindings;
      return this;
    }

    final Operation build() {
      return operation;
    }

    final Builder description(final String description) {
      operation.description = description;
      return this;
    }

    final Builder extensions(final Extensions extensions) {
      operation.extensions = extensions;
      return this;
    }

    final Builder externalDocs(final ExternalDocs externalDocs) {
      operation.externalDocs = externalDocs;
      return this;
    }

    final Builder message(Message message) {
      operation.message = message;
      return this;
    }

    final Builder operationId(final String operationId) {
      operation.operationId = operationId;
      return this;
    }

    final Builder summary(final String summary) {
      operation.summary = summary;
      return this;
    }

    final Builder tags(final List<Tag> tags) {
      operation.tags = tags;
      return this;
    }

    final Builder traits(final List<OperationTrait> list) {
      operation.traits = list;
      return this;
    }
  }

  private OperationBindings bindings = null;
  private String description = null;
  private Extensions extensions = null;
  private ExternalDocs externalDocs = null;
  private Message message = null;
  private String operationId = null;
  private String summary = null;
  private List<Tag> tags = null;
  private List<OperationTrait> traits = null;

  protected OperationImpl() {
    /* Use the builder for construction. */
  }

  @Override
  public final OperationBindings getBindings() {
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
  public final Message getMessage() {
    return message;
  }

  @Override
  public final String getOperationId() {
    return this.operationId;
  }

  @Override
  public final String getSummary() {
    return this.summary;
  }

  @Override
  public final List<Tag> getTags() {
    return this.tags;
  }

  @Override
  public final List<OperationTrait> getTraits() {
    return this.traits;
  }

  @Override
  public final String toString() {
    return ToStringFormatter.toString(this);
  }

}
