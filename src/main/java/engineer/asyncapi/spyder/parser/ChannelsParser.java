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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engineer.asyncapi.spyder.model.Channel;
import engineer.asyncapi.spyder.model.Channels;
import java.util.Iterator;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author johncatlin
 *
 */
final class ChannelsParser extends AsyncAPICommonObjectParser {

  static final Logger log = LoggerFactory.getLogger(ChannelsParser.class);

  static final Channels parse(final ObjectNode node) {
    if (node == null) {
      return null;
    }
    final ChannelsImpl channels = new ChannelsImpl();
    final Iterator<Map.Entry<String, JsonNode>> i = node.fields();
    while (i.hasNext()) {
      final Map.Entry<String, JsonNode> entry = i.next();
      if (isObjectNode(entry.getValue())) {
        ObjectNode channelNode = (ObjectNode) entry.getValue();
        final Channel channel = ChannelParser.parse(channelNode);
        if (channel != null) {
          channels.put(entry.getKey(), channel);
        }
      }
    }
    return channels;
  }

  private ChannelsParser() {
    /* this static utility should not be instantiated */
  }
}