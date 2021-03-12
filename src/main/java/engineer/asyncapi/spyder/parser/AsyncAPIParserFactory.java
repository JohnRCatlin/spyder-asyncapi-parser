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
public final class AsyncAPIParserFactory {

	public static final AsyncAPIParser create(final SupportedAsyncAPIVersions version) {
		if (version.equals(SupportedAsyncAPIVersions.V2_0_0)) {
			return new AsyncAPIv20ParserImpl();
		}
		return null;
	}

	private AsyncAPIParserFactory() {
		/* this static utility should not be instantiated */
	}
}
