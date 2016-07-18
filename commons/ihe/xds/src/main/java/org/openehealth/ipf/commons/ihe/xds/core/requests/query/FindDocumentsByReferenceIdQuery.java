/*
 * Copyright 2013 the original author or authors.
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
package org.openehealth.ipf.commons.ihe.xds.core.requests.query;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.ReferenceId;

import javax.xml.bind.annotation.*;

/**
 * Represents a stored query for FindDocumentsByReferenceIdQuery.
 * @author Dmytro Rud
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FindDocumentsByReferenceIdQuery", propOrder = {"referenceIds"})
@XmlRootElement(name = "findDocumentsByReferenceIdQuery")
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
public class FindDocumentsByReferenceIdQuery extends FindDocumentsQuery {
    private static final long serialVersionUID = 8898792914033157098L;

    @XmlElement(name = "referenceId")
    @Getter @Setter private QueryList<ReferenceId> referenceIds;


    /**
     * Constructs the query.
     */
    public FindDocumentsByReferenceIdQuery() {
        super(QueryType.FIND_DOCUMENTS_BY_REFERENCE_ID);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
