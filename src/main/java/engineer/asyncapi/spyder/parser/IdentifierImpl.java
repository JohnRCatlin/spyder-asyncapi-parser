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

import engineer.asyncapi.spyder.model.Identifier;

/**
 * 
 * @author johncatlin
 *
 */
final class IdentifierImpl implements Identifier {

  /**
   * 
   * @author johncatlin
   *
   */
  static final class Builder {

    private IdentifierImpl identifier = new IdentifierImpl();

    public final Identifier build() {
      return identifier;
    }

    public final Builder id(final String id) {
      identifier.id = id;
      return this;
    }
  }

  private String id = null;

  private IdentifierImpl() {
    /* Use the builder for construction. */
  }

  @Override
  public final String getId() {
    return this.id;
  }

  @Override
  public final String toString() {
    return ToStringFormatter.toString(this);
  }
}
