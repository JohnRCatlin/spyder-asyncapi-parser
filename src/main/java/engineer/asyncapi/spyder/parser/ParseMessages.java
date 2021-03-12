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

/**
 * 
 * @author johncatlin
 *
 */
enum ParseMessages {

	CHANNEL_BINDINGS_PARSER_NOT_IMPLEMENTED(
			"The {} channel binding you have requested is not yet implemented. Please contribute on github."),
	MESSAGE_BINDINGS_PARSER_NOT_IMPLEMENTED(
			"The {} message binding you have requested is not yet implemented. Please contribute on github."),
	SERVER_BINDINGS_PARSER_NOT_IMPLEMENTED(
			"The {} server binding you have requested is not yet implemented. Please contribute on github.");

	public final String message;

	ParseMessages(String message) {
		this.message = message;
	}

}
