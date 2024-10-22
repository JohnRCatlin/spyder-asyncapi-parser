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
import engineer.asyncapi.spyder.model.ServerVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author johncatlin
 *
 */
final class ServerVariableImpl implements ServerVariable {

  /**
   * 
   * @author johncatlin
   *
   */
  static final class Builder {

    private ServerVariableImpl variable = new ServerVariableImpl();

    final Builder theDefault(final String theDefault) {
      variable.theDefault = theDefault;
      return this;
    }

    final Builder theEnum(final List<String> theEnum) {
      variable.theEnum = theEnum;
      return this;
    }

    final ServerVariable build() {
      return variable;
    }

    final Builder description(final String description) {
      variable.description = description;
      return this;
    }

    final Builder enumItem(final String enumItem) {
      if (variable.theEnum == null) {
        variable.theEnum = new ArrayList<>();
      }
      variable.theEnum.add(enumItem);
      return this;
    }

    final Builder extension(final String name, final String value) {
      if (ExtensionsParser.notValidExtension(name)) {
        return this;
      }
      variable.extensions.put(name, value);
      return this;
    }

    final Builder extensions(final Extensions extensions) {
      variable.extensions = extensions;
      return this;
    }
  }

  private String theDefault = null;
  private List<String> theEnum = null;
  private String description = null;
  private Extensions extensions = null;

  private ServerVariableImpl() {
    /* Use the builder for construction. */
  }

  @Override
  public final String getDefault() {
    return this.theDefault;
  }

  @Override
  public final String getDescription() {
    return this.description;
  }

  @Override
  public final List<String> getEnum() {
    return this.theEnum;
  }

  @Override
  public final Extensions getExtensions() {
    return this.extensions;
  }

  @Override
  public final void setEnum(final List<String> theEnum) {
    this.theEnum = theEnum;
  }

  @Override
  public final String toString() {
    return ToStringFormatter.toString(this);
  }

}