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

import engineer.asyncapi.spyder.model.bindings.IBMMQChannelBinding;
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
final class IBMMQChannelBindingParser extends AsyncAPICommonObjectParser {

  static final IBMMQChannelBinding parse(final ObjectNode node) {
    try {
      final String bindingVersion = parseBindingVersion(node);
      if (null == bindingVersion
          || bindingVersion.equals(IBMMQChannelBinding010Impl.BINDING_VERSION)) {
        return parseBindingV010(node);
      }
      // use latest
      return parseBindingV010(node);
    } catch (Exception e) {
      return null;
    }
  }

  private static final IBMMQChannelBinding parseBindingV010(final ObjectNode node) {
    final IBMMQChannelBinding010Impl.Builder builder = new IBMMQChannelBinding010Impl.Builder();
    builder.extensions(parseExtensions(node));
    builder.destinationType(parseDestinationType(node));
    builder.maxMsgLength(parseMaxMsgLength(node));
    builder.queueExclusive(parseQueueExclusive(node));
    builder.queueIsPartitioned(parseQueueIsPartitioned(node));
    builder.queueObjectName(parseQueueObjectName(node));
    builder.topicDurablePermitted(parseTopicDurablePermitted(node));
    builder.topicLastMsgRetained(parseTopicLastMsgRetained(node));
    builder.topicObjectName(parseTopicObjectName(node));
    builder.topicString(parseTopicString(node));
    return builder.build();
  }

  private static final String parseDestinationType(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.DESTINATION_TYPE.value, node);
  }

  private static final Integer parseMaxMsgLength(final ObjectNode node) {
    return integerFrom(Fields.MAX_MSG_LENGTH.value, node);
  }

  private static final Boolean parseQueueExclusive(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode queue = objectNodeFrom(Fields.QUEUE.value, node);
    if (null == queue) {
      return null;
    }
    return booleanFrom(Fields.EXCLUSIVE.value, queue);
  }

  private static final Boolean parseQueueIsPartitioned(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode queue = objectNodeFrom(Fields.QUEUE.value, node);
    if (null == queue) {
      return null;
    }
    return booleanFrom(Fields.IS_PARTITIONED.value, queue);
  }

  private static final String parseQueueObjectName(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode queue = objectNodeFrom(Fields.QUEUE.value, node);
    if (null == queue) {
      return null;
    }
    return valueOfKeyOrNull(Fields.OBJECT_NAME.value, queue);
  }

  private static final Boolean parseTopicDurablePermitted(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode topic = objectNodeFrom(Fields.TOPIC.value, node);
    if (null == topic) {
      return null;
    }
    return booleanFrom(Fields.DUARBLE_PERMITTED.value, topic);
  }

  private static final Boolean parseTopicLastMsgRetained(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode topic = objectNodeFrom(Fields.TOPIC.value, node);
    if (null == topic) {
      return null;
    }
    return booleanFrom(Fields.LAST_MSG_RETAINED.value, topic);
  }

  private static final String parseTopicObjectName(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode topic = objectNodeFrom(Fields.TOPIC.value, node);
    if (null == topic) {
      return null;
    }
    return valueOfKeyOrNull(Fields.OBJECT_NAME.value, topic);
  }

  private static final String parseTopicString(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode topic = objectNodeFrom(Fields.TOPIC.value, node);
    if (null == topic) {
      return null;
    }
    return valueOfKeyOrNull(Fields.STRING.value, topic);
  }

  private IBMMQChannelBindingParser() {
    /* this static utility should not be instantiated */
  }
}