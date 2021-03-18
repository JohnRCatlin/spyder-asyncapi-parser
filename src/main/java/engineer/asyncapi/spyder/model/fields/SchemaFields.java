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
enum SchemaFields {

  ADDITIONAL_PROPERTIES("additionalProperties", false),
  ALL_OF("allOf", false),
  ANY_OF("anyOf", false),
  DEFAULT("_default", false),
  DEPRECATED("deprecated", false),
  DESCRIPTION("description", false),
  DISCRIMINATOR("discriminator", false),
  ENUM("enum", false),
  EXAMPLE("example", false),
  EXCLUSIVE_MAXIMUM("exclusiveMaximum", false),
  EXCLUSIVE_MINIMUM("exclusiveMinimum", false),
  EXTENSIONS("extensions", false),
  EXTERNAL_DOCS("externalDocs", false),
  FROMAT("format", false),
  ITEMS("items", false),
  MAX_ITEMS("maxItems", false),
  MAX_PROPERTIES("maxProperties", false),
  MAXIMUM("maximum", false),
  MAXIMUM_LENGHT("maxLength", false),
  MIN_ITEM("minItems", false),
  MIN_PROPERTIES("minProperties", false),
  MINIMUM("minimum", false),
  MINIMUM_LENGHT("minLength", false),
  MULTIPLE_OF("multipleOf", false),
  NOT("not", false),
  NULLABLE("nullable", false),
  ONE_OF("oneOf", false),
  PATTERN("pattern", false),
  PROPERTIES("properties", false),
  READ_ONLY("readOnly", false),
  REF("$ref", false),
  REQUIRED("required", false),
  TITLE("title", false),
  TYPE("type", false),
  UNIQUE_ITEMS("uniqueItems", false),
  WRITE_ONLY("writeOnly", false);

  public final String field;
  public final boolean required;

  SchemaFields(final String field, final boolean required) {
    this.field = field;
    this.required = required;
  }
}