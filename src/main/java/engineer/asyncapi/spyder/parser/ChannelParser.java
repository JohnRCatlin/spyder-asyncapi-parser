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
import engineer.asyncapi.spyder.model.Channel;
import engineer.asyncapi.spyder.model.Operation;
import engineer.asyncapi.spyder.model.bindings.ChannelBindings;
import engineer.asyncapi.spyder.model.fields.Fields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author johncatlin
 *
 */
final class ChannelParser extends AsyncAPICommonObjectParser {

  static final Logger log = LoggerFactory.getLogger(ChannelParser.class);

  static final Channel parse(final ObjectNode node) {
    if (node == null) {
      return null;
    }
    final ChannelImpl.Builder builder = new ChannelImpl.Builder();
    builder.ref(parseRef(node));
    builder.description(parseDescription(node));
    builder.publish(parsePublish(node));
    builder.subscribe(parseSubscribe(node));
    builder.extensions(parseExtensions(node));
    builder.parameters(parseParameters(node));
    builder.bindings(parseBindings(node));
    return builder.build();
  }

  static final Operation parsePublish(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode publishNode = objectNodeFrom(Fields.PUBLISH.value, node);
    if (publishNode != null) {
      return OperationParser.parse(publishNode);
    }
    return null;
  }

  static final Operation parseSubscribe(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode subscribeNode = objectNodeFrom(Fields.SUBSCRIBE.value, node);
    if (subscribeNode != null) {
      return OperationParser.parse(subscribeNode);
    }
    return null;
  }

  static final ChannelBindings parseBindings(final ObjectNode node) {
    if (null == node) {
      return null;
    }
    final ObjectNode objectNode = objectNodeFrom(Fields.BINDINGS.value, node);
    final ChannelBindings bindings = ChannelBindingsParser.parse(objectNode);
    if (bindings != null && bindings.size() > 0) {
      return bindings;
    }
    return null;
  }

  private ChannelParser() {
    /* this static utility should not be instantiated */
  }
}