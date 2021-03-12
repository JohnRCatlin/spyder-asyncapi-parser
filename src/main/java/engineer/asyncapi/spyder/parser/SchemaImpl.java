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

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engineer.asyncapi.spyder.model.Example;
import engineer.asyncapi.spyder.model.Examples;
import engineer.asyncapi.spyder.model.Extensions;
import engineer.asyncapi.spyder.model.ExternalDocs;
import engineer.asyncapi.spyder.model.Schema;

class SchemaImpl implements Schema {

	/**
	 * 
	 * @author johncatlin
	 *
	 */
	static final class Builder {

		private SchemaImpl schema = new SchemaImpl();

		final Builder additionalItems(final Boolean additionalItems) {
			schema.additionalItems = additionalItems;
			return this;
		}

		final Builder additionalProperties(final Boolean additionalProperties) {
			schema.additionalProperties = additionalProperties;
			return this;
		}

		final Builder allOf(final List<String> allOf) {
			schema.allOf = allOf;
			return this;
		}

		final Builder anyOf(final List<String> anyOf) {
			schema.anyOf = anyOf;
			return this;
		}

		final Schema build() {
			return this.schema;
		}

		final Builder deprecated(final Boolean deprecated) {
			schema.deprecated = deprecated;
			return this;
		}

		final Builder description(final String description) {
			schema.description = description;
			return this;
		}

		final Builder discriminator(final String discriminator) {
			schema.discriminator = discriminator;
			return this;
		}

		final Builder exclusiveMaximum(final Boolean exclusiveMaximum) {
			schema.exclusiveMaximum = exclusiveMaximum;
			return this;
		}

		final Builder exclusiveMinimum(final Boolean exclusiveMinimum) {
			schema.exclusiveMinimum = exclusiveMinimum;
			return this;
		}

		final Builder extensions(final Extensions extensions) {
			schema.extensions = extensions;
			return this;
		}

		final Builder externalDocs(final ExternalDocs externalDocs) {
			schema.externalDocs = externalDocs;
			return this;
		}

		final Builder format(final String format) {
			schema.format = format;
			return this;
		}

		final Builder items(final Schema items) {
			schema.items = items;
			return this;
		}

		final Builder maximum(final BigDecimal maximum) {
			schema.maximum = maximum;
			return this;
		}

		final Builder maxItems(final Integer maxItems) {
			schema.maxItems = maxItems;
			return this;
		}

		final Builder maxLength(final Integer maxLength) {
			schema.maxLength = maxLength;
			return this;
		}

		final Builder maxProperties(final Integer maxProperties) {
			schema.maxProperties = maxProperties;
			return this;
		}

		final Builder minimum(final BigDecimal minimum) {
			schema.minimum = minimum;
			return this;
		}

		final Builder minItems(final Integer minItems) {
			schema.minItems = minItems;
			return this;
		}

		final Builder minLength(final Integer minLength) {
			schema.minLength = minLength;
			return this;
		}

		final Builder minProperties(final Integer minProperties) {
			schema.minProperties = minProperties;
			return this;
		}

		final Builder multipleOf(final BigDecimal multipleOf) {
			schema.multipleOf = multipleOf;
			return this;
		}

		final Builder not(final List<String> not) {
			schema.not = not;
			return this;
		}

		final Builder oneOf(final List<String> oneOf) {
			schema.oneOf = oneOf;
			return this;
		}

		final Builder pattern(final String pattern) {
			schema.pattern = pattern;
			return this;
		}

		final Builder properties(final Map<String, Schema> properties) {
			schema.properties = properties;
			return this;
		}

		final Builder readOnly(final Boolean readOnly) {
			schema.readOnly = readOnly;
			return this;
		}

		final Builder ref(final String ref) {
			schema.ref = ref;
			return this;
		}

		final Builder required(final List<String> required) {
			schema.required = required;
			return this;
		}

		final Builder theConst(final Boolean theConst) {
			schema.theConst = theConst;
			return this;
		}

		final Builder theDefault(String theDefault) {
			schema.theDefault = theDefault;
			return this;
		}

		final Builder theEnum(final List<String> theEnum) {
			schema.theEnum = theEnum;
			return this;
		}

		final Builder title(final String title) {
			schema.title = title;
			return this;
		}

		final Builder type(final String type) {
			schema.type = type;
			return this;
		}

		final Builder uniqueItems(final Boolean uniqueItems) {
			schema.uniqueItems = uniqueItems;
			return this;
		}

		final Builder writeOnly(final Boolean writeOnly) {
			schema.writeOnly = writeOnly;
			return this;
		}
	}

	private Boolean additionalItems = null;
	private Boolean additionalProperties = null;
	private List<String> allOf = null;
	private List<String> anyOf = null;
	private Schema contains = null;
	private Boolean deprecated = null;
	private String description = null;
	private String discriminator = null;
	private Example example = null;
	private Examples examples = null;
	private Boolean exclusiveMaximum = null;
	private Boolean exclusiveMinimum = null;
	private Extensions extensions = null;
	private ExternalDocs externalDocs = null;
	private String format = null;
	private Schema items = null;
	private BigDecimal maximum = null;
	private Integer maxItems = null;
	private Integer maxLength = null;
	private Integer maxProperties = null;
	private BigDecimal minimum = null;
	private Integer minItems = null;
	private Integer minLength = null;
	private Integer minProperties = null;
	private BigDecimal multipleOf = null;
	private List<String> not = null;
	private List<String> oneOf = null;
	private String pattern = null;
	private Map<String, Schema> properties = null;
	private Boolean readOnly = null;
	private String ref = null;
	private List<String> required = null;
	private Boolean theConst = null;
	private String theDefault;
	private List<String> theEnum = null;
	private String title = null;
	private String type = null;
	private Boolean uniqueItems = null;
	private Boolean writeOnly = null;

	private SchemaImpl() {
		/* Use the builder for construction. */
	}

	@Override
	public Boolean getAdditionalItems() {
		return this.additionalItems;
	}

	@Override
	public Boolean getAdditionalProperties() {
		return this.additionalProperties;
	}

	@Override
	public List<String> getAllOf() {
		return this.allOf;
	}

	@Override
	public List<String> getAnyOf() {
		return this.anyOf;
	}

	@Override
	public Boolean getConst() {
		return this.theConst;
	}

	@Override
	public Schema getContains() {
		return this.contains;
	}

	@Override
	public String getDefault() {
		return this.theDefault;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String getDiscriminator() {
		return this.discriminator;
	}

	@Override
	public List<String> getEnum() {
		return this.theEnum;
	}

	@Override
	public Example getExample() {
		return this.example;
	}

	@Override
	public Examples getExamples() {
		return this.examples;
	}

	@Override
	public Extensions getExtensions() {
		return this.extensions;
	}

	@Override
	public ExternalDocs getExternalDocs() {
		return this.externalDocs;
	}

	@Override
	public String getFormat() {
		return this.format;
	}

	@Override
	public Schema getItems() {
		return this.items;
	}

	@Override
	public BigDecimal getMaximum() {
		return this.maximum;
	}

	@Override
	public Integer getMaxItems() {
		return this.maxItems;
	}

	@Override
	public Integer getMaxLength() {
		return this.maxLength;
	}

	@Override
	public Integer getMaxProperties() {
		return this.maxProperties;
	}

	@Override
	public BigDecimal getMinimum() {
		return this.minimum;
	}

	@Override
	public Integer getMinItems() {
		return this.minItems;
	}

	@Override
	public Integer getMinLength() {
		return this.minLength;
	}

	@Override
	public Integer getMinProperties() {
		return this.minProperties;
	}

	@Override
	public BigDecimal getMultipleOf() {
		return multipleOf;
	}

	@Override
	public List<String> getNot() {
		return this.not;
	}

	@Override
	public List<String> getOneOf() {
		return this.oneOf;
	}

	@Override
	public String getPattern() {
		return this.pattern;
	}

	@Override
	public Map<String, Schema> getProperties() {
		return this.properties;
	}

	@Override
	public Set<String> getPropertyNames() {
		return (null == this.properties) ? null : this.properties.keySet();
	}

	@Override
	public Boolean getReadOnly() {
		return this.readOnly;
	}

	@Override
	public String getRef() {
		return this.ref;
	}

	@Override
	public List<String> getRequiredProperties() {
		return this.required;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public Boolean getUniqueItems() {
		return this.uniqueItems;
	}

	@Override
	public Boolean getWriteOnly() {
		return this.writeOnly;
	}

	@Override
	public Boolean isDeprecated() {
		return this.deprecated;
	}

	@Override
	public Boolean isExclusiveMaximum() {
		return this.exclusiveMaximum;
	}

	@Override
	public Boolean isExclusiveMinimum() {
		return this.exclusiveMinimum;
	}

	@Override
	public final String toString() {
		return ToStringFormatter.toString(this);
	}
}
