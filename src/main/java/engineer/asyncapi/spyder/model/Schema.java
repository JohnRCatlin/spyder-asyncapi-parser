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

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author johncatlin
 *
 */
public interface Schema {

  Boolean getAdditionalItems();

  Boolean getAdditionalProperties();

  List<String> getAllOf();

  List<String> getAnyOf();

  Boolean getConst();

  Schema getContains();

  String getDefault();

  String getDescription();

  String getDiscriminator();

  List<String> getEnum();

  Example getExample();

  Examples getExamples();

  Extensions getExtensions();

  ExternalDocs getExternalDocs();

  String getFormat();

  Schema getItems();

  BigDecimal getMaximum();

  Integer getMaxItems();

  Integer getMaxLength();

  Integer getMaxProperties();

  BigDecimal getMinimum();

  Integer getMinItems();

  Integer getMinLength();

  Integer getMinProperties();

  BigDecimal getMultipleOf();

  List<String> getNot();

  List<String> getOneOf();

  String getPattern();

  Map<String, Schema> getProperties();

  Set<String> getPropertyNames();

  Boolean getReadOnly();

  String getRef();

  List<String> getRequiredProperties();

  String getTitle();

  String getType();

  Boolean getUniqueItems();

  Boolean getWriteOnly();

  Boolean isDeprecated();

  Boolean isExclusiveMaximum();

  Boolean isExclusiveMinimum();

}