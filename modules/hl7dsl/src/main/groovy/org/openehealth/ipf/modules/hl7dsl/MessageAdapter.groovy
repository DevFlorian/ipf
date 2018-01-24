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
package org.openehealth.ipf.modules.hl7dsl

import static org.openehealth.ipf.modules.hl7dsl.AdapterHelper.adaptStructure
import static org.openehealth.ipf.modules.hl7dsl.util.Messages.copyMessage
import static org.openehealth.ipf.modules.hl7dsl.AdapterHelper.newInstance
import ca.uhn.hl7v2.model.Message
import ca.uhn.hl7v2.parser.Parser
import ca.uhn.hl7v2.model.AbstractMessage

/**
 * @author Martin Krasser
 * @author Mitko Kolev
 * @deprecated the ipd-modules-hl7dsl module is deprecated
 */
class MessageAdapter<T extends AbstractMessage> extends GroupAdapter<T> implements Writable {

    Parser parser

    MessageAdapter(Message message) {
        this(message.parser, message)
    }

    MessageAdapter(Parser parser, Message message) {
        super(message)
        this.parser = (parser != null ? parser : message.parser)
    }

    MessageAdapter empty() {
        adaptStructure(newInstance(target, target.modelClassFactory))
    }
    
    MessageAdapter copy() {
        MessageAdapter copy = empty()
        adaptStructure(copyMessage(this.target, copy.target))
        copy.parser = this.parser
        return copy
    }

    Writer writeTo(Writer writer) {
        String s = parser.encode(target)
        writer.write(s)
        writer.flush()
        writer
    }
    
    String toString() {
        def writer = new StringWriter()
        writer << this
        writer.buffer.toString()
    }

    boolean matches(String code, String type, String v) {    	
    	(code == '*' || code == get('MSH')[9][1].value) && 
    	(type == '*' || type == get('MSH')[9][2].value) && 
    	(v == '*' || v == version)    
    }

    Message getHapiMessage() {
        return target
    }
}