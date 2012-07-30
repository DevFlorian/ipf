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
package org.openehealth.ipf.commons.ihe.xds.iti39;

import org.openehealth.ipf.commons.ihe.core.atna.AuditorManager;
import org.openehealth.ipf.commons.ihe.xds.core.audit.XdsRetrieveAuditDataset;
import org.openehealth.ipf.commons.ihe.xds.core.audit.XdsRetrieveAuditStrategy30;

/**
 * Server audit strategy for ITI-39.
 * @author Dmytro Rud
 */
public class Iti39ServerAuditStrategy extends XdsRetrieveAuditStrategy30 {

    private static final String[] NECESSARY_AUDIT_FIELDS = new String[] {
        "EventOutcomeCode",
        "ClientIpAddress",
        "ServiceEndpointUrl",
        "DocumentUuids",
        "RepositoryUuids",
        "HomeCommunityUuids"};

    
    public Iti39ServerAuditStrategy(boolean allowIncompleteAudit) {
        super(true, allowIncompleteAudit);
    }

    @Override
    public void doAudit(XdsRetrieveAuditDataset auditDataset) {
        String[] homeCommunityIds = auditDataset.getHomeCommunityUuids();
        AuditorManager.getXCARespondingGatewayAuditor().auditCrossGatewayRetrieveEvent(
                auditDataset.getEventOutcomeCode(),
                auditDataset.getUserId(),
                auditDataset.getClientIpAddress(),
                auditDataset.getServiceEndpointUrl(),
                auditDataset.getUserName(),
                auditDataset.getDocumentUuids(),
                auditDataset.getRepositoryUuids(),
                ((homeCommunityIds != null) && (homeCommunityIds.length != 0)) ? homeCommunityIds[0] : null);
    }

    @Override
    public String[] getNecessaryAuditFieldNames() {
        return NECESSARY_AUDIT_FIELDS;
    }
}
