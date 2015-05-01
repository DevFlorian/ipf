/*
 * Copyright 2009 the original author or authors.
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
package org.openehealth.ipf.commons.xml;

import org.openehealth.ipf.commons.xml.svrl.SchematronOutput;

import javax.xml.transform.dom.DOMResult;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

/**
 * @author Christian Ohr
 */
class ResultHolderFactory {

    @SuppressWarnings("unchecked")
    public static <T> ResultHolder<T> create(Class<T> clazz) {
        if (String.class == clazz) {
            return (ResultHolder<T>) new StringResultHolder();
        } else if (InputStream.class == clazz) {
            return (ResultHolder<T>) new InputStreamResultHolder();
        } else if (OutputStream.class == clazz) {
            return (ResultHolder<T>) new OutputStreamResultHolder();
        } else if (Writer.class == clazz) {
            return (ResultHolder<T>) new WriterResultHolder();
        } else if (DOMResult.class == clazz) {
            return (ResultHolder<T>) new DOMResultHolder();
        } else if (SchematronOutput.class == clazz) {
            return (ResultHolder<T>) new SvrlResultHolder();
        } else {
            return null;
        }
    }

}
