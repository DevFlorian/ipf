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
package org.openehealth.ipf.tutorials.hl7.route

import org.apache.camel.Exchange
import org.apache.camel.spring.SpringRouteBuilder
import org.openehealth.ipf.tutorials.hl7.validation.SampleRulesBuilder

/**
 * @author Martin Krasser
 */
public class SampleRouteBuilder extends SpringRouteBuilder {

     void configure() {

        from('direct:input')
            .unmarshal().ghl7()
            .validate().ghl7().staticProfile(lookup(SampleRulesBuilder))
            .transmogrify { msg ->
                msg.PV1[3][2] = '' // clear room nr.
                msg.PV1[3][3] = '' // clear bed nr.
                msg.PID[7][1] = msg.PID[7][1].value.substring(0, 8) // format birth date
                msg.PID[8]    = msg.PID[8].mapGender()              // map gender
                msg
            }
            .setHeader(Exchange.FILE_NAME) {exchange -> 
                exchange.in.body.MSH[4].value + '.hl7'
            }
            .marshal().ghl7()
            .to('file:target/output')
            
    }
    
}
