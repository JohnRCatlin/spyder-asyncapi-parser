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
package engineer.asyncapi.spyder.model;

import engineer.asyncapi.spyder.model.bindings.ChannelBindings;
import engineer.asyncapi.spyder.model.bindings.MessageBindings;
import engineer.asyncapi.spyder.model.bindings.OperationBindings;
import engineer.asyncapi.spyder.model.bindings.ServerBindings;

/**
 * 
 * @author johncatlin
 *
 */
public interface Components {

	ChannelBindings getChannelBindings();

	CorrelationIds getCorrelationIds();

	Extensions getExtensions();

	MessageBindings getMessageBindings();

	Messages getMessages();

	MessageTraits getMessageTraits();

	OperationBindings getOperationBindings();

	OperationTraits getOperationTraits();

	Parameters getParameters();

	Schemas getSchemas();

	SecuritySchemes getSecuritySchemes();

	ServerBindings getServerBindings();

}