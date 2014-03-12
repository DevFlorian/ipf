/*
 * Copyright 2012 the original author or authors.
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
package org.openehealth.ipf.commons.ihe.xds.core.requests;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.RetrieveImagingDocumentSetRequestType;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Request object for the Retrieve Imaging Document Set transaction.
 * <p>
 * Lists are pre-created and can therefore never be <code>null</code>.
 * @author Clay Sebourn
 */

@XmlRootElement(name = "retrieveImagingDocumentSet")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrieveImagingDocumentSet", propOrder = {"retrieveStudies", "transferSyntaxIds"})
public class RetrieveImagingDocumentSet implements Serializable {
    private static final long serialVersionUID = -8999352499981099419L;

    @XmlElementRef
    private final List<RetrieveStudy> retrieveStudies = new ArrayList<RetrieveStudy>();

    @XmlElement
    private final List<String> transferSyntaxIds = new ArrayList<String>();

    /**
     * @return the list of StudyRequests to retrieve.
     */
    public List<RetrieveStudy> getRetrieveStudies() {
        return retrieveStudies;
    }

    /**
     *
     * @return the list of TransferSyntaxUID
     */
    public List<String> getTransferSyntaxIds() {
        return transferSyntaxIds;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((retrieveStudies == null) ? 0 : retrieveStudies.hashCode());
        result = prime * result + ((transferSyntaxIds == null) ? 0 : transferSyntaxIds.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RetrieveImagingDocumentSet other = (RetrieveImagingDocumentSet) obj;

        if (retrieveStudies == null) {
            if (other.retrieveStudies != null)
                return false;
        } else if (!retrieveStudies.equals(other.retrieveStudies))
            return false;
        if (transferSyntaxIds == null) {
            if (other.transferSyntaxIds != null)
                return false;
        } else if (!transferSyntaxIds.equals(other.transferSyntaxIds))
            return false;
        return true;
    }
        
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
