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

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author johncatlin
 *
 */
final class RFC3339Converter {

	private static final Pattern RFC3339_DATE_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})$");

	static final Date toDate(final String rfc3339DateString) {
		final Matcher matcher = RFC3339_DATE_PATTERN.matcher(rfc3339DateString);
		if (matcher.matches()) {
			final Integer year = Integer.parseInt(matcher.group(1));
			final Integer month = Integer.parseInt(matcher.group(2));
			final Integer day = Integer.parseInt(matcher.group(3));
			try {
				return new Calendar.Builder().setDate(year, month, day).build().getTime();
			} catch (final Exception ignore) {
				return null;
			}
		}
		return null;
	}

	static final OffsetDateTime toOffsetDateTime(final String rfc3339DateString) {
		try {
			return OffsetDateTime.parse(rfc3339DateString);
		} catch (final Exception ignore) {
			return null;
		}
	}

	private RFC3339Converter() {
		// static utility should not be instantiated.
	}
}
