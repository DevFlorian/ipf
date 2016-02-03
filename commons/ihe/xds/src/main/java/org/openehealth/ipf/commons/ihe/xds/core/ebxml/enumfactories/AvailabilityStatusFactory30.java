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

import org.openehealth.ipf.commons.ihe.xds.core.metadata.AvailabilityStatus;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.XdsEnum;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.XdsEnumFactory;

public class AvailabilityStatusFactory30 extends XdsEnumFactory<AvailabilityStatus> {

    @Override
    public AvailabilityStatus[] getOfficialValues() {
        return AvailabilityStatus.OFFICIAL_VALUES;
    }

    @Override
    public AvailabilityStatus createCode(XdsEnum.Type type, String ebXML) {
        return new AvailabilityStatus(type, ebXML, ebXML);
    }

    @Override
    public String getEbXML(AvailabilityStatus code) {
        return code.getEbXML30();
    }

}
