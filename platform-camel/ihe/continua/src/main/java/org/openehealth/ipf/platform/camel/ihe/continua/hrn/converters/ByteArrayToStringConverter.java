/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.platform.camel.ihe.continua.hrn.converters;

import org.springframework.core.convert.converter.Converter;

import java.nio.charset.Charset;

/**
 * @author Stefan Ivanov
 */
public class ByteArrayToStringConverter implements Converter<byte[], String> {

    @Override
    public String convert(byte[] source) {
        return new String(source, Charset.defaultCharset());
    }
}
