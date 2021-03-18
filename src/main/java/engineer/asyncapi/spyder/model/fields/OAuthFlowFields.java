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
enum OAuthFlowFields {

  AUTHORIZATION_CODE("authorizationCode", false),
  AUTHORIZATION_URL("authorizationUrl", false),
  CLIENT_CREDENTIALS("clientCredentials", false),
  IMPILICT("implicit", false),
  PASSWORD("password", false),
  REFRESH_URL("refreshUrl", false),
  SCOPES("scopes", false),
  TOKEN_URL("tokenUrl", false);

  public final boolean required;
  public final String value;

  OAuthFlowFields(final String value, final boolean required) {
    this.value = value;
    this.required = required;
  }

}