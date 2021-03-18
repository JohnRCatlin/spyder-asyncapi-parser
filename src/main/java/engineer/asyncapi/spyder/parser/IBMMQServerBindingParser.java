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
import engineer.asyncapi.spyder.model.bindings.IBMMQServerBinding;
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
final class IBMMQServerBindingParser extends AsyncAPICommonObjectParser {

  static final IBMMQServerBinding parse(final ObjectNode node) {
    try {
      final String bindingVersion = parseBindingVersion(node);
      if (null == bindingVersion
          || bindingVersion.equals(IBMMQServerBinding010Impl.BINDING_VERSION)) {
        return parseBindingV010(node);
      }
      // use latest
      return parseBindingV010(node);
    } catch (Exception e) {
      return null;
    }
  }

  private static final IBMMQServerBinding parseBindingV010(final ObjectNode node) {
    final IBMMQServerBinding010Impl.Builder builder = new IBMMQServerBinding010Impl.Builder();
    builder.extensions(parseExtensions(node));
    builder.ccdtQueueManagerName(parseCcdtQueueManagerName(node));
    builder.cipherSpec(parseCipherSpec(node));
    builder.groupId(parseGroupId(node));
    builder.heartBeatInterval(parseHeartBeatInterval(node));
    builder.multiEndpointServer(parseMultiEndpointServer(node));
    return builder.build();
  }

  private static Boolean parseMultiEndpointServer(final ObjectNode node) {
    return booleanFrom(Fields.MULTI_ENDPOINT_SERVER.value, node);
  }

  private static Integer parseHeartBeatInterval(final ObjectNode node) {
    return integerFrom(Fields.HEARTBEAT_INTERVAL.value, node);
  }

  private static String parseGroupId(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.GROUP_ID.value, node);
  }

  private static String parseCipherSpec(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.CIPHER_SPEC.value, node);
  }

  private static String parseCcdtQueueManagerName(final ObjectNode node) {
    return valueOfKeyOrNull(Fields.CCDT_QUEUE_MANAGER_NAME.value, node);
  }

  private IBMMQServerBindingParser() {
    /* this static utility should not be instantiated */
  }
}