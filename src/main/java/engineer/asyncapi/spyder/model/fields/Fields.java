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
package engineer.asyncapi.spyder.model.fields;

/**
 * 
 * @author johncatlin
 *
 */
public enum Fields {

	ACK("ack"),
	ADDITIONAL_ITEMS("additionalItems"),
	ADDITIONAL_PROPERTIES("additionalProperties"),
	ALL_OF("allOf"),
	ANY_OF("anyOf"),
	ASYNCAPI("asyncapi"),
	AUTHORIZATION_CODE("authorizationCode"),
	AUTHORIZATION_URL("authorizationUrl"),
	AUTO_DELETE("autoDelete"),
	BINDING_VERSION("bindingVersion"),
	BINDINGS("bindings"),
	BOOLEAN("boolean"),
	CHANNEL_BINDINGS("channelBindings"),
	CHANNELS("channels"),
	CLIENT_CREDENTIALS("clientCredentials"),
	CLIENT_ID("clientId"),
	COMPONENTS("components"),
	CONST("const"),
	CONTACT("contact"),
	CONTENT_ENCODING("contentEncoding"),
	CONTENT_TYPE("contentType"),
	CORRELATION_ID("correlationId"),
	CORRELATION_IDS("correlationIds"),
	DEFAULT("default"),
	DEFAULT_CONTENT_TYPE("defaultContentType"),
	DELIVERY_MODE("deliveryMode"),
	DEPRECATED("deprecated"),
	DESCRIPTION("description"),
	DISCRIMINATOR("discriminator"),
	EMAIL("email"),
	ENUM("enum"),
	EXCHANGE("exchange"),
	EXCLUSIVE("exclusive"),
	EXCLUSIVE_MAXIMUM("exclusiveMaximum"),
	EXCLUSIVE_MINIMUM("exclusiveMinimum"),
	EXPIRATION("expiration"),
	EXTENSIONS("extensions"),
	EXTENSIONS_PRFIX("x-"),
	EXTERNAL_DOCS("externalDocs"),
	FORMAT("format"),
	GROUP_ID("groupId"),
	HEADERS("headers"),
	ID("id"),
	IMPILICT("implicit"),
	IN("in"),
	INFO("info"),
	IS("is"),
	ITEMS("items"),
	KEY("key"),
	LICENSE("license"),
	LOCATION("location"),
	MANDATORY("mandatory"),
	MAX_ITEMS("maxItems"),
	MAX_LENGTH("maxLength"),
	MAX_PROPERTIES("maxProperties"),
	MAXIMUM("maximum"),
	MESSAGE("message"),
	MESSAGE_BINDINGS("messageBindings"),
	MESSAGE_TRAITS("messageTraits"),
	MESSAGE_TYPE("messageType"),
	MESSAGES("messages"),
	MIN_ITEMS("minItems"),
	MIN_LENGTH("minLength"),
	MIN_PROPERTIES("minProperties"),
	MINIMUM("minimum"),
	MULTIPLE_OF("multipleOf"),
	NAME("name"),
	NOT("not"),
	NULL("null"),
	NUMBER("number"),
	ONE_OF("oneOf"),
	OPERATION_BINDINGS("operationBindings"),
	OPERATION_ID("operationId"),
	OPERATION_TRAITS("operationTraits"),
	PARAMETERS("parameters"),
	PASSWORD("password"),
	PATTERN("pattern"),
	PAYLOAD("payload"),
	PRIORITY("priority"),
	PROPERTIES("properties"),
	PROTOCOL("protocol"),
	PROTOCOL_VERSION("protocolVersion"),
	PUBLISH("publish"),
	QUEUE("queue"),
	READ_ONLY("readOnly"),
	REF("$ref"),
	REFRESH_URL("refreshUrl"),
	REPLY_TO("replyTo"),
	REQUIRED("required"),
	REQUIRED_PROPERTIES("requiredProperties"),
	SCHEMA("schema"),
	SCHEMA_FORMAT("schemaFormat"),
	SCHEMAS("schemas"),
	SCOPES("scopes"),
	SECURITY("security"),
	SECURITY_SCHEMES("securitySchemes"),
	SERVER_BINDINGS("serverBindings"),
	SERVERS("servers"),
	STRING("string"),
	SUBSCRIBE("subscribe"),
	SUMMARY("summary"),
	TAGS("tags"),
	TERMS_OF_SERVICE("termsOfService"),
	TIMESTAMP("timestamp"),
	TITLE("title"),
	TOKEN_URL("tokenUrl"),
	TRAITS("traits"),
	TYPE("type"),
	UNIQUE_ITEMS("uniqueItems"),
	URL("url"),
	USER_ID("userId"),
	VARIABLES("variables"),
	VERSION("version"),
	VHOST("vhost"),
	WRITE_ONLY("writeOnly"),
	DURABLE("durable");

	public final String value;

	Fields(String value) {
		this.value = value;
	}
}