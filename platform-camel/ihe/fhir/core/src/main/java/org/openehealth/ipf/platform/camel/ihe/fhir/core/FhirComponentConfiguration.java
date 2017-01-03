/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openehealth.ipf.platform.camel.ihe.fhir.core;

import org.openehealth.ipf.commons.ihe.fhir.AbstractPlainProvider;
import org.openehealth.ipf.commons.ihe.fhir.ClientRequestFactory;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;

/**
 * Static configuration for FHIR components. This configuration cannot be altered in the
 * endpoint URI.
 *
 * @author Christian Ohr
 * @since 3.1
 *
 * @deprecated use {@link FhirTransactionConfiguration}
 */
public class FhirComponentConfiguration extends FhirTransactionConfiguration {

    public FhirComponentConfiguration(AbstractPlainProvider resourceProvider, ClientRequestFactory<?> clientRequestFactory) {
        super(resourceProvider, clientRequestFactory);
    }
}