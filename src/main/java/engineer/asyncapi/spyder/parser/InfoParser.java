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

import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.Contact;
import engineer.asyncapi.spyder.model.Info;
import engineer.asyncapi.spyder.model.License;
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
final class InfoParser extends AsyncAPICommonObjectParser {

  static final Info parse(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final InfoImpl.Builder builder = new InfoImpl.Builder();
    builder.title(parseTitle(node));
    builder.version(parseVersion(node));
    builder.description(parseDescription(node));
    builder.termsOfService(parseTermsOfService(node));
    builder.contact(parseContact(node));
    builder.license(parseLicense(node));
    builder.extensions(parseExtensions(node));
    return builder.build();
  }

  static final String parseVersion(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.VERSION.value, node);
  }

  static final Contact parseContact(final ObjectNode node) {
    final ObjectNode objectNode = objectNodeFrom(Fields.CONTACT.value, node);
    return ContactParser.parse(objectNode);
  }

  static final License parseLicense(final ObjectNode node) {
    final ObjectNode objectNode = objectNodeFrom(Fields.LICENSE.value, node);
    return LicenseParser.parse(objectNode);
  }

  static final String parseTermsOfService(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.TERMS_OF_SERVICE.value, node);
  }

  private InfoParser() {
    /* this static utility should not be instantiated */
  }
}