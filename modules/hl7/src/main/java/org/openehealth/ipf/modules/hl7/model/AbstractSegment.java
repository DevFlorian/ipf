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
package org.openehealth.ipf.modules.hl7.model;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.AbstractMessage;
import ca.uhn.hl7v2.model.Group;
import ca.uhn.hl7v2.model.Type;
import ca.uhn.hl7v2.parser.ModelClassFactory;

/**
 * Convenience subclass that uses Java 5 Generics to abbreviate tedious HL7v2
 * parsing.
 * 
 * @author Christian Ohr
 * @deprecated use {@link ca.uhn.hl7v2.model.AbstractSegment} directly
 */
@SuppressWarnings("serial")
public abstract class AbstractSegment extends ca.uhn.hl7v2.model.AbstractSegment {

    public AbstractSegment(Group parent, ModelClassFactory factory) {
        super(parent, factory);
    }
    
    protected void add(String typeName, boolean required, int maxReps, int length, Object[] constructorArgs) throws HL7Exception {
    	add(typeName, required, maxReps, length, constructorArgs, null);
    }
    
    protected void add(String typeName, boolean required, int maxReps, int length, Object[] constructorArgs, String name) throws HL7Exception {
    	AbstractMessage message = (AbstractMessage)getMessage();
    	Class<? extends Type> typeClass = message.getModelClassFactory().getTypeClass(typeName, message.getVersion());
    	add(typeClass, required, maxReps, length, constructorArgs, name);
    }

}
