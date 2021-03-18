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
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
abstract class AsyncAPICommonFieldParser extends NodeClipper {

  static final boolean hasRef(final ObjectNode node) {
    return null != node.get(Fields.REF.value);
  }

  static final String parseBindingVersion(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.BINDING_VERSION.value, node);
  }

  static final String parseContentType(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.CONTENT_TYPE.value, node);
  }

  static final String parseDefault(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.DEFAULT.value, node);
  }

  static final String parseDescription(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.DESCRIPTION.value, node);
  }

  static final String parseLocation(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.LOCATION.value, node);
  }

  static final String parseMethod(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.METHOD.value, node);
  }

  static final String parseName(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.NAME.value, node);
  }

  static final String parseOperationId(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.OPERATION_ID.value, node);
  }

  static final String parseRef(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.REF.value, node);
  }

  static final String parseSummary(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.SUMMARY.value, node);
  }

  static final String parseTitle(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.TITLE.value, node);
  }

  static final String parseType(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.TYPE.value, node);
  }

  static final String parseUrl(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.URL.value, node);
  }

  protected AsyncAPICommonFieldParser() {
    /* this static utility should not be instantiated */
  }
}