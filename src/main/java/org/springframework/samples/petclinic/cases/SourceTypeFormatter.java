/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

/**
 */
@Component
public class SourceTypeFormatter implements Formatter<SourceType> {

	private final CaseRepository owners;

	@Autowired
	public SourceTypeFormatter(CaseRepository owners) {
		this.owners = owners;
	}

	@Override
	public String print(SourceType sourceType, Locale locale) {
		return sourceType.getName();
	}

	@Override
	public SourceType parse(String text, Locale locale) throws ParseException {
		Collection<SourceType> sourceTypes = this.owners.findASourceTypes();
		for (SourceType type : sourceTypes) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
