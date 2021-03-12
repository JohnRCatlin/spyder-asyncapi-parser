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
enum MessageFields {

	BINDINGS("bindings", false),
	CONTENT_TYPE("contentType", false),
	CORRELATION_ID("correlationId", false),
	DESCRIPTION("description", false),
	EXAMPLES("examples", false),
	EXTENSIONS("extensions", false),
	EXTERNAL_DOCS("externalDocs", false),
	HEADERS("headers", false),
	NAME("name", false),
	PAYLOAD("payload", false),
	SCHEMA_FORMAT("schemaFormat", false),
	SUMMARY("summary", false),
	TAGS("tags", false),
	TITLE("title", false);

	public final String field;
	public final boolean required;

	MessageFields(String field, boolean required) {
		this.field = field;
		this.required = required;
	}
}