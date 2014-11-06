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

import org.junit.Before
import org.junit.Test

import static org.openehealth.ipf.modules.hl7dsl.MessageAdapters.*
import ca.uhn.hl7v2.model.v24.group.ORU_R01_PATIENT
import ca.uhn.hl7v2.model.v24.message.ORU_R01

/**
 * @author Martin Krasser
 * @author Christian Ohr
 * @author Mitko Kolev
 */
class GroupAdapterTest extends groovy.test.GroovyAssert {
    
    MessageAdapter<ORU_R01> message

    @Before
    void setUp() {
        message = load('msg-02.hl7')
    }

    @Test
    void testInvokeMethod() {
        // invoke method on adapter
        assert message.count('MSH') == 1
        
        // invoke method on target
        assert message.isRequired('MSH')
    }

    @Test
    void testGet() {
        // property access on target
        assert message.version == '2.4'
    }

    @Test
    void testGetAt() {
        assert message.MSH instanceof SegmentAdapter
        assert message.PATIENT_RESULT instanceof Closure
        assert message.PATIENT_RESULT() instanceof List
        assert message.PATIENT_RESULT(0) instanceof GroupAdapter
        assert message.PATIENT_RESULT(0).PATIENT.PID instanceof SegmentAdapter

        // alternative notation 
        assert message['MSH'] instanceof SegmentAdapter
        assert message['PATIENT_RESULT'] instanceof Closure
        assert message['PATIENT_RESULT']() instanceof List
        assert message['PATIENT_RESULT'](0) instanceof GroupAdapter
        assert message['PATIENT_RESULT'](0)['PATIENT']['PID'] instanceof SegmentAdapter
    }

    @Test
    void testPath(){
         assert message.path == ''
         assert message.PATIENT_RESULT(0).path == 'PATIENT_RESULT(0)'
         assert message.PATIENT_RESULT(0).PATIENT.PID.path == 'PATIENT_RESULT(0).PATIENT.PID'
         assert message.PATIENT_RESULT(0).PATIENT.PID.path == 'PATIENT_RESULT(0).PATIENT.PID'
         assert message.PATIENT_RESULT(0).ORDER_OBSERVATION.path == 'PATIENT_RESULT(0).ORDER_OBSERVATION(0)'
         assert message.PATIENT_RESULT(0).ORDER_OBSERVATION(0).path == 'PATIENT_RESULT(0).ORDER_OBSERVATION(0)'
         assert message.PATIENT_RESULT(0).ORDER_OBSERVATION.OBR.path == 'PATIENT_RESULT(0).ORDER_OBSERVATION(0).OBR'
         assert message.PATIENT_RESULT(0).ORDER_OBSERVATION.OBR.path == 'PATIENT_RESULT(0).ORDER_OBSERVATION(0).OBR'
         assert message.PATIENT_RESULT(0).ORDER_OBSERVATION.OBSERVATION.path == 'PATIENT_RESULT(0).ORDER_OBSERVATION(0).OBSERVATION(0)'
         assert message.PATIENT_RESULT(0).ORDER_OBSERVATION.OBSERVATION(0).path == 'PATIENT_RESULT(0).ORDER_OBSERVATION(0).OBSERVATION(0)'
         
         assert message.PATIENT_RESULT(0).ORDER_OBSERVATION.OBSERVATION(0).OBX.path == 'PATIENT_RESULT(0).ORDER_OBSERVATION(0).OBSERVATION(0).OBX'
         assert message.PATIENT_RESULT(0).ORDER_OBSERVATION(0).OBSERVATION(0).OBX.path == 'PATIENT_RESULT(0).ORDER_OBSERVATION(0).OBSERVATION(0).OBX'
         
         assert 2 == message.PATIENT_RESULT(0).ORDER_OBSERVATION(1).OBSERVATION().size()
         
         assert message.PATIENT_RESULT(0).ORDER_OBSERVATION(1).OBSERVATION(0).OBX.path == 'PATIENT_RESULT(0).ORDER_OBSERVATION(1).OBSERVATION(0).OBX'
         assert message.PATIENT_RESULT(0).ORDER_OBSERVATION(1).OBSERVATION(1).OBX.path == 'PATIENT_RESULT(0).ORDER_OBSERVATION(1).OBSERVATION(1).OBX'
         
         //OBSERVATION(2) is created even though it is not in the message.
         //This is a feature of the DSL, see SelectorClosure call implementation 
         assert message.PATIENT_RESULT(0).ORDER_OBSERVATION(1).OBSERVATION(2).OBX.path == 'PATIENT_RESULT(0).ORDER_OBSERVATION(1).OBSERVATION(2).OBX'
    }

    @Test
    void testCount() {
        assert message.PATIENT_RESULT(0).count('ORDER_OBSERVATION') == 2
        
        // alternative notation
        assert message['PATIENT_RESULT'](0).count('ORDER_OBSERVATION') == 2
    }

    @Test
    void testGetTarget() {
        assert message.target instanceof ca.uhn.hl7v2.model.Message
    }

    @Test
	void testGrep() {
		// We have three OBX segments somewhere in the message
		assertEquals(3, message.grep { it.name == 'OBX' }.size())
	}

    @Test
	void testFind() {
		def obx = message.find { it.name == 'OBX' }
		assert obx[3][1].value == '25026500'
		obx = message.findOBX()
		assert message.findOBX()[3][1].value == '25026500'
		assert obx[3][1].value == '25026500'
		def patient = message.findPATIENT()
		assert patient.target instanceof ORU_R01_PATIENT
		def visit = message.findVISIT()
		assert visit == null
	}

    @Test
	void testFindAll() {
		def obxs = message.findAll { it.name == 'OBX' }
		assertEquals(3, obxs?.size())
		def obxs2 = message.findAllOBX()
		assertEquals(obxs*.name, obxs2*.name)
	}

    @Test
	void testFindIndexOf() {
		def index = message.findIndexOf { it.name == 'OBX' }
		assertEquals('PATIENT_RESULT(0).ORDER_OBSERVATION(0).OBSERVATION(0).OBX', index)		
		def index2 = message.findIndexOfOBX()
		assertEquals(index, index2)
	}

    @Test
	void testFindLastIndexOf() {
		def index = message.findLastIndexOf { it.name == 'OBX' }
		assertEquals('PATIENT_RESULT(0).ORDER_OBSERVATION(1).OBSERVATION(1).OBX', index)
		def index2 = message.findLastIndexOfOBX()
		assertEquals(index, index2)
	}

    @Test
	void testFindIndexValues() {
		def indexes = message.findIndexValues { it.name == 'OBX' }
		assertEquals(3, indexes?.size())
		assert indexes.contains('PATIENT_RESULT(0).ORDER_OBSERVATION(0).OBSERVATION(0).OBX')
		assert indexes.contains('PATIENT_RESULT(0).ORDER_OBSERVATION(1).OBSERVATION(0).OBX')
		assert indexes.contains('PATIENT_RESULT(0).ORDER_OBSERVATION(1).OBSERVATION(1).OBX')
	}

    @Test
	void testSplit() {
		def (segments, groups) = message.split { it instanceof SegmentAdapter }
		assertEquals(27, segments.size())
		assertEquals(7, groups.size())
	}

    @Test
	void testEach() {
		int numberOfStructures = 0
		message.each { numberOfStructures++ }
		assertEquals(34,  numberOfStructures)
	}

    @Test
	void testEachWithIndex() {
		def found = []
		message.eachWithIndex { structure, index ->
			found << index
		}
		assert found.contains('PATIENT_RESULT(0).ORDER_OBSERVATION(1).OBSERVATION(1).NTE(17)')
		assert found.contains('PATIENT_RESULT(0).PATIENT.PID')
		assert found.contains('MSH')
	}

    @Test
	void testEvery() {
		assertFalse message.every { it instanceof SegmentAdapter }
	}

    @Test
	void testAny() {
		assert message.any { it instanceof GroupAdapter }		
	}

    @Test
	void testSpread() {
		// Length of all structure names concatenated
		assertEquals(169,  message*.name.join('').length())
	}

    @Test
    void testNrp() {
        def nteCount = observation(message).count('NTE')
        assert observation(message).nrp('NTE') instanceof SegmentAdapter
        assert observation(message).count('NTE') == nteCount + 1
    }

    @Test
	void testIsEmpty() {
		assert message.PATIENT_RESULT.isEmpty() == false
		assert message.PATIENT_RESULT.PATIENT.VISIT.isEmpty() == true
	}

    @Test
	void testFor() {
		int length = 0
		for (def s in message) {
			length += s.name.length()
		}
		assertEquals(169, length)
	}
	
    
    private GroupAdapter observation(message) {
        message.PATIENT_RESULT(0).ORDER_OBSERVATION(1).OBSERVATION(1)		
    }
    
}