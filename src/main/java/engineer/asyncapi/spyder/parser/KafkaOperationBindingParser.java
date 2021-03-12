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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import engineer.asyncapi.spyder.model.Schema;
import engineer.asyncapi.spyder.model.bindings.KafkaOperationBinding;
import engineer.asyncapi.spyder.model.bindings.KafkaOperationBinding010;
import engineer.asyncapi.spyder.model.fields.Fields;

/**
 * 
 * @author johncatlin
 *
 */
final class KafkaOperationBindingParser extends AsyncAPICommonObjectParser {

	static final KafkaOperationBinding parse(final ObjectNode node) {
		KafkaOperationBinding binding = null;
		if (node == null) {
			return null;
		}
		final String bindingVersion = parseBindingVersion(node);
		if (null == bindingVersion || bindingVersion.equals(KafkaOperationBinding010Impl.BINDING_VERSION)) {
			binding = parseBindingV010(node);
		}
		return binding;
	}

	private static final KafkaOperationBinding010 parseBindingV010(final ObjectNode node) {
		final KafkaOperationBinding010Impl.Builder builder = new KafkaOperationBinding010Impl.Builder();
		builder.groupId(parseGroupId(node));
		builder.clientId(parseClientId(node));
		builder.extensions(parseExtensions(node));
		return builder.build();
	}

	static final Schema parseClientId(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		Schema response = parseClientIdLongForm(node);
		if (null != response) {
			return response;
		}
		return parseClientIdShortForm(node);
	}

	/*
	 * full object, of form ... <code> { "groupId":
	 * {"type":"string","enum":["myGroupId"]},
	 * "clientId":{"type":"string","enum":["myClientId"]}, "bindingVersion":"0.1.0"
	 * }</code>
	 */
	private static final Schema parseClientIdLongForm(final ObjectNode node) {
		final SchemaImpl.Builder builder = new SchemaImpl.Builder();
		ObjectNode objectNode = objectNodeFrom(Fields.CLIENT_ID.value, node);
		if (null != objectNode) {
			String type = stringFrom(Fields.TYPE.value, objectNode);
			List<String> theEnum = parseEnum(objectNode);
			builder.type(type);
			builder.theEnum(theEnum);
			return builder.build();
		}
		return null;
	}

	/*
	 * string object, or form ... <code> {"clientId":"my-app-id"}</code>
	 */
	private static final Schema parseClientIdShortForm(final ObjectNode node) {
		final SchemaImpl.Builder builder = new SchemaImpl.Builder();
		List<String> values = new ArrayList<>();
		String s = stringFrom(Fields.CLIENT_ID.value, node);
		if (null != s) {
			values.add(s);
			builder.theEnum(values);
			builder.type(Fields.STRING.value);
			return builder.build();
		}
		return null;
	}

	static final Schema parseGroupId(final ObjectNode node) {
		if (null == node) {
			return null;
		}
		Schema response = parseGroupIdLongForm(node);
		if (null != response) {
			return response;
		}
		return parseGroupIdShortForm(node);
	}

	/*
	 * full object, of form ... <code> { "groupId":
	 * {"type":"string","enum":["myGroupId"]},
	 * "clientId":{"type":"string","enum":["myClientId"]}, "bindingVersion":"0.1.0"
	 * } </code>
	 */
	private static final Schema parseGroupIdLongForm(final ObjectNode node) {
		final SchemaImpl.Builder builder = new SchemaImpl.Builder();
		ObjectNode objectNode = objectNodeFrom(Fields.GROUP_ID.value, node);
		if (null != objectNode) {
			String type = stringFrom(Fields.TYPE.value, objectNode);
			List<String> theEnum = parseEnum(objectNode);
			builder.type(type);
			builder.theEnum(theEnum);
			return builder.build();
		}
		return null;
	}

	/*
	 * string object, or form ...
	 * 
	 * <code>{"groupId":"my-app-id"}</code>
	 */
	private static final Schema parseGroupIdShortForm(final ObjectNode node) {
		final SchemaImpl.Builder builder = new SchemaImpl.Builder();
		List<String> values = new ArrayList<>();
		String s = stringFrom(Fields.GROUP_ID.value, node);
		if (null != s) {
			values.add(s);
			builder.theEnum(values);
			builder.type(Fields.STRING.value);
			return builder.build();
		}
		return null;
	}

	private KafkaOperationBindingParser() {
		/* this static utility should not be instantiated */
	}
}