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

/**
 * <p>
 * This is the model's root object.
 * </p>
 * <p>
 * To get started, see the code sample at <a href=
 * "../parser/package-summary.html">AsyncAPIParserFactory</a>.
 * </p>
 * 
 * @author johncatlin
 *
 */
public interface AsyncAPI {

	String getAsyncapi();

	Channels getChannels();

	Components getComponents();

	String getDefaultContentType();

	Extensions getExtensions();

	ExternalDocs getExternalDocs();

	Identifier getIdentifier();

	Info getInfo();

	Servers getServers();

	Tags getTags();

	String toString();

}