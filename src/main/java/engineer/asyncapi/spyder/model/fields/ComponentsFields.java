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
enum ComponentsFields {

  CHANNEL_BINDINGS("channelBindings", false),
  CORRELATION_IDS("correlationIds", false),
  EXTENSIONS("extensions", false),
  MESSAGE_BINDINGS("messageBindings", false),
  MESSAGE_TRIATS("messageTraits", false),
  MESSAGES("messages", false),
  OPERATION_BINDINGS("operationBindings", false),
  OPERATION_TRAITS("operationTraits", false),
  PARAMETERS("parameters", false),
  SCHEMAS("schemas", false),
  SECURITY_SCHEMAS("securitySchemes", false),
  SERVER_BINDINGS("serverBindings", false);

  public final String field;
  public final boolean required;

  ComponentsFields(final String field, final boolean required) {
    this.field = field;
    this.required = required;
  }
}