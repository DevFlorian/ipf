/*
 * Copyright 2015 the original author or authors.
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

import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;
import org.openehealth.ipf.commons.ihe.fhir.atna.FhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.core.InterceptorFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Configuration of a FHIR endpoint instance
 *
 * @since 3.1
 */
@UriParams
public class FhirEndpointConfiguration<AuditDatasetType extends FhirAuditDataset> implements Serializable {

    private String serviceName;

    @UriParam(defaultValue = "false")
    private boolean audit = false;

    @UriParam(defaultValue = "FhirServlet")
    private String servletName = CamelFhirServlet.DEFAULT_SERVLET_NAME;

    @UriParam
    private AbstractResourceProvider<AuditDatasetType> resourceProvider;

    @UriParam
    private List<InterceptorFactory> customInterceptorFactories;

    protected FhirEndpointConfiguration(FhirComponent<AuditDatasetType> component, String serviceName, Map<String, Object> parameters) throws Exception {
        this.serviceName = serviceName;
        audit = component.getAndRemoveParameter(parameters, "audit", boolean.class, true);
        servletName = component.getAndRemoveParameter(parameters, "servletName", String.class, CamelFhirServlet.DEFAULT_SERVLET_NAME);
        resourceProvider = component.getAndRemoveOrResolveReferenceParameter(
                parameters, "resourceProvider", AbstractResourceProvider.class, null);
        customInterceptorFactories = component.resolveAndRemoveReferenceListParameter(
                parameters, "interceptorFactories", InterceptorFactory.class);
    }

    public boolean isAudit() {
        return audit;
    }

    public AbstractResourceProvider<AuditDatasetType> getResourceProvider() {
        return resourceProvider;
    }

    public List<InterceptorFactory> getCustomInterceptorFactories() {
        return customInterceptorFactories;
    }

    public String getServletName() {
        return servletName;
    }

    public String getServiceName() {
        return serviceName;
    }
}
