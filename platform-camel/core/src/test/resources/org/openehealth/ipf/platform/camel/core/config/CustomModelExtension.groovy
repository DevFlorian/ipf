/*
 * Copyright 2008 the original author or authors.
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
package org.openehealth.ipf.platform.camel.core.config

import org.apache.camel.builder.RouteBuilder
import org.openehealth.ipf.commons.core.extend.config.DynamicExtension

/**
 * @author Martin Krasser
 */
class CustomModelExtension implements DynamicExtension {

    static RouteBuilder direct(RouteBuilder delegate, String endpointName) {
        delegate.from('direct:' + endpointName)
    }

    @Override
    String getModuleName() {
        return "TestCustomExtension"
    }

    @Override
    String getModuleVersion() {
        return "1.0.0"
    }

    @Override
    boolean isStatic() {
        return true
    }
}