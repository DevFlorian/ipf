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
package org.openehealth.ipf.modules.hl7.validation.builder

import org.openehealth.ipf.modules.hl7.validation.DefaultValidationContext

/**
 * Abstract helper class to separate ValidationContext creation from
 * ValidationRule definition.
 * 
 * @author Christian Ohr
 * @deprecated use validation builders of HAPI (cf. {@link ca.uhn.hl7v2.validation.builder.ValidationRuleBuilder})
 */
public abstract class ValidationContextBuilder{
    
    /**
     * @return a RuleBuilder instance that defines the validation rules
     * for the 
     */
    public abstract RuleBuilder forContext(DefaultValidationContext context)
    
}
