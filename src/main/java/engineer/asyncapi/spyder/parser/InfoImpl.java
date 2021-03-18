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

import engineer.asyncapi.spyder.model.Contact;
import engineer.asyncapi.spyder.model.Extensions;
import engineer.asyncapi.spyder.model.Info;
import engineer.asyncapi.spyder.model.License;

/**
 * 
 * @author johncatlin
 *
 */
final class InfoImpl implements Info {

  /**
   * 
   * @author johncatlin
   *
   */
  static final class Builder {

    private InfoImpl info = new InfoImpl();

    public final Info build() {
      return info;
    }

    public final Builder contact(final Contact contact) {
      info.contact = contact;
      return this;
    }

    public final Builder description(final String description) {
      info.description = description;
      return this;
    }

    public final Builder extensions(final Extensions extensions) {
      info.extensions = extensions;
      return this;
    }

    public final Builder license(final License license) {
      info.license = license;
      return this;
    }

    public final Builder termsOfService(final String termsOfService) {
      info.termsOfService = termsOfService;
      return this;
    }

    public final Builder title(final String title) {
      info.title = title;
      return this;
    }

    public final Builder version(final String version) {
      info.version = version;
      return this;
    }
  }

  private Contact contact = null;
  private String description = null;
  private Extensions extensions = null;
  private License license = null;
  private String termsOfService = null;
  private String title = null;
  private String version = null;

  private InfoImpl() {
    /* Use the builder for construction. */
  }

  @Override
  public final Contact getContact() {
    return this.contact;
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
  public final License getLicense() {
    return this.license;
  }

  @Override
  public final String getTermsOfService() {
    return this.termsOfService;
  }

  @Override
  public final String getTitle() {
    return this.title;
  }

  @Override
  public final String getVersion() {
    return this.version;
  }

  @Override
  public final String toString() {
    return ToStringFormatter.toString(this);
  }
}
