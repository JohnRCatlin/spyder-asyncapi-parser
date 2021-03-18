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

import engineer.asyncapi.spyder.model.bindings.MQTTServerBinding;
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
final class MQTTServerBindingParser extends AsyncAPICommonObjectParser {

  static final MQTTServerBinding parse(final ObjectNode node) {
    try {
      final String bindingVersion = parseBindingVersion(node);
      if (null == bindingVersion
          || bindingVersion.equals(MQTTServerBinding010Impl.BINDING_VERSION)) {
        return parseBindingV010(node);
      }
      // use latest
      return parseBindingV010(node);
    } catch (Exception e) {
      return null;
    }
  }

  private static final MQTTServerBinding parseBindingV010(final ObjectNode node) {
    final MQTTServerBinding010Impl.Builder builder = new MQTTServerBinding010Impl.Builder();
    builder.extensions(parseExtensions(node));
    builder.clientId(parseClientId(node));
    builder.cleanSession(parseCleanSession(node));
    builder.keepAlive(parseKeepAlive(node));
    builder.lastWillMessage(parseLastWillMessage(node));
    builder.lastWillQos(parseLastWillQos(node));
    builder.lastWillRetain(parseLastWillRetain(node));
    builder.lastWillTopic(parseLastWillTopic(node));
    return builder.build();
  }

  private static Boolean parseCleanSession(final ObjectNode node) {
    return booleanFrom(Fields.CLEAN_SESSION.value, node);
  }

  private static String parseClientId(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.CLIENT_ID.value, node);
  }

  private static Integer parseKeepAlive(final ObjectNode node) {
    return integerFrom(Fields.KEEP_ALIVE.value, node);
  }

  private static String parseLastWillMessage(final ObjectNode node) {
    ObjectNode topicNode = objectNodeFrom(Fields.LAST_WILL.value, node);
    if (null == topicNode || !isObjectNode(topicNode)) {
      return null;
    }
    return valueOfKeyOrNull(Fields.MESSAGE.value, topicNode);
  }

  private static Integer parseLastWillQos(final ObjectNode node) {
    ObjectNode topicNode = objectNodeFrom(Fields.LAST_WILL.value, node);
    if (null == topicNode || !isObjectNode(topicNode)) {
      return null;
    }
    return integerFrom(Fields.QOS.value, topicNode);
  }

  private static Boolean parseLastWillRetain(final ObjectNode node) {
    ObjectNode topicNode = objectNodeFrom(Fields.LAST_WILL.value, node);
    if (null == topicNode || !isObjectNode(topicNode)) {
      return null;
    }
    return booleanFrom(Fields.RETAIN.value, topicNode);
  }

  private static String parseLastWillTopic(final ObjectNode node) {
    ObjectNode topicNode = objectNodeFrom(Fields.LAST_WILL.value, node);
    if (null == topicNode || !isObjectNode(topicNode)) {
      return null;
    }
    return valueOfKeyOrNull(Fields.TOPIC.value, topicNode);
  }

  private MQTTServerBindingParser() {
    /* this static utility should not be instantiated */
  }
}