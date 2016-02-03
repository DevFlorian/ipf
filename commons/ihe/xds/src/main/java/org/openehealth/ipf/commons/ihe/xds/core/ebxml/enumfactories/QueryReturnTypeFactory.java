/*
 * Copyright 2016 the original author or authors.
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
package org.openehealth.ipf.commons.ihe.xds.core.ebxml.enumfactories;

import org.openehealth.ipf.commons.ihe.xds.core.metadata.XdsEnum;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.XdsEnumFactory;
import org.openehealth.ipf.commons.ihe.xds.core.requests.query.QueryReturnType;

public class QueryReturnTypeFactory extends XdsEnumFactory<QueryReturnType> {

    @Override
    public QueryReturnType[] getOfficialValues() {
        return QueryReturnType.OFFICIAL_VALUES;
    }

    @Override
    public QueryReturnType createCode(XdsEnum.Type type, String ebXML) {
        return new QueryReturnType(type, ebXML);
    }

    @Override
    public String getEbXML(QueryReturnType code) {
        return code.getEbXML30();
    }

}
